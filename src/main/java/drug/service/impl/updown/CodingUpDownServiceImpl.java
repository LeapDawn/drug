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

import drug.commons.excelModel.StrainExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dao.StrainCodingDAO;
import drug.dao.StrainToNotesDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainCoding;
import drug.model.StrainCoding;
import drug.model.StrainToNotes;
import drug.service.UpDownService;

@Service("codingUpDown")
/**
 * 菌株信息导入/导出
 */
public class CodingUpDownServiceImpl implements UpDownService{

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private StrainCodingDAO strainDAO;
	@Autowired
	private StrainToNotesDAO notesDAO;
	
	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		String[] importColumns = StrainExcel.getCodingColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExcelException e) {
			throw e;
		}
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<PStrainCoding> pageList = this.transferCoding(bodyList, user);
		List<StrainCoding> addList = new ArrayList<StrainCoding>();
		List<PStrainCoding> errorList = checkCodingAttrs(addList, pageList);
		for (StrainCoding strain : addList) {
			try {
				strain.setAdduser(user);
				strainDAO.insert(strain);
				log.info("【菌株信息导入成功】："+ strain+"【"+user+"】");
			} catch (Exception e) {
				e.printStackTrace();
				String errorMsg = "";
				if (e instanceof DuplicateKeyException) {
					errorMsg = "该菌株信息已经存在";
				} else if (e instanceof DataIntegrityViolationException) {
					errorMsg = "该样品编号不存在";
				} else if (e instanceof CannotCreateTransactionException 
						|| e instanceof DataAccessResourceFailureException) {
					errorMsg = "数据库服务异常,请重新添加";
				} else {
					errorMsg = "未知异常";
				}
				PStrainCoding pstrain = Transfer.changetoPageModel(strain);
				pstrain.setOtherMsg(errorMsg);
				errorList.add(pstrain);
				log.error("【导入菌株信息异常】："+pstrain+ e +"【"+user+"】");
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
		List<StrainCoding> strains = strainDAO.selectByNos(strainnos);
		
		List<Map<String, Object>> bodyList = this.retransfer(strains);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(StrainExcel.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "菌株信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}
	
	private List<Map<String, Object>> retransfer(List<StrainCoding> strains) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StrainCoding strain : strains) {
			Map<String, Object> map = new HashMap<String, Object>();
			PStrainCoding pstrain = Transfer.changetoPageModel(strain);
			map.put(StrainExcel.STRAINNO, pstrain.getStrainno());
			map.put(StrainExcel.SAMPLENO, pstrain.getSampleno());
			map.put(StrainExcel.STRAINALIAS, pstrain.getStrainalias());
			map.put(StrainExcel.STRAINCATEGORY, pstrain.getStraincategory());
			map.put(StrainExcel.STRAINTYPE, pstrain.getStraintype());
			map.put(StrainExcel.STRAINSTORAGEDATE, pstrain.getStrainstoragedateStr());
			map.put(StrainExcel.SEROTYPE, pstrain.getSerotype());
			map.put(StrainExcel.STRAINMLST, pstrain.getStrainmlst());
			map.put(StrainExcel.STRAINPLG, pstrain.getStrainplg());
			map.put(StrainExcel.OPERATOR, pstrain.getOperator());
			map.put(StrainExcel.STRAINPARTER, pstrain.getStrainparter());
			map.put(StrainExcel.FARMNAME, pstrain.getFarmName());
			map.put(StrainExcel.SAMPLEFARMADDR, pstrain.getSamplefarmaddr());
			map.put(StrainExcel.SAMPLEANIMALAGE, pstrain.getSampleanimalage());
			map.put(StrainExcel.SAMPLESOURCE, pstrain.getSamplesource());
			map.put(StrainExcel.PARTNAME, pstrain.getPartName());
			map.put(StrainExcel.SAMPLECOLLECTOR, pstrain.getSamplecollector());
			map.put(StrainExcel.SAMPLEMEDICALHISTORY, pstrain.getSamplemedicalhistory());
			map.put(StrainExcel.STRAINREMARKS, pstrain.getStrainremarks());
			list.add(map);
		}
		return list;
	}

	// 将execl文件中的信息转化为待导入的菌株信息页面模型集合
	private List<PStrainCoding> transferCoding(List<Map<String, Object>> bodyList,
			String user) {
		List<PStrainCoding> stranlist = new ArrayList<PStrainCoding>();
		PStrainCoding strain = null;
		for (Map<String,Object> map : bodyList) {
			strain = new PStrainCoding();
			strain.setSampleno(String.valueOf(map.get(StrainExcel.SAMPLENO)));
			strain.setStrainalias(String.valueOf(map.get(StrainExcel.STRAINALIAS)));
			strain.setStraincategory(String.valueOf(map.get(StrainExcel.STRAINCATEGORY)));
			strain.setStraintype(String.valueOf(map.get(StrainExcel.STRAINTYPE)));
			strain.setStrainstoragedateStr(String.valueOf(map.get(StrainExcel.STRAINSTORAGEDATE)));
			strain.setOperator(String.valueOf(map.get(StrainExcel.OPERATOR)));
			strain.setOtherMsg(user);
			stranlist.add(strain);
		}
		return stranlist;
	}

	// 检查菌株属性
	private List<PStrainCoding> checkCodingAttrs(List<StrainCoding> addList,
			List<PStrainCoding> pageList) {
		List<PStrainCoding> errorList = new ArrayList<PStrainCoding>();
		List<StrainToNotes> notes = notesDAO.selectAll();
		for (PStrainCoding pstrain : pageList) {
			// 判断菌株编号
			Boolean flag = false;
			for (StrainToNotes strainToNotes : notes) {
				if (strainToNotes.getStrainname().equals(pstrain.getStraintype()) 
						&& strainToNotes.getStraincategory().equals(pstrain.getStraincategory())) {
					flag = true;
					pstrain.setStrainno(pstrain.getSampleno() + strainToNotes.getStrainnotes());
					pstrain.setGramstain(strainToNotes.getGramstain());
					break;
				}
			}
			if (!flag) {
				pstrain.setOtherMsg("["+pstrain.getStraincategory()+"- "+pstrain.getStraintype() + "]"
						+ "对应的菌种编号不存在,请在[编号管理-菌种编号]页面添加");
				errorList.add(pstrain);
				continue;
			} 
			
			// 判断菌株重复
			flag = false;
			String strainNo = pstrain.getStrainno();
			String strainalias = pstrain.getStrainalias();
			List<String> list = strainDAO.selectStrainNoByAliasOrNo(strainNo, strainalias);
			if (list != null && list.size() > 0) {
				String no = list.get(0);
				if (no.equals(strainNo)) {
					pstrain.setOtherMsg("已存在样品编号["+pstrain.getSampleno() + "]["+pstrain.getStraincategory() + "-"+pstrain.getStraintype() +"]"
							+ "对应的菌株信息");
				} else {
					pstrain.setOtherMsg("已存在内部编号["+strainalias+"]对应的菌株信息");
				}
				errorList.add(pstrain);
			} else {
				// 日期格式
				try {
					addList.add(Transfer.changeToEntity(pstrain));
				} catch (Exception e) {
					e.printStackTrace();
					pstrain.setOtherMsg(e.getMessage());
					errorList.add(pstrain);
				}
			}
		}
		return errorList;
	}

	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}

	public void setNotesDAO(StrainToNotesDAO notesDAO) {
		this.notesDAO = notesDAO;
	}
}
