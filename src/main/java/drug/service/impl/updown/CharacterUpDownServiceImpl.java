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

import drug.commons.excelModel.CharacterExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dao.StrainCharacterDAO;
import drug.dao.StrainCodingDAO;
import drug.dto.pageModel.UploadResultModel;
import drug.dto.pageModel.PStrainCharacter;
import drug.model.StrainCharacter;
import drug.model.StrainCoding;
import drug.service.UpDownService;

@Service("characterUpDown")
/**
 * 基因导入/导出
 */
public class CharacterUpDownServiceImpl implements UpDownService {

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private StrainCharacterDAO characterDAO;
	@Autowired
	private StrainCodingDAO codingDAO;
	public void setCodingDAO(StrainCodingDAO codingDAO) {
		this.codingDAO = codingDAO;
	}
	public void setCharacterDAO(StrainCharacterDAO characterDAO) {
		this.characterDAO = characterDAO;
	}

	@Override
	public UploadResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		String[] importColumns = CharacterExcel.getImportColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExcelException e) {
			throw e;
		}
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<PStrainCharacter> pageList = this.transferCoding(bodyList, user);
		List<StrainCharacter> addList = new ArrayList<StrainCharacter>();
		List<PStrainCharacter> errorList = checkAttrs(addList, pageList);
		for (StrainCharacter character : addList) {
			try {
				character.setAdduser(user);
				characterDAO.insert(character);
				log.info("【基因信息导入成功】："+ character+"【"+user+"】");
			} catch (Exception e) {
				e.printStackTrace();
				String errorMsg = "";
				if (e instanceof DuplicateKeyException) {
					errorMsg = "该基因信息已经存在";
				} else if (e instanceof DataIntegrityViolationException) {
					errorMsg = "该菌种编号不存在";
				} else if (e instanceof CannotCreateTransactionException 
						|| e instanceof DataAccessResourceFailureException) {
					errorMsg = "数据库服务异常,请重新添加";
				} else {
					errorMsg = "未知异常";
				}
				PStrainCharacter pcharacter = Transfer.changetoPageModel(character);
				pcharacter.setOtherMsg(errorMsg);
				errorList.add(pcharacter);
				log.error("【导入基因信息异常】："+pcharacter+ e +"【"+user+"】");
			}
		}
		UploadResultModel result = new UploadResultModel(errorList, bodyList.size());
		return result;
	}

	/**
	 * 导出基因信息
	 */
	@Override
	public File exportDatas(String ids, File file) throws ExcelException {
		if (ids == null || ids.trim().equals("") || ids.trim().equals(",")){
			throw new DataViolationException("没有选择导出的基因信息");
		}
		String[] nos = ids.trim().split(",");
		List<StrainCharacter> characters = characterDAO.selectByIds(nos);
		
		List<Map<String, Object>> bodyList = this.retransfer(characters);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(CharacterExcel.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "基因信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}
	
	private List<PStrainCharacter> checkAttrs(List<StrainCharacter> addList,
			List<PStrainCharacter> pageList) {
		List<PStrainCharacter> errorList = new ArrayList<PStrainCharacter>();
		for (PStrainCharacter pch : pageList) {
			String genalias = pch.getGenalias() == null?"":pch.getGenalias().trim();
			String strainno = pch.getStrainno()== null?"":pch.getStrainno().trim();
			if (genalias.equals("") && strainno.equals("")) {
				pch.setOtherMsg("菌株编号或内部编号不能都为空");
				errorList.add(pch);
				continue;
			}
			
			if (pch.getGenname() == null || "".equals(pch.getGenname().trim())) {
				pch.setOtherMsg("基因名称不能为空");
				errorList.add(pch);
				continue;
			}
			
			List<StrainCoding> strainnoList = codingDAO.selectStrainNoByAliasOrNo(strainno, genalias);
			if (strainnoList == null || strainnoList.size() == 0) {
				pch.setOtherMsg("没有与菌株编号/内部编号对应的菌株信息(菌株不存在)");
				errorList.add(pch);
				continue;
			} else if(strainnoList.size() == 2) {
				pch.setOtherMsg("菌株编号与内部编号对应的菌株信息不一致");
				errorList.add(pch);
				continue;
			} else if(strainnoList.size() > 2){
				pch.setOtherMsg("数据出现错误,请联系维护人员查询");
				errorList.add(pch);
				continue;
			} else {
				pch.setStrainno(strainnoList.get(0).getStrainno());
				addList.add(Transfer.changeToEntity(pch));
			}
		}
		return errorList;
	}
	
	private List<Map<String, Object>> retransfer(List<StrainCharacter> characters) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (StrainCharacter character : characters) {
			Map<String, Object> map = new HashMap<String, Object>();
			PStrainCharacter pcharacter = Transfer.changetoPageModel(character);
			map.put(CharacterExcel.STRAINNO, pcharacter.getStrainno());
			map.put(CharacterExcel.GENALIAS, pcharacter.getGenalias());
			map.put(CharacterExcel.GENNAME, pcharacter.getGenname());
			map.put(CharacterExcel.ISEQ, pcharacter.getIseq());
			map.put(CharacterExcel.REPLICON, pcharacter.getReplicon());
			map.put(CharacterExcel.GENTC, pcharacter.getGentc());
			map.put(CharacterExcel.OPERATOR, pcharacter.getOperator());
			map.put(CharacterExcel.REMARK, pcharacter.getGenremarks());
			list.add(map);
		}
		return list;
	}
	
	// 将execl文件中的信息转化为待导入的菌株信息页面模型集合
	private List<PStrainCharacter> transferCoding(List<Map<String, Object>> bodyList,
			String user) {
		List<PStrainCharacter> characterList = new ArrayList<PStrainCharacter>();
		PStrainCharacter character = null;
		for (Map<String,Object> map : bodyList) {
			character = new PStrainCharacter();
			character.setStrainno(String.valueOf(map.get(CharacterExcel.STRAINNO)));
			character.setGenalias(String.valueOf(map.get(CharacterExcel.GENALIAS)));
			character.setGenname(String.valueOf(map.get(CharacterExcel.GENNAME)));
			character.setIseq(String.valueOf(map.get(CharacterExcel.ISEQ)));
			character.setReplicon(String.valueOf(map.get(CharacterExcel.REPLICON)));
			character.setGentc(String.valueOf(map.get(CharacterExcel.GENTC)));
			character.setOperator(String.valueOf(map.get(CharacterExcel.OPERATOR)));
			character.setOtherMsg(user);
			characterList.add(character);
		}
		return characterList;
	}
}
