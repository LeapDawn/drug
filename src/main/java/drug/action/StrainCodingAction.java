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
import drug.commons.exception.ExeclException;
import drug.dto.AjaxResult;
import drug.dto.listModel.LStrainCoding;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainCoding;
import drug.dto.pageModel.PageResultModel;
import drug.service.StrainCodingService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/strain")
public class StrainCodingAction extends BaseAction{

	@Autowired
	private StrainCodingService strainService;
	@Resource(name="codingUpDown")
	private UpDownService codingUpDownService;
	@Resource(name="strainUpDown")
	private UpDownService strainUpDownService;
	public void setStrainService(StrainCodingService strainService) {
		this.strainService = strainService;
	}
	public void setCodingUpDownService(UpDownService codingUpDownService) {
		this.codingUpDownService = codingUpDownService;
	}
	public void setStrainUpDownService(UpDownService strainUpDownService) {
		this.strainUpDownService = strainUpDownService;
	}

	@RequestMapping("/addition")
	@ResponseBody
	public AjaxResult add(@RequestBody PStrainCoding strain) {
		strain.setOtherMsg(user.getUsername());
		try {
			strainService.save(strain);
			log.info("【菌株基本信息添加成功】："+strain+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "菌株基本信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				strain.setOtherMsg("该菌株信息已经存在");
			} else if (e instanceof DataViolationException) {
				strain.setOtherMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				strain.setOtherMsg("添加失败,该菌株基本信息记录存在不合法信息");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				strain.setOtherMsg("数据库服务异常,请重新添加");
			} else {
				strain.setOtherMsg("新增菌株基本信息时发生未知异常,请联系维护人员");
			}
			log.info("【添加菌株基本信息异常】："+strain+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, strain.getOtherMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PStrainCoding strain) {
		try {
			strainService.update(strain);
			log.info("【菌株基本信息修改成功】："+strain+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "菌株基本信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				strain.setOtherMsg(e.getMessage());
			}  else if (e instanceof DataIntegrityViolationException) {
		    	strain.setOtherMsg("该菌株基本信息记录存在不合法信息,请重新修改");
			}  else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				strain.setOtherMsg("数据库服务异常,请重新修改");
			} else {
				strain.setOtherMsg("修改菌株基本信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改菌株基本信息异常】："+strain+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, strain.getOtherMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PStrainCoding strain) {
		String errorMsg = "";
		String strainnos = strain.getStrainno();
		try {
			int deleteNum = strainService.delete(strainnos);
			log.info("【菌株基本信息删除成功】："+strainnos+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条菌株基本信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该菌株基本信息已被引用,无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除菌株基本信息时发生未知异常,请联系维护人员";
			}
			log.info("【删除菌株基本信息异常】："+strainnos + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/getting", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult get(@RequestParam(value="strainno") String strainNo) {
		String errorMsg = "";
		try {
			PStrainCoding pstrain = strainService.selectOne(strainNo);
			if (pstrain != null) {
				result = new AjaxResult(true, pstrain);
			} else {
				result = new AjaxResult(false, "不存在菌株编号[" + strainNo + "]的菌株信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询指定菌株基本信息列表时发生未知异常,请联系维护人员";
			}
			log.info("【查询指定菌株基本信息列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) LStrainCoding strain) {
		String errorMsg = "";
		try {
			PageResultModel<PStrainCoding> resultModel = strainService.list(strain);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询菌株基本信息列表时发生未知异常,请联系维护人员";
			}
			log.info("【查询菌株基本信息列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection/mic", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getStrainNosNoInMic(@RequestBody(required=false) PStrainCoding strain) {
		String gramstrain = "";
		if (strain != null && strain.getGramstain() != null) {
			gramstrain = strain.getGramstain().trim();
		}
		String errorMsg = "";
		try {
			List<String> strainNos = strainService.getStringNoNotInMic(gramstrain);
			result = new AjaxResult(true, strainNos);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取菌株编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取菌株编号信息(MIC)异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection/character", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getStrainNosNoInCharacter() {
		String errorMsg = "";
		try {
			List<String> strainNos = strainService.getStringNoNotInCharacter();
			result = new AjaxResult(true, strainNos);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取菌株编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取菌株编号信息(character)异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value="/coding/upload", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadCoding(@RequestParam("file") MultipartFile file){
		if (file == null) {
			return new AjaxResult(false, "上传的文件为空");
		}
		ImportResultModel resultModel = null;
		
		// 导入功能的日志记录下放至service层
		try {
			resultModel = codingUpDownService.importDatas(file.getInputStream(), user.getUsername());
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
			result = new AjaxResult(true, "成功导入" + resultModel.getSuccess() + "条菌株记录");
		}
		return result;
	}
	
	@RequestMapping(value="/strain/upload", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadStrain(@RequestParam("file") MultipartFile file){
		if (file == null) {
			return new AjaxResult(false, "上传的文件为空");
		}
		ImportResultModel resultModel = null;
		
		// 导入功能的日志记录下放至service层
		try {
			resultModel = strainUpDownService.importDatas(file.getInputStream(), user.getUsername());
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
			result = new AjaxResult(true, "成功导入" + resultModel.getSuccess() + "条分型记录");
		}
		return result;
	}
	
	@RequestMapping(value="/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("strainno") String strainno){
		String root = System.getProperty("drugPath");
		File file = new File(root + "strains" + System.currentTimeMillis() + ".xls");
		Object retObj = null;
		try {
			file = codingUpDownService.exportDatas(strainno, file);
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentDispositionFormData("attachment","strains.xls");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	        retObj = responseEntity;
		} catch (Exception e) {
			String errorMsg = "";
			if (e instanceof ExeclException) {
				e.printStackTrace();
				errorMsg = "生成菌株execl文件失败";
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
