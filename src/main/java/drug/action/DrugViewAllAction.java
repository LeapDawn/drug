package drug.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.dto.AjaxResult;
import drug.dto.analysisModel.ADrugViewAll;
import drug.dto.listModel.LDrugViewAll;
import drug.dto.pageModel.PDrugViewAll;
import drug.dto.pageModel.PageResultModel;
import drug.service.DrugViewAllService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/straincheck")
public class DrugViewAllAction extends BaseAction {
	
	@Autowired
	private DrugViewAllService dvaService;
	@Resource(name="drugViewAllDown")
	private UpDownService updownService;
	public void setUpdownService(UpDownService updownService) {
		this.updownService = updownService;
	}
	public void setDvaService(DrugViewAllService dvaService) {
		this.dvaService = dvaService;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) LDrugViewAll drugViewAll) {
		String errorMsg = "";
		try {
			PageResultModel<PDrugViewAll> resultModel = dvaService.list(drugViewAll);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询检出率分析列表时发生未知异常,请联系维护人员";
			}
			log.info("【查询检出率分析列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/analysis", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult analysis(@RequestBody(required=false) ADrugViewAll drugViewAll) {
		String errorMsg = "";
		try {
			List<?> analysisData = dvaService.getAnalysisData(drugViewAll);
			result = new AjaxResult(true, analysisData);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取检出率分析数据时发生未知异常,请联系维护人员";
			}
			log.info("【获取检出率分析数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value="/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("id") String id){
		String root = System.getProperty("drugPath");
		File file = new File(root + "strainCheck" + System.currentTimeMillis() + ".xls");
		Object retObj = null;
		try {
			file = updownService.exportDatas(id, file);
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentDispositionFormData("attachment","date.xls");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	        retObj = responseEntity;
		} catch (Exception e) {
			String errorMsg = "";
			if (e instanceof ExcelException) {
				e.printStackTrace();
				errorMsg = "生成检出率execl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出检出率数据时发生未知异常,请联系维护人员";
			}
			log.error("【导出检出率数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
}
