package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import drug.commons.excelModel.MicExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.MicDataCheck;
import drug.commons.util.StringUtil;
import drug.commons.util.Transfer;
import drug.dao.StrainCodingDAO;
import drug.dao.StrainMicDAO;
import drug.dto.pageModel.PStrainMic;
import drug.dto.pageModel.UploadResultModel;
import drug.model.StrainCoding;
import drug.model.StrainMic;
import drug.service.UpDownService;
import drug.service.impl.UploadUpdateService;

public abstract class MicUpDownService implements UpDownService,
		UploadUpdateService {

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	protected StrainCodingDAO strainDAO;
	@Autowired
	protected StrainMicDAO micDAO;

	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}

	public void setMicDAO(StrainMicDAO micDAO) {
		this.micDAO = micDAO;
	}

	@Override
	public abstract UploadResultModel importDatas(InputStream input, String user)
			throws ExcelException;

	@Override
	public abstract File exportDatas(String ids, File file)
			throws ExcelException;

	@Override
	@Transactional
	public UploadResultModel uploadUpdate(InputStream input, String gram, String user) throws ExcelException{
		String[] neededUpdateColumns = MicExcel.getNeededUpdateColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(neededUpdateColumns);
		try {
			execlUtil.readExecl(input);
		} catch (ExcelException e) {
			throw e;
		}
		List<Map<String, Object>> errorList = new ArrayList<Map<String,Object>>();
		String[] headArray = execlUtil.getHeadArray();
		System.out.println(headArray);
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<Map<String, Object>> addList = checkUpdateMicAttrs(headArray ,bodyList, errorList, gram);
//		micDAO.updateBatch(addList);   //error code
		for (Map<String, Object> map : addList) {
			micDAO.updateByMap(map);
			log.info("【mic信息修改成功】：" + map + "【" + user + "】");
			System.out.println("【mic信息修改成功】：" + map + "【" + user + "】");
		}
		UploadResultModel result = new UploadResultModel(errorList, bodyList.size());
		return result;
	}

	/**
	 * 将阴性菌株mic信息转化为即将写入excel文件的集合
	 * 
	 * @param strainMics
	 * @return
	 */
	protected List<Map<String, Object>> retransferForNegative(
			List<StrainMic> strainMics) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StrainMic mic : strainMics) {
			Map<String, Object> map = new HashMap<String, Object>();
			PStrainMic pmic = Transfer.changetoPageModel(mic);
			map.put(MicExcel.STRAINNO, pmic.getStrainno());
			map.put(MicExcel.MICALIAS, pmic.getMicalias());
			map.put(MicExcel.AMK, String.valueOf(pmic.getAmk()));
			map.put(MicExcel.AMP, String.valueOf(pmic.getAmp()));
			map.put(MicExcel.APR, String.valueOf(pmic.getApr()));
			map.put(MicExcel.CAZ, String.valueOf(pmic.getCaz()));
			map.put(MicExcel.CL, String.valueOf(pmic.getCl()));
			map.put(MicExcel.CHL, String.valueOf(pmic.getChl()));
			map.put(MicExcel.CIP, String.valueOf(pmic.getCip()));
			map.put(MicExcel.CTX, String.valueOf(pmic.getCtx()));
			map.put(MicExcel.CQM, String.valueOf(pmic.getCqm()));
			map.put(MicExcel.DOX, String.valueOf(pmic.getDox()));
			map.put(MicExcel.FOX, String.valueOf(pmic.getFox()));
			map.put(MicExcel.FFC, String.valueOf(pmic.getFfc()));
			map.put(MicExcel.FOS, String.valueOf(pmic.getFos()));
			map.put(MicExcel.GEN, String.valueOf(pmic.getGen()));
			map.put(MicExcel.IMP, String.valueOf(pmic.getImp()));
			map.put(MicExcel.OQX, String.valueOf(pmic.getOqx()));
			map.put(MicExcel.NEO, String.valueOf(pmic.getNeo()));
			map.put(MicExcel.SXT, String.valueOf(pmic.getSxt()));
			map.put(MicExcel.STR, String.valueOf(pmic.getStr()));
			map.put(MicExcel.TET, String.valueOf(pmic.getTet()));
			map.put(MicExcel.MEM, String.valueOf(pmic.getMem()));

			map.put(MicExcel.REMARK, pmic.getRemark());
			list.add(map);
		}
		return list;
	}

	/**
	 * 将阳性菌株mic信息转化为即将写入excel文件的集合
	 * 
	 * @param strainMics
	 * @return
	 */
	protected List<Map<String, Object>> retransferForPositive(
			List<StrainMic> strainMics) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StrainMic mic : strainMics) {
			Map<String, Object> map = new HashMap<String, Object>();
			PStrainMic pmic = Transfer.changetoPageModel(mic);
			map.put(MicExcel.STRAINNO, pmic.getStrainno());
			map.put(MicExcel.MICALIAS, pmic.getMicalias());
			map.put(MicExcel.AMP, String.valueOf(pmic.getAmp()));
			map.put(MicExcel.CIP, String.valueOf(pmic.getCip()));
			map.put(MicExcel.CLI, String.valueOf(pmic.getCli()));
			map.put(MicExcel.CQM, String.valueOf(pmic.getCqm()));
			map.put(MicExcel.ERY, String.valueOf(pmic.getEry()));
			map.put(MicExcel.FFC, String.valueOf(pmic.getFfc()));
			map.put(MicExcel.FOX, String.valueOf(pmic.getFox()));
			map.put(MicExcel.GEN, String.valueOf(pmic.getGen()));
			map.put(MicExcel.LZD, String.valueOf(pmic.getLzd()));
			map.put(MicExcel.OXA, String.valueOf(pmic.getOxa()));
			map.put(MicExcel.PEN, String.valueOf(pmic.getPen()));
			map.put(MicExcel.RIF, String.valueOf(pmic.getRif()));
			map.put(MicExcel.TET, String.valueOf(pmic.getTet()));
			map.put(MicExcel.TIA, String.valueOf(pmic.getTia()));
			map.put(MicExcel.VAL, String.valueOf(pmic.getVal()));
			map.put(MicExcel.VAN, String.valueOf(pmic.getVan()));
			map.put(MicExcel.REMARK, pmic.getRemark());
			list.add(map);
		}
		return list;
	}

	/**
	 * 将从excel文件中读取的阴性属性集合转化为待持久化的数据集合
	 * 
	 * @param bodyList
	 * @param errorList
	 * @param user
	 * @return
	 */
	protected List<PStrainMic> transferCodingForNegative(
			List<Map<String, Object>> bodyList, List<PStrainMic> errorList,
			String user) {
		List<PStrainMic> stranlist = new ArrayList<PStrainMic>();
		PStrainMic mic = null;
		for (Map<String, Object> map : bodyList) {
			mic = new PStrainMic();
			try {
				mic.setStrainno(String.valueOf(map.get(MicExcel.STRAINNO)));
				mic.setMicalias(String.valueOf(map.get(MicExcel.MICALIAS)));
				mic.setAmk(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.AMK))));
				mic.setAmp(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.AMP))));
				mic.setApr(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.APR))));
				mic.setCaz(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CAZ))));
				mic.setCl(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CL))));
				mic.setChl(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CHL))));
				mic.setCip(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CIP))));
				mic.setCtx(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CTX))));
				mic.setCqm(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CQM))));
				mic.setDox(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.DOX))));
				mic.setFox(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.FOX))));
				mic.setFfc(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.FFC))));
				mic.setFos(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.FOS))));
				mic.setGen(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.GEN))));
				mic.setImp(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.IMP))));
				mic.setOqx(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.OQX))));
				mic.setNeo(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.NEO))));
				mic.setSxt(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.SXT))));
				mic.setStr(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.STR))));
				mic.setTet(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.TET))));
				mic.setMem(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.MEM))));

				mic.setOperator(String.valueOf(map.get(MicExcel.OPERATOR)));
				mic.setMicdetectiontype(String.valueOf(map
						.get(MicExcel.MICDETECTIONTYPE)));
				mic.setRemark(String.valueOf(map.get(MicExcel.REMARK)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				mic.setOtherMsg("药物浓度必须为数字");
				errorList.add(mic);
				continue;
			}
			mic.setOtherMsg(user);
			stranlist.add(mic);
		}
		return stranlist;
	}

	/**
	 * 将从excel文件中读取的阳性属性集合转化为待持久化的数据集合
	 * 
	 * @param bodyList
	 * @param errorList
	 * @param user
	 * @return
	 */
	protected List<PStrainMic> transferCodingForPositive(
			List<Map<String, Object>> bodyList, List<PStrainMic> errorList,
			String user) {
		List<PStrainMic> stranlist = new ArrayList<PStrainMic>();
		PStrainMic mic = null;
		for (Map<String, Object> map : bodyList) {
			mic = new PStrainMic();
			try {
				mic.setStrainno(String.valueOf(map.get(MicExcel.STRAINNO)));
				mic.setMicalias(String.valueOf(map.get(MicExcel.MICALIAS)));
				mic.setAmp(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.AMP))));
				mic.setCip(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CIP))));
				mic.setCli(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CLI))));
				mic.setCqm(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.CQM))));
				mic.setEry(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.ERY))));
				mic.setFfc(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.FFC))));
				mic.setFox(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.FOX))));
				mic.setGen(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.GEN))));
				mic.setLzd(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.LZD))));
				mic.setOxa(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.OXA))));
				mic.setPen(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.PEN))));
				mic.setRif(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.RIF))));
				mic.setTet(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.TET))));
				mic.setTia(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.TIA))));
				mic.setVal(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.VAL))));
				mic.setVan(StringUtil.parseDouble(String.valueOf(map
						.get(MicExcel.VAN))));
				mic.setOperator(String.valueOf(map.get(MicExcel.OPERATOR)));
				mic.setMicdetectiontype(String.valueOf(map
						.get(MicExcel.MICDETECTIONTYPE)));
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

	/**
	 * 检查菌株属性
	 * 
	 * @param addList
	 * @param errorList
	 * @param pageList
	 * @param gram
	 */
	protected void CheckCodingAttrs(List<StrainMic> addList,
			List<PStrainMic> errorList, List<PStrainMic> pageList, String gram) {
		for (PStrainMic pmic : pageList) {
			try {
				MicDataCheck.checkAllDate(pmic);
			} catch (Exception e) {
				pmic.setOtherMsg(e.getMessage());
				errorList.add(pmic);
				continue;
			}
			String strainno = pmic.getStrainno();
			String alias = pmic.getMicalias();
			if (strainno.equals("") && alias.equals("")) {
				pmic.setOtherMsg("菌株编号/内部编号不能都为空");
				errorList.add(pmic);
				continue;
			}
			try {
				List<StrainCoding> strainnoList = strainDAO
						.selectStrainNoByAliasOrNo(strainno, alias);
				// StrainCoding strain = strainDAO.select(strainno);
				if (strainnoList == null || strainnoList.size() == 0) {
					pmic.setOtherMsg("没有与菌株编号/内部编号对应的菌株信息(菌株不存在)");
					errorList.add(pmic);
					continue;
				} else if (strainnoList.size() == 2) {
					pmic.setOtherMsg("菌株编号与内部编号对应的菌株信息不一致");
					errorList.add(pmic);
					continue;
				} else if (strainnoList.size() > 2) {
					pmic.setOtherMsg("数据出现错误,请联系维护人员查询");
					errorList.add(pmic);
					continue;
				} else {
					StrainCoding strain = strainnoList.get(0);
					pmic.setStrainno(strainnoList.get(0).getStrainno());
					if (!strain.getGramstain().equals(gram)) {
						pmic.setOtherMsg("该菌株为" + strain.getGramstain() + "菌株");
						errorList.add(pmic);
						continue;
					} else {
						addList.add(Transfer.changeToEntity(pmic));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				pmic.setOtherMsg("未知错误");
				errorList.add(pmic);
				continue;
			}
		}
	}

	/**
	 * 检查mic属性
	 * @param headArray
	 * @param bodyList
	 * @param errorList
	 * @param gram
	 * @return 待修改集合
	 */
	protected List<Map<String, Object>> checkUpdateMicAttrs(
			String[] headArray, List<Map<String, Object>> bodyList, List<Map<String, Object>> errorList, String gram) {
		// 获取可修改列
		String[] updateColumns = null;
		if (gram.equals("阴性")) {
			updateColumns = MicExcel.getNegativeUpdateColumns();
		} else {
			updateColumns = MicExcel.getPositiveUpdateColumns();
		}
		String[] drugs = MicExcel.getDrugColumns();
		
		// 获取待修改列
		Set<String> needUpdateColumns = new HashSet<String>();
		for (String column : updateColumns) {
			if (StringUtil.search(headArray, column)) {
				needUpdateColumns.add(column);
			}
		}
		if (needUpdateColumns.size() == 0) {
			throw new DataViolationException("execl文件中没有包含可以修改的列信息");
		}
		
		// 分离excel文件列数据，对药物浓度数据进行校验，对菌株编号/内部编号合法性进行校验
		List<Map<String, Object>> addList = new ArrayList<Map<String,Object>>();
		Map<String, Object> resultmap = null;
		Map<String, Double> drugMap = null;
		for (Map<String,Object> map : bodyList) {
			resultmap = new HashMap<String, Object>();
			drugMap = new HashMap<String, Double>();
			
			String strainno = (String)map.get(MicExcel.STRAINNO);
			String alias = (String)map.get(MicExcel.MICALIAS);
			resultmap.put("strainNo", strainno);
			resultmap.put("micAlias", alias);
			// 分离数据并校验药物浓度
			try {
				for (String column : needUpdateColumns) {
					if (StringUtil.search(drugs, column)) {
						Double value = null;
						try {
							value = StringUtil.parseDouble(String.valueOf(map.get(column)));
						} catch (NumberFormatException e) {
							throw new DataViolationException(column + "数字格式错误");
						}
						resultmap.put(column, value);
						drugMap.put(column, value);
					} else {
						switch (column) {
						case MicExcel.STRAINNO:
							resultmap.put("strainNo", String.valueOf(map.get(column)));
							break;
						case MicExcel.MICALIAS:
							resultmap.put("micAlias", String.valueOf(map.get(column)));
							break;
						case MicExcel.OPERATOR:
							resultmap.put("operator", String.valueOf(map.get(column)));
							break;
						case MicExcel.MICDETECTIONTYPE:
							resultmap.put("micdetectionType", String.valueOf(map.get(column)));
							break;
						case MicExcel.REMARK:
							resultmap.put("remark", String.valueOf(map.get(column)));
							break;
						default:
							break;
						}
					}
				}
				MicDataCheck.checkAllDate(drugMap);
			} catch (DataViolationException e) {
				resultmap.put("errorMsg", e.getMessage());
				errorList.add(resultmap);
				continue;
			}
			
			
			
			// 检验内部编号/菌株编号合法性
			if (strainno.equals("") && alias.equals("")) {
				resultmap.put("errorMsg", "菌株编号/内部编号不能都为空");
				errorList.add(resultmap);
				continue;
			}
			
			List<StrainMic> micList = micDAO.selectByNoOrAlias(strainno, alias);
			if (micList.size() == 1) {
				StrainMic mic = micList.get(0);
				resultmap.put("strainNo", mic.getStrainno());
				resultmap.put("micAlias", mic.getMicalias());
				if (!mic.getGramstain().equals(gram)) {
					resultmap.put("errorMsg", "该菌株为" + mic.getGramstain() + "菌株");
					errorList.add(resultmap);
				} else {
					addList.add(resultmap);
				}
			} else {
				if (micList.size() == 0) {
					resultmap.put("errorMsg", "没有与菌株编号/内部编号对应的菌株mic信息(菌株mic不存在)");
				} else if (micList.size() == 2) {
					resultmap.put("errorMsg", "菌株编号与内部编号对应的菌株mic信息不一致");
				} else {
					resultmap.put("errorMsg", "数据出现错误,请联系维护人员查询");
				}
				errorList.add(resultmap);
			}
		}
		System.out.println("=============");
		for (Map<String, Object> map2 : addList) {
			System.out.println(map2);
		}
		
		return addList;
	}
	
	/**
	 * 将从excel整理出来的集合转化为待持久化集合
	 * @param addList
	 * @return
	 */
//	private List<Map<String, Object>> transferUpdate(List<Map<String, Object>> addList) {
//		List<Map<String, Object>> resultlist = new ArrayList<Map<String,Object>>();
//		Map<String, Object> resultmap = null;
//		for (Map<String, Object> map : addList) {
//			resultmap = new HashMap<String, Object>();
//			if (map.get(MicExcel.STRAINNO)!=null){
//				resultmap.put("strainNo", map.get(MicExcel.STRAINNO));
//			}
//			if (map.get(MicExcel.MICALIAS)!=null){
//				resultmap.put("micAlias", map.get(MicExcel.MICALIAS));
//			}
//			if (map.get(MicExcel.AMK)!=null){
//				resultmap.put("AMK", (Double)map.get(MicExcel.AMK));
//			}
//			if (map.get(MicExcel.AMP)!=null){
//				resultmap.put("AMP", (Double)map.get(MicExcel.AMP));
//			}
//			if (map.get(MicExcel.APR)!=null){
//				resultmap.put("APR", (Double)map.get(MicExcel.APR));
//			}
//			if (map.get(MicExcel.AZM)!=null){
//				resultmap.put("AZM", (Double)map.get(MicExcel.AZM));
//			}
//			if (map.get(MicExcel.CFZ)!=null){
//				resultmap.put("CFZ", (Double)map.get(MicExcel.CFZ));
//			}
//			if (map.get(MicExcel.FEP)!=null){
//				resultmap.put("FEP", (Double)map.get(MicExcel.FEP));
//			}
//			if (map.get(MicExcel.CTX)!=null){
//				resultmap.put("CTX", (Double)map.get(MicExcel.CTX));
//			}
//			if (map.get(MicExcel.FOX)!=null){
//				resultmap.put("FOX", (Double)map.get(MicExcel.FOX));
//			}
//			if (map.get(MicExcel.CAZ)!=null){
//				resultmap.put("CAZ", (Double)map.get(MicExcel.CAZ));
//			}
//			if (map.get(MicExcel.CRO)!=null){
//				resultmap.put("CRO", (Double)map.get(MicExcel.CRO));
//			}
//			if (map.get(MicExcel.CQM)!=null){
//				resultmap.put("CQM", (Double)map.get(MicExcel.CQM));
//			}
//			if (map.get(MicExcel.CHL)!=null){
//				resultmap.put("CHL", (Double)map.get(MicExcel.CHL));
//			}
//			if (map.get(MicExcel.CIP)!=null){
//				resultmap.put("CIP", (Double)map.get(MicExcel.CIP));
//			}
//			if (map.get(MicExcel.TIA)!=null){
//				resultmap.put("TIA", (Double)map.get(MicExcel.TIA));
//			}
//			if (map.get(MicExcel.CLI)!=null){
//				resultmap.put("CLI", (Double)map.get(MicExcel.CLI));
//			}
//			if (map.get(MicExcel.CL)!=null){
//				resultmap.put("CL", (Double)map.get(MicExcel.CL));
//			}
//			if (map.get(MicExcel.DOX)!=null){
//				resultmap.put("DOX", (Double)map.get(MicExcel.DOX));
//			}
//			if (map.get(MicExcel.VAL)!=null){
//				resultmap.put("VAL", (Double)map.get(MicExcel.VAL));
//			}
//			if (map.get(MicExcel.ERY)!=null){
//				resultmap.put("ERY", (Double)map.get(MicExcel.ERY));
//			}
//			if (map.get(MicExcel.FFC)!=null){
//				resultmap.put("FFC", (Double)map.get(MicExcel.FFC));
//			}
//			if (map.get(MicExcel.FOS)!=null){
//				resultmap.put("FOS", (Double)map.get(MicExcel.FOS));
//			}
//			if (map.get(MicExcel.GEN)!=null){
//				resultmap.put("GEN", (Double)map.get(MicExcel.GEN));
//			}
//			if (map.get(MicExcel.IMP)!=null){
//				resultmap.put("IMP", (Double)map.get(MicExcel.IMP));
//			}
//			if (map.get(MicExcel.LEV)!=null){
//				resultmap.put("LEV", (Double)map.get(MicExcel.LEV));
//			}
//			if (map.get(MicExcel.LZD)!=null){
//				resultmap.put("LZD", (Double)map.get(MicExcel.LZD));
//			}
//			if (map.get(MicExcel.MEM)!=null){
//				resultmap.put("MEM", (Double)map.get(MicExcel.MEM));
//			}
//			if (map.get(MicExcel.MIN)!=null){
//				resultmap.put("MIN", (Double)map.get(MicExcel.MIN));
//			}
//			if (map.get(MicExcel.NAL)!=null){
//				resultmap.put("NAL", map.get(MicExcel.NAL));
//			}
//			if (map.get(MicExcel.NEO)!=null){
//				resultmap.put("NEO", map.get(MicExcel.NEO));
//			}
//			if (map.get(MicExcel.NET)!=null){
//				resultmap.put("NET", map.get(MicExcel.NET));
//			}
//			if (map.get(MicExcel.NIT)!=null){
//				resultmap.put("NIT", map.get(MicExcel.NIT));
//			}
//			if (map.get(MicExcel.NOR)!=null){
//				resultmap.put("NOR", map.get(MicExcel.NOR));
//			}
//			if (map.get(MicExcel.OFX)!=null){
//				resultmap.put("OFX", map.get(MicExcel.OFX));
//			}
//			if (map.get(MicExcel.OXA)!=null){
//				resultmap.put("OXA", map.get(MicExcel.OXA));
//			}
//			if (map.get(MicExcel.PEN)!=null){
//				resultmap.put("PEN", map.get(MicExcel.PEN));
//			}
//			if (map.get(MicExcel.PIP)!=null){
//				resultmap.put("PIP", map.get(MicExcel.PIP));
//			}
//			if (map.get(MicExcel.TZP)!=null){
//				resultmap.put("TZP", map.get(MicExcel.TZP));
//			}
//			if (map.get(MicExcel.RIF)!=null){
//				resultmap.put("RIF", map.get(MicExcel.RIF));
//			}
//			if (map.get(MicExcel.STR)!=null){
//				resultmap.put("STR", map.get(MicExcel.STR));
//			}
//			if (map.get(MicExcel.TEC)!=null){
//				resultmap.put("TEC", map.get(MicExcel.TEC));
//			}
//			if (map.get(MicExcel.TET)!=null){
//				resultmap.put("TET", map.get(MicExcel.TET));
//			}
//			if (map.get(MicExcel.TOB)!=null){
//				resultmap.put("TOB", map.get(MicExcel.TOB));
//			}
//			if (map.get(MicExcel.TMP)!=null){
//				resultmap.put("TMP", map.get(MicExcel.TMP));
//			}
//			if (map.get(MicExcel.SXT)!=null){
//				resultmap.put("SXT", map.get(MicExcel.SXT));
//			}
//			if (map.get(MicExcel.VAN)!=null){
//				resultmap.put("VAN", map.get(MicExcel.VAN));
//			}
//			if (map.get(MicExcel.OQX)!=null){
//				resultmap.put("OQX", map.get(MicExcel.OQX));
//			}
//			if (map.get(MicExcel.OPERATOR)!=null){
//				resultmap.put("operator", map.get(MicExcel.OPERATOR));
//			}
//			if (map.get(MicExcel.MICDETECTIONTYPE)!=null){
//				resultmap.put("micdetectionType", map.get(MicExcel.MICDETECTIONTYPE));
//			}
//			if (map.get(MicExcel.REMARK)!=null){
//				resultmap.put("remark", map.get(MicExcel.REMARK));
//			}
//			resultlist.add(resultmap);
//		}
//		return resultlist;
//	}
}
