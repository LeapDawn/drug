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
import drug.dto.analysisModel.AGenView;
import drug.dto.listModel.LGenView;
import drug.dto.pageModel.PGenView;
import drug.dto.pageModel.PageResultModel;
import drug.service.GenViewService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/genanalysis")
public class GenViewAction extends BaseAction{
	
	@Autowired
	private GenViewService genViewService;
	@Resource(name="genViewDown")
	private UpDownService updownService;
	public void setUpdownService(UpDownService updownService) {
		this.updownService = updownService;
	}
	public void setGenViewService(GenViewService genViewService) {
		this.genViewService = genViewService;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody LGenView genView) {
		String errorMsg = "";
		try {
			PageResultModel<PGenView> resultModel = genViewService.list(genView);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询检出率分析列表时发生未知异常,请联系维护人员";
			}
			log.error("【查询基因检出率分析列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
//	@RequestMapping(value = "/{gram}/analysis", method=RequestMethod.POST)
//	@ResponseBody
//	public AjaxResult list(@RequestBody ADrugView drugView, @PathVariable("gram")String gram) {
//		String errorMsg = "";
//		if (gram.equals("negative")) {
//			drugView.setGram("阴性");
//		} else if(gram.equals("positive")) {
//			drugView.setGram("阳性");
//		} else if(gram.equals("all")) {
//			drugView.setGram("");
//		} else {
//			return new AjaxResult(false, "URL错误");
//		}
//		try {
//			List<?> analysisData = drugViewService.getAnalysisData(drugView);
//			result = new AjaxResult(true, analysisData);
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (e instanceof DataViolationException) {
//				errorMsg = e.getMessage();
//			} else if (e instanceof CannotCreateTransactionException 
//					|| e instanceof DataAccessResourceFailureException) {
//				errorMsg = "数据库服务异常,请重新获取";
//			} else {
//				errorMsg = "获取耐药性分析数据时发生未知异常,请联系维护人员";
//			}
//			log.info("【获取耐药性分析数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
//			result = new AjaxResult(false, errorMsg);
//		}
//		return result;
//	}
	
	@RequestMapping(value="/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("id") String id){
		id = id != null ? id : "";
		String root = System.getProperty("drugPath");
		File file = new File(root + "genCheck" + System.currentTimeMillis() + ".xls");
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
				errorMsg = "生成基因检出率execl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出基因检出率数据时发生未知异常,请联系维护人员";
			}
			log.error("【导出基因检出率数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
	
	@RequestMapping(value = "/analysis", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult analysis(@RequestBody AGenView agenView) {
		String errorMsg = "";
		try {
			List<?> analysisData = genViewService.getAnalysisData(agenView);
			result = new AjaxResult(true, analysisData);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取基因检出率分析数据时发生未知异常,请联系维护人员";
			}
			log.info("【获取基因检出率分析数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/interval/analysis", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult intervalAnalysis(@RequestBody AGenView agenView) {
		String errorMsg = "";
		try {
			List<?> analysisData = genViewService.getIntervalAnalysisData(agenView);
			result = new AjaxResult(true, analysisData);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取基因(区间)检出率分析数据时发生未知异常,请联系维护人员";
			}
			log.info("【获取基因(区间)检出率分析数据异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
