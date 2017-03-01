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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.dto.AjaxResult;
import drug.dto.analysisModel.ADrugView;
import drug.dto.listModel.LDrugView;
import drug.dto.pageModel.PDrugView;
import drug.dto.pageModel.PageResultModel;
import drug.service.DrugViewService;
import drug.service.UpDownService;

@Controller
@RequestMapping("druganalysis")
public class DrugViewAction extends BaseAction{
	
	@Autowired
	private DrugViewService drugViewService;
	@Resource(name="drugViewDown")
	private UpDownService updownService;
	public void setUpdownService(UpDownService updownService) {
		this.updownService = updownService;
	}
	public void setDrugViewService(DrugViewService drugViewService) {
		this.drugViewService = drugViewService;
	}
	
	@RequestMapping(value = "/{gram}/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody LDrugView drugView, @PathVariable("gram")String gram) {
		if (gram.equals("negative")) {
			drugView.setGram("阴性");
		} else if(gram.equals("positive")) {
			drugView.setGram("阳性");
		} else if(gram.equals("all")) {
			drugView.setGram("");
		} else {
			return new AjaxResult(false, "URL错误");
		}
		String errorMsg = "";
		try {
			PageResultModel<PDrugView> resultModel = drugViewService.list(drugView);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询检出率分析列表时发生未知异常,请联系维护人员";
			}
			log.error("【查询检出率分析列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/{gram}/analysis", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody ADrugView drugView, @PathVariable("gram")String gram) {
		String errorMsg = "";
		if (gram.equals("negative")) {
			drugView.setGram("阴性");
		} else if(gram.equals("positive")) {
			drugView.setGram("阳性");
		} else if(gram.equals("all")) {
			drugView.setGram("");
		} else {
			return new AjaxResult(false, "URL错误");
		}
		try {
			List<?> analysisData = drugViewService.getAnalysisData(drugView);
			result = new AjaxResult(true, analysisData);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取耐药性分析数据时发生未知异常,请联系维护人员";
			}
			log.info("【获取耐药性分析数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value="/{gram}/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("id") String id, @PathVariable("gram")String gram){
		id = id != null ? id : "";
		if (gram.equals("negative")) {
			id = "2" + id;
		} else if(gram.equals("positive")) {
			id = "1" + id;
		} else if(gram.equals("all")) {
			id = "0" + id;
		} else {
			return new AjaxResult(false, "URL错误");
		}
		
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
				errorMsg = "生成耐药率execl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出耐药率数据时发生未知异常,请联系维护人员";
			}
			log.error("【导出耐药率数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
}
