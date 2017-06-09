package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import drug.commons.excelModel.MicExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dto.pageModel.UploadResultModel;
import drug.dto.pageModel.PStrainMic;
import drug.model.StrainMic;

@Service("positiveMicUpDown")
/**
 * 阳性MIC导入/导出
 */
public class PositiveMicUpDownServiceImpl extends MicUpDownService {

	public static Logger log = Logger.getLogger("R");

	@Override
	public UploadResultModel importDatas(InputStream input, String user)
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
		List<PStrainMic> pageList = this.transferCodingForPositive(bodyList, errorList, user);
		List<StrainMic> addList = new ArrayList<StrainMic>();
		CheckCodingAttrs(addList, errorList, pageList, "阳性");
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
		UploadResultModel result = new UploadResultModel(errorList, bodyList.size());
		return result;
	}

	@Override
	public File exportDatas(String nos, File file) throws ExcelException {
		if (nos == null || nos.trim().equals("") || nos.trim().equals(",")){
			throw new DataViolationException("没有选择导出的菌株信息");
		}
		String[] strainnos = nos.trim().split(",");
		List<StrainMic> strainMics = micDAO.selectByNos(strainnos, "阳性");
		
		List<Map<String, Object>> bodyList = this.retransferForPositive(strainMics);
		
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
}
