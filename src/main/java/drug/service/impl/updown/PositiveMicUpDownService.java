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

import drug.commons.excelModel.MicExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.MicDataCheck;
import drug.commons.util.StringUtil;
import drug.commons.util.Transfer;
import drug.dao.StrainCodingDAO;
import drug.dao.StrainMicDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainMic;
import drug.model.StrainCoding;
import drug.model.StrainMic;
import drug.service.UpDownService;

@Service("positiveMicUpDown")
/**
 * 阳性MIC导入/导出
 */
public class PositiveMicUpDownService implements UpDownService {

	public static Logger log = Logger.getLogger("R");

	@Autowired
	private StrainCodingDAO strainDAO;
	@Autowired
	private StrainMicDAO micDAO;

	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		String[] importColumns = MicExcel.getPositiveExportColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExcelException e) {
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
	public File exportDatas(String nos, File file) throws ExcelException {
		if (nos == null || nos.trim().equals("") || nos.trim().equals(",")){
			throw new DataViolationException("没有选择导出的菌株信息");
		}
		String[] strainnos = nos.trim().split(",");
		List<StrainMic> strainMics = micDAO.selectByNos(strainnos, "阳性");
		
		List<Map<String, Object>> bodyList = this.retransfer(strainMics);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(MicExcel.getPositiveExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "MIC信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}
	
	private List<Map<String, Object>> retransfer(List<StrainMic> strainMics) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StrainMic mic : strainMics) {
			Map<String, Object> map = new HashMap<String, Object>();
			PStrainMic pmic = Transfer.changetoPageModel(mic);
			map.put(MicExcel.STRAINNO, pmic.getStrainno());
			map.put(MicExcel.MICALIAS, pmic.getMicalias());
			map.put(MicExcel.AMP,String.valueOf(pmic.getAmp()));
			map.put(MicExcel.CIP,String.valueOf(pmic.getCip()));
			map.put(MicExcel.CLI,String.valueOf(pmic.getCli()));
			map.put(MicExcel.CQM,String.valueOf(pmic.getCqm()));
			map.put(MicExcel.ERY,String.valueOf(pmic.getEry()));
			map.put(MicExcel.FFC,String.valueOf(pmic.getFfc()));
			map.put(MicExcel.FOX,String.valueOf(pmic.getFox()));
			map.put(MicExcel.GEN,String.valueOf(pmic.getGen()));
			map.put(MicExcel.LZD,String.valueOf(pmic.getLzd()));
			map.put(MicExcel.OXA,String.valueOf(pmic.getOxa()));
			map.put(MicExcel.PEN,String.valueOf(pmic.getPen()));
			map.put(MicExcel.RIF,String.valueOf(pmic.getRif()));
			map.put(MicExcel.TET,String.valueOf(pmic.getTet()));
			map.put(MicExcel.TIA,String.valueOf(pmic.getTia()));
			map.put(MicExcel.VAL,String.valueOf(pmic.getVal()));
			map.put(MicExcel.VAN,String.valueOf(pmic.getVan()));
			map.put(MicExcel.REMARK,pmic.getRemark());
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
				mic.setStrainno(String.valueOf(map.get(MicExcel.STRAINNO)));
				mic.setMicalias(String.valueOf(map.get(MicExcel.MICALIAS)));
				mic.setAmp(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.AMP))));
				mic.setCip(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.CIP))));
				mic.setCli(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.CLI))));
				mic.setCqm(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.CQM))));
				mic.setEry(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.ERY))));
				mic.setFfc(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.FFC))));
				mic.setFox(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.FOX))));
				mic.setGen(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.GEN))));
				mic.setLzd(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.LZD))));
				mic.setOxa(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.OXA))));
				mic.setPen(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.PEN))));
				mic.setRif(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.RIF))));
				mic.setTet(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.TET))));
				mic.setTia(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.TIA))));
				mic.setVal(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.VAL))));
				mic.setVan(StringUtil.parseDouble(String.valueOf(map.get(MicExcel.VAN))));
				mic.setOperator(String.valueOf(map.get(MicExcel.OPERATOR)));
				mic.setMicdetectiontype(String.valueOf(map.get(MicExcel.MICDETECTIONTYPE)));
				mic.setRemark(String.valueOf(map.get(MicExcel.REMARK)));
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
