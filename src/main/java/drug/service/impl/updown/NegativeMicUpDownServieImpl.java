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

@Service("negativeMicUpDown")
public class NegativeMicUpDownServieImpl implements UpDownService {
	public static Logger log = Logger.getLogger("R");

	@Autowired
	private StrainCodingDAO strainDAO;
	@Autowired
	private StrainMicDAO micDAO;

	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExeclException {
		String[] importColumns = MicExecl.getNegativeExportColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		} catch (ExeclException e) {
			throw e;
		}
		List<PStrainMic> errorList = new ArrayList<PStrainMic>();
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<PStrainMic> pageList = this.transferCoding(bodyList, errorList,
				user);
		List<StrainMic> addList = new ArrayList<StrainMic>();
		CheckCodingAttrs(addList, errorList, pageList);
		for (StrainMic mic : addList) {
			try {
				mic.setAdduser(user);
				micDAO.insert(mic);
				log.info("【mic信息导入成功】：" + mic + "【" + user + "】");
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
				log.error("【导入mic信息异常】：" + pmic + e + "【" + user + "】");
			}
		}
		ImportResultModel result = new ImportResultModel(errorList,
				bodyList.size());
		return result;
	}

	@Override
	public File exportDatas(String nos, File file) throws ExeclException {
		if (nos == null || nos.trim().equals("") || nos.trim().equals(",")) {
			throw new DataViolationException("没有选择导出的菌株信息");
		}
		String[] strainnos = nos.trim().split(",");
		List<StrainMic> strainMics = micDAO.selectByNos(strainnos, "阴性");

		List<Map<String, Object>> bodyList = this.retransfer(strainMics);

		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(MicExecl.getNegativeExportColumns());
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
			map.put(MicExecl.AMK,String.valueOf(pmic.getAmk()));
			map.put(MicExecl.AMP,String.valueOf(pmic.getAmp()));
			map.put(MicExecl.APR,String.valueOf(pmic.getApr()));
			map.put(MicExecl.CAZ,String.valueOf(pmic.getCaz()));
			map.put(MicExecl.CL,String.valueOf(pmic.getCl()));
			map.put(MicExecl.CHL,String.valueOf(pmic.getChl()));
			map.put(MicExecl.CIP,String.valueOf(pmic.getCip()));
			map.put(MicExecl.CTX,String.valueOf(pmic.getCtx()));
			map.put(MicExecl.CQM,String.valueOf(pmic.getCqm()));
			map.put(MicExecl.DOX,String.valueOf(pmic.getDox()));
			map.put(MicExecl.FOX,String.valueOf(pmic.getFox()));
			map.put(MicExecl.FFC,String.valueOf(pmic.getFfc()));
			map.put(MicExecl.FOS,String.valueOf(pmic.getFos()));
			map.put(MicExecl.GEN,String.valueOf(pmic.getGen()));
			map.put(MicExecl.IMP,String.valueOf(pmic.getImp()));
			map.put(MicExecl.OQX,String.valueOf(pmic.getOqx()));
			map.put(MicExecl.NEO,String.valueOf(pmic.getNeo()));
			map.put(MicExecl.SXT,String.valueOf(pmic.getSxt()));
			map.put(MicExecl.STR,String.valueOf(pmic.getStr()));
			map.put(MicExecl.TET,String.valueOf(pmic.getTet()));
			map.put(MicExecl.MEM,String.valueOf(pmic.getMem()));

			map.put(MicExecl.REMARK, pmic.getRemark());
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
				} else if (!strain.getGramstain().equals("阴性")) {
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

	private List<PStrainMic> transferCoding(List<Map<String, Object>> bodyList,
			List<PStrainMic> errorList, String user) {
		List<PStrainMic> stranlist = new ArrayList<PStrainMic>();
		PStrainMic mic = null;
		for (Map<String, Object> map : bodyList) {
			mic = new PStrainMic();
			try {
				mic.setStrainno(String.valueOf(map.get(MicExecl.STRAINNO)));
				mic.setMicalias(String.valueOf(map.get(MicExecl.MICALIAS)));
				mic.setAmk(Double.valueOf(String.valueOf(map.get(MicExecl.AMK))));
				mic.setAmp(Double.valueOf(String.valueOf(map.get(MicExecl.AMP))));
				mic.setApr(Double.valueOf(String.valueOf(map.get(MicExecl.APR))));
				mic.setCaz(Double.valueOf(String.valueOf(map.get(MicExecl.CAZ))));
				mic.setCl(Double.valueOf(String.valueOf(map.get(MicExecl.CL))));
				mic.setChl(Double.valueOf(String.valueOf(map.get(MicExecl.CHL))));
				mic.setCip(Double.valueOf(String.valueOf(map.get(MicExecl.CIP))));
				mic.setCtx(Double.valueOf(String.valueOf(map.get(MicExecl.CTX))));
				mic.setCqm(Double.valueOf(String.valueOf(map.get(MicExecl.CQM))));
				mic.setDox(Double.valueOf(String.valueOf(map.get(MicExecl.DOX))));
				mic.setFox(Double.valueOf(String.valueOf(map.get(MicExecl.FOX))));
				mic.setFfc(Double.valueOf(String.valueOf(map.get(MicExecl.FFC))));
				mic.setFos(Double.valueOf(String.valueOf(map.get(MicExecl.FOS))));
				mic.setGen(Double.valueOf(String.valueOf(map.get(MicExecl.GEN))));
				mic.setImp(Double.valueOf(String.valueOf(map.get(MicExecl.IMP))));
				mic.setOqx(Double.valueOf(String.valueOf(map.get(MicExecl.OQX))));
				mic.setNeo(Double.valueOf(String.valueOf(map.get(MicExecl.NEO))));
				mic.setSxt(Double.valueOf(String.valueOf(map.get(MicExecl.SXT))));
				mic.setStr(Double.valueOf(String.valueOf(map.get(MicExecl.STR))));
				mic.setTet(Double.valueOf(String.valueOf(map.get(MicExecl.TET))));
				mic.setMem(Double.valueOf(String.valueOf(map.get(MicExecl.MEM))));

				mic.setOperator(String.valueOf(map.get(MicExecl.OPERATOR)));
				mic.setMicdetectiontype(String.valueOf(map
						.get(MicExecl.MICDETECTIONTYPE)));
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
