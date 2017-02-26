package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExeclException;
import drug.commons.execlModel.MicExecl;
import drug.commons.util.ExeclUtil;
import drug.commons.util.MicDataCheck;
import drug.commons.util.Transfer;
import drug.dao.StrainCodingDAO;
import drug.dao.StrainMicDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainMic;
import drug.model.StrainCoding;
import drug.model.StrainMic;
import drug.service.UpDownService;

@Service("positiveMicUpDown")
public class PositiveMicUpDownService implements UpDownService {

	public static Logger log = Logger.getLogger("R");

	@Autowired
	private StrainCodingDAO strainDAO;
	@Autowired
	private StrainMicDAO micDAO;

	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExeclException {
		String[] importColumns = MicExecl.getPositiveExportColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExeclException e) {
			throw e;
		}
		List<PStrainMic> errorList = new ArrayList<PStrainMic>();
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<PStrainMic> pageList = this.transferCoding(bodyList, errorList, user);
		List<StrainMic> addList = new ArrayList<StrainMic>();
		CheckCodingAttrs(addList, errorList, pageList);
		for (StrainMic mic : addList) {
			try {
				mic.setAdduser(user);
				micDAO.insert(mic);
				log.info("【mic信息导入成功】："+ mic+"【"+user+"】");
			} catch (Exception e) {
				e.printStackTrace();
				String errorMsg = "";
				if (e instanceof DuplicateKeyException) {
					errorMsg = "该Mic信息已经存在";
				} else if (e instanceof DataIntegrityViolationException) {
					errorMsg = "该菌株编号不存在";
				} else if (e instanceof CannotCreateTransactionException 
						|| e instanceof DataAccessResourceFailureException) {
					errorMsg = "数据库服务异常,请重新添加";
				} else {
					errorMsg = "未知异常";
				}
				PStrainMic pmic = Transfer.changetoPageModel(mic);
				pmic.setOtherMsg(errorMsg);
				errorList.add(pmic);
				log.error("【导入mic信息异常】："+pmic+ e +"【"+user+"】");
			}
		}
		ImportResultModel result = new ImportResultModel(errorList, bodyList.size());
		return result;
	}

	@Override
	public File exportDatas(String nos, File file) throws ExeclException {
		if (nos == null || nos.trim().equals("") || nos.trim().equals(",")){
			throw new DataViolationException("没有选择导出的菌株信息");
		}
		String[] strainnos = nos.trim().split(",");
		List<StrainMic> strainMics = micDAO.selectByNos(strainnos, "阳性");
		
		List<Map<String, Object>> bodyList = this.retransfer(strainMics);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(MicExecl.getPositiveExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "MIC信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExeclException("写入execl文件失败");
		}
		return file;
	}
	
	private List<Map<String, Object>> retransfer(List<StrainMic> strainMics) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StrainMic mic : strainMics) {
			Map<String, Object> map = new HashMap<String, Object>();
			PStrainMic pmic = Transfer.changetoPageModel(mic);
			map.put(MicExecl.STRAINNO, pmic.getStrainno());
			map.put(MicExecl.MICALIAS, pmic.getMicalias());
			map.put(MicExecl.AMP,String.valueOf(pmic.getAmp()));
			map.put(MicExecl.CIP,String.valueOf(pmic.getCip()));
			map.put(MicExecl.CLI,String.valueOf(pmic.getCli()));
			map.put(MicExecl.CQM,String.valueOf(pmic.getCqm()));
			map.put(MicExecl.ERY,String.valueOf(pmic.getEry()));
			map.put(MicExecl.FFC,String.valueOf(pmic.getFfc()));
			map.put(MicExecl.FOX,String.valueOf(pmic.getFox()));
			map.put(MicExecl.GEN,String.valueOf(pmic.getGen()));
			map.put(MicExecl.LZD,String.valueOf(pmic.getLzd()));
			map.put(MicExecl.OXA,String.valueOf(pmic.getOxa()));
			map.put(MicExecl.PEN,String.valueOf(pmic.getPen()));
			map.put(MicExecl.RIF,String.valueOf(pmic.getRif()));
			map.put(MicExecl.TET,String.valueOf(pmic.getTet()));
			map.put(MicExecl.TIA,String.valueOf(pmic.getTia()));
			map.put(MicExecl.VAL,String.valueOf(pmic.getVal()));
			map.put(MicExecl.VAN,String.valueOf(pmic.getVan()));
			map.put(MicExecl.REMARK,pmic.getRemark());
			list.add(map);
		}
		return list;
	}

	private void CheckCodingAttrs(List<StrainMic> addList,
			List<PStrainMic> errorList, List<PStrainMic> pageList) {
		for (PStrainMic pmic : pageList) {
			try {
				MicDataCheck.checkAllDate(pmic);
			} catch (Exception e) {
				pmic.setOtherMsg(e.getMessage());
				errorList.add(pmic);
				continue;
			}
			String strainno = pmic.getStrainno();
			if (strainno.equals("")) {
				pmic.setOtherMsg("菌株编号不能为空");
				errorList.add(pmic);
				continue;
			}
			try {
				StrainCoding strain = strainDAO.select(strainno);
				if (strain == null) {
					pmic.setOtherMsg("该菌株编号不存在");
					errorList.add(pmic);
					continue;
				} else if (!strain.getGramstain().equals("阳性")) {
					pmic.setOtherMsg("该菌株为" + strain.getGramstain() + "菌株");
					errorList.add(pmic);
					continue;
				} else {
					addList.add(Transfer.changeToEntity(pmic));
				}
			} catch (Exception e) {
				e.printStackTrace();
				pmic.setOtherMsg("未知错误");
				errorList.add(pmic);
				continue;
			}
		}
	}

	private List<PStrainMic> transferCoding(List<Map<String, Object>> bodyList, List<PStrainMic> errorList,
			String user) {
		List<PStrainMic> stranlist = new ArrayList<PStrainMic>();
		PStrainMic mic = null;
		for (Map<String,Object> map : bodyList) {
			mic = new PStrainMic();
			try {
				mic.setStrainno(String.valueOf(map.get(MicExecl.STRAINNO)));
				mic.setMicalias(String.valueOf(map.get(MicExecl.MICALIAS)));
				mic.setAmp(Double.valueOf(String.valueOf(map.get(MicExecl.AMP))));
				mic.setCip(Double.valueOf(String.valueOf(map.get(MicExecl.CIP))));
				mic.setCli(Double.valueOf(String.valueOf(map.get(MicExecl.CLI))));
				mic.setCqm(Double.valueOf(String.valueOf(map.get(MicExecl.CQM))));
				mic.setEry(Double.valueOf(String.valueOf(map.get(MicExecl.ERY))));
				mic.setFfc(Double.valueOf(String.valueOf(map.get(MicExecl.FFC))));
				mic.setFox(Double.valueOf(String.valueOf(map.get(MicExecl.FOX))));
				mic.setGen(Double.valueOf(String.valueOf(map.get(MicExecl.GEN))));
				mic.setLzd(Double.valueOf(String.valueOf(map.get(MicExecl.LZD))));
				mic.setOxa(Double.valueOf(String.valueOf(map.get(MicExecl.OXA))));
				mic.setPen(Double.valueOf(String.valueOf(map.get(MicExecl.PEN))));
				mic.setRif(Double.valueOf(String.valueOf(map.get(MicExecl.RIF))));
				mic.setTet(Double.valueOf(String.valueOf(map.get(MicExecl.TET))));
				mic.setTia(Double.valueOf(String.valueOf(map.get(MicExecl.TIA))));
				mic.setVal(Double.valueOf(String.valueOf(map.get(MicExecl.VAL))));
				mic.setVan(Double.valueOf(String.valueOf(map.get(MicExecl.VAN))));
				mic.setOperator(String.valueOf(map.get(MicExecl.OPERATOR)));
				mic.setMicdetectiontype(String.valueOf(map.get(MicExecl.MICDETECTIONTYPE)));
				mic.setRemark(String.valueOf(map.get(MicExecl.REMARK)));
			} catch (NumberFormatException e) {
				mic.setOtherMsg("药物浓度必须为数字");
				errorList.add(mic);
				continue;
			}
			mic.setOtherMsg(user);
			stranlist.add(mic);
		}
		return stranlist;
	}

	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}

	public void setMicDAO(StrainMicDAO micDAO) {
		this.micDAO = micDAO;
	}

}
