package drug.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
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
import org.springframework.web.multipart.MultipartFile;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.dto.AjaxResult;
import drug.dto.listModel.LSample;
import drug.dto.pageModel.UploadResultModel;
import drug.dto.pageModel.PSample;
import drug.dto.pageModel.PageResultModel;
import drug.service.SampleService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/sample")
public class SampleAction extends BaseAction {

	@Autowired
	private SampleService sampleService;
	@Resource(name = "sampleUpDown")
	private UpDownService updownService;
	public void setSampleUpDownService(UpDownService updownService) {
		this.updownService = updownService;
	}
	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}
	
	
	@RequestMapping("/addition")
	@ResponseBody
	public AjaxResult add(@RequestBody PSample sample) {
		sample.setOtherMsg(user.getUsername());
		try {
			sampleService.save(sample);
			log.info("【样品信息添加成功】："+sample+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "样品信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				sample.setOtherMsg("添加失败(样品编号重复),请重新添加");
			} else if (e instanceof DataViolationException) {
					sample.setOtherMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
					sample.setOtherMsg("添加失败,该样品信息记录存在不合法信息");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				sample.setOtherMsg("数据库服务异常,请重新添加");
			} else {
				sample.setOtherMsg("新增样品信息时发生未知异常,请联系维护人员");
			}
			log.error("【添加样品信息异常】："+sample+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, sample.getOtherMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PSample sample) {
		try {
			sampleService.update(sample);
			log.info("【样品信息修改成功】："+sample+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "样品信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				sample.setOtherMsg(e.getMessage());
			} else  if (e instanceof DataIntegrityViolationException) {
		    	sample.setOtherMsg("该样品信息记录存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				sample.setOtherMsg("数据库服务异常,请重新修改");
			} else {
				sample.setOtherMsg("修改样品信息时发生未知异常,请联系维护人员");
			}
			log.error("【修改样品信息异常】："+sample+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, sample.getOtherMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PSample sample) {
		String errorMsg = "";
		String samplenos = sample.getSampleno();
		try {
			int deleteNum = sampleService.delete(samplenos);
			log.info("【样品信息删除成功】："+samplenos+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条样品信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该样品信息已被引用,无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除样品信息时发生未知异常,请联系维护人员";
			}
			log.error("【删除样品信息异常】："+samplenos + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getSampleNos() {
		String errorMsg = "";
		try {
			List<String> sampleNos = sampleService.getSampleNos();
			result = new AjaxResult(true, sampleNos);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取样品编号信息时发生未知异常,请联系维护人员";
			}
			log.error("【获取样品编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) LSample sample) {
		String errorMsg = "";
		try {
			PageResultModel<PSample> resultModel = sampleService.list(sample);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询样品信息列表时发生未知异常,请联系维护人员";
			}
			log.error("【查询样品信息列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult upload(@RequestParam("file") MultipartFile file){
		if (file == null) {
			return new AjaxResult(false, "上传的文件为空");
		}
		UploadResultModel resultModel = null;
		
		// 导入功能的日志记录下放至service层
		try {
			resultModel = updownService.importDatas(file.getInputStream(), user.getUsername());
		} catch (IOException e) {
			return new AjaxResult(false, "获取上传的文件失败");
		} catch (ExcelException e) {
			return new AjaxResult(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(false, "未知异常");
		}
		
		// 是否全部导入成功
		if (resultModel.getError() > 0) {
			result = new AjaxResult(false, resultModel);
		} else {
			result = new AjaxResult(true, "成功导入" + resultModel.getSuccess() + "条样品记录");
		}
		return result;
	}
	
	@RequestMapping(value="/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("sampleno") String sampleno){
		String root = System.getProperty("drugPath");
		File file = new File(root + "samples" + System.currentTimeMillis() + ".xls");
		Object retObj = null;
		try {
			file = updownService.exportDatas(sampleno, file);
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentDispositionFormData("attachment","samples.xls");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	        retObj = responseEntity;
		} catch (Exception e) {
			String errorMsg = "";
			if (e instanceof ExcelException) {
				e.printStackTrace();
				errorMsg = "生成样品execl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出样品时发生未知异常,请联系维护人员";
			}
			log.error("【导出样品异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
}
