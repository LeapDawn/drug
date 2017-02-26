package drug.action;

import java.io.File;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExeclException;
import drug.dto.AjaxResult;
import drug.dto.listModel.LStrainMic;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainMic;
import drug.dto.pageModel.PageResultModel;
import drug.service.StrainMicService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/mic")
public class StrainMicAction extends BaseAction {

	@Autowired
	private StrainMicService micService;
	@Resource(name="positiveMicUpDown")
	private UpDownService positiveUpDown;
	@Resource(name="negativeMicUpDown")
	private UpDownService negativeUpDown;
	public void setMicService(StrainMicService micService) {
		this.micService = micService;
	}
	public void setPositiveUpDown(UpDownService positiveUpDown) {
		this.positiveUpDown = positiveUpDown;
	}
	public void setNegativeUpDown(UpDownService negativeUpDown) {
		this.negativeUpDown = negativeUpDown;
	}
	
	@RequestMapping("/{gram}/addition")
	@ResponseBody
	public AjaxResult add(@RequestBody PStrainMic strainMic, @PathVariable("gram") String gram) {
		strainMic.setOtherMsg(user.getUsername());
		if (gram.equals("negative")) {
			strainMic.setGramstain("阴性");
		} else if(gram.equals("positive")) {
			strainMic.setGramstain("阳性");
		} else {
			return new AjaxResult(false, "URL错误");
		}
		try {
			micService.save(strainMic);
			log.info("【菌株mic信息添加成功】："+strainMic+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "菌株mic信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				strainMic.setOtherMsg("该菌株MIC信息已经存在");
			} else if (e instanceof DataViolationException) {
				strainMic.setOtherMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				strainMic.setOtherMsg("添加失败,该菌株mic信息记录存在不合法信息");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				strainMic.setOtherMsg("数据库服务异常,请重新添加");
			} else {
				strainMic.setOtherMsg("新增菌株mic信息时发生未知异常,请联系维护人员");
			}
			log.info("【添加菌株mic信息异常】："+strainMic+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, strainMic.getOtherMsg());
		}
		return result;
	}
	
	
	@RequestMapping(value = "/{gram}/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PStrainMic strainMic, @PathVariable("gram") String gram) {
		if (gram.equals("negative")) {
			strainMic.setGramstain("阴性");
		} else if(gram.equals("positive")) {
			strainMic.setGramstain("阳性");
		} else {
			return new AjaxResult(false, "URL错误");
		}
		try {
			micService.update(strainMic);
			log.info("【菌株mic信息修改成功】："+strainMic+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "菌株mic信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				strainMic.setOtherMsg(e.getMessage());
			}  else if (e instanceof DataIntegrityViolationException) {
				strainMic.setOtherMsg("该菌株mic信息记录存在不合法信息,请重新修改");
			}  else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				strainMic.setOtherMsg("数据库服务异常,请重新修改");
			} else {
				strainMic.setOtherMsg("修改菌株mic信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改菌株mic信息异常】："+strainMic+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, strainMic.getOtherMsg());
		}
		return result;
	}
	
	
	@RequestMapping(value = "/{gram}/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PStrainMic strainMic, @PathVariable("gram") String gram) {
		if (gram.equals("negative")) {
			strainMic.setGramstain("阴性");
		} else if(gram.equals("positive")) {
			strainMic.setGramstain("阳性");
		} else {
			return new AjaxResult(false, "URL错误");
		}
		String errorMsg = "";
		String strainnos = strainMic.getStrainno();
		try {
			int deleteNum = micService.delete(strainnos);
			log.info("【菌株mic信息删除成功】："+strainnos+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条菌株mic信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该菌株mic信息无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除菌株mic信息时发生未知异常,请联系维护人员";
			}
			log.info("【删除菌株mic信息异常】："+strainnos + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/{gram}/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) LStrainMic strainMic, @PathVariable("gram") String gram) {
		if (gram.equals("negative")) {
			strainMic.setGram("阴性");
		} else if(gram.equals("positive")) {
			strainMic.setGram("阳性");
		} else {
			return new AjaxResult(false, "URL错误");
		}
		String errorMsg = "";
		try {
			PageResultModel<PStrainMic> resultModel = micService.list(strainMic);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询菌株mic信息列表时发生未知异常,请联系维护人员";
			}
			log.info("【查询菌株mic信息列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value="/{gram}/upload", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadStrain(@RequestParam("file") MultipartFile file, @PathVariable("gram") String gram){
		UpDownService updownService = null;
		if (gram.equals("negative")) {
			updownService = negativeUpDown;
		} else if(gram.equals("positive")) {
			updownService = positiveUpDown;
		} else {
			return new AjaxResult(false, "URL错误");
		}
		if (file == null) {
			return new AjaxResult(false, "上传的文件为空");
		}
		ImportResultModel resultModel = null;
		
		// 导入功能的日志记录下放至service层
		try {
			resultModel = updownService.importDatas(file.getInputStream(), user.getUsername());
		} catch (IOException e) {
			return new AjaxResult(false, "获取上传的文件失败");
		} catch (ExeclException e) {
			return new AjaxResult(false, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(false, "未知异常");
		}
		
		// 是否全部导入成功
		if (resultModel.getError() > 0) {
			result = new AjaxResult(false, resultModel);
		} else {
			result = new AjaxResult(true, "成功导入" + resultModel.getSuccess() + "条MIC记录");
		}
		return result;
	}
	
	@RequestMapping(value="/{gram}/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("strainno") String strainno, @PathVariable("gram") String gram){
		UpDownService updownService = null;
		if (gram.equals("negative")) {
			updownService = negativeUpDown;
		} else if(gram.equals("positive")) {
			updownService = positiveUpDown;
		} else {
			return new AjaxResult(false, "URL错误");
		}
		String root = System.getProperty("drugPath");
		File file = new File(root + "strains" + System.currentTimeMillis() + ".xls");
		Object retObj = null;
		try {
			file = updownService.exportDatas(strainno, file);
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentDispositionFormData("attachment","mic.xls");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	        retObj = responseEntity;
		} catch (Exception e) {
			String errorMsg = "";
			if (e instanceof ExeclException) {
				e.printStackTrace();
				errorMsg = "生成MICexecl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出菌株时发生未知异常,请联系维护人员";
			}
			log.error("【导出菌株异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
}
