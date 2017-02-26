package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import drug.commons.exception.ExeclException;
import drug.commons.execlModel.StrainExecl;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dao.StrainCodingDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainCoding;
import drug.model.StrainCoding;
import drug.service.UpDownService;

//分型信息
@Service("strainUpDown")
public class StrainUpDownServiceImpl implements UpDownService {
public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private StrainCodingDAO strainDAO;


	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}


	/**
	 * 导入分型信息(更新操作)
	 * @return
	 * @throws ExeclException
	 */
	@Override
	public ImportResultModel importDatas(InputStream input, String user) throws ExeclException{
		String[] importColumns = StrainExecl.getStrainColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExeclException e) {
			throw e;
		}
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<PStrainCoding> pageList = this.transferStrain(bodyList, user);
		List<StrainCoding> addList = new ArrayList<StrainCoding>();
		List<PStrainCoding> errorList = CheckStrainAttrs(addList, pageList);
		for (StrainCoding strain : addList) {
			try {
				strain.setAdduser(user);
				strainDAO.update(strain);
				log.info("【分型信息导入成功】："+ strain+"【"+user+"】");
			} catch (Exception e) {
				e.printStackTrace();
				String errorMsg = "";
			    if (e instanceof DataIntegrityViolationException) {
					errorMsg = "存在不合法信息(某些字段过长)";
				} else if (e instanceof CannotCreateTransactionException 
						|| e instanceof DataAccessResourceFailureException) {
					errorMsg = "数据库服务异常,请重新添加";
				} else {
					errorMsg = "未知异常";
				}
			    PStrainCoding pstrain = Transfer.changetoPageModel(strain);
				pstrain.setOtherMsg(errorMsg);
				errorList.add(pstrain);
				log.error("【导入分型信息异常】："+pstrain+ e +"【"+user+"】");
			}
		}
		ImportResultModel result = new ImportResultModel(errorList, bodyList.size());
		return result;
	}

	// 将execl文件中的信息转化为待导入的分型信息页面模型集合
	private List<PStrainCoding> transferStrain(List<Map<String, Object>> bodyList,
			String user) {
		List<PStrainCoding> stranlist = new ArrayList<PStrainCoding>();
		PStrainCoding strain = null;
		for (Map<String,Object> map : bodyList) {
			strain = new PStrainCoding();
			strain.setStrainno(String.valueOf(map.get(StrainExecl.STRAINNO)));
			strain.setSerotype(String.valueOf(map.get(StrainExecl.SEROTYPE)));
			strain.setStrainmlst(String.valueOf(map.get(StrainExecl.STRAINMLST)));
			strain.setStrainplg(String.valueOf(map.get(StrainExecl.STRAINPLG)));
			strain.setStrainparter(String.valueOf(map.get(StrainExecl.STRAINPARTER)));
			strain.setStrainremarks(String.valueOf(map.get(StrainExecl.STRAINREMARKS)));
			strain.setOtherMsg(user);
			stranlist.add(strain);
		}
		return stranlist;
	}
	
	// 检查细菌分型信息
		private List<PStrainCoding> CheckStrainAttrs(List<StrainCoding> addList,
				List<PStrainCoding> pageList) {
			List<PStrainCoding> errorList = new ArrayList<PStrainCoding>();
			for (PStrainCoding pstrain : pageList) {
				String strainno = pstrain.getStrainno();
				if(strainDAO.select(strainno) == null){
					pstrain.setOtherMsg("菌株编号[" + strainno + "]不存在");;
					errorList.add(pstrain);
				} else {
					addList.add(Transfer.changeToEntity(pstrain));
				}
			}
			return errorList;
		}

	@Override
	public File exportDatas(String ids, File file) throws ExeclException {
		return null;
	}
	
}
