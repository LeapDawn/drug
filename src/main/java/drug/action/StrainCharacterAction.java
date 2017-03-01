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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.dto.AjaxResult;
import drug.dto.listModel.LStrainCharacter;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PStrainCharacter;
import drug.dto.pageModel.PageResultModel;
import drug.service.StrainCharacterService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/character")
public class StrainCharacterAction extends BaseAction {

	@Autowired
	private StrainCharacterService characterService;
	@Resource(name="characterUpDown")
	private UpDownService characterUpDownService;
	public void setCharacterService(StrainCharacterService characterService) {
		this.characterService = characterService;
	}
	
	@RequestMapping("/addition")
	@ResponseBody
	public AjaxResult add(@RequestBody PStrainCharacter character) {
		character.setOtherMsg(user.getUsername());
		try {
			characterService.save(character);
			log.info("【耐药性基因信息添加成功】："+character+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "耐药性基因信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				character.setOtherMsg("该耐药性基因信息[" + character.getStrainno()
						+ "-" + character.getGenname() + "]已经存在");
			} else if (e instanceof DataViolationException) {
				character.setOtherMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				character.setOtherMsg("添加失败,该耐药性基因信息记录存在不合法信息");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				character.setOtherMsg("数据库服务异常,请重新添加");
			} else {
				character.setOtherMsg("新增耐药性基因信息时发生未知异常,请联系维护人员");
			}
			log.info("【添加耐药性基因信息异常】："+character+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, character.getOtherMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PStrainCharacter character) {
		try {
			characterService.update(character);
			log.info("【耐药性基因信息修改成功】："+character+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "耐药性基因信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				character.setOtherMsg("该耐药性基因信息[" + character.getStrainno()
						+ "-" + character.getGenname() + "]已经存在");
			} else if (e instanceof DataViolationException) {
				character.setOtherMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				character.setOtherMsg("该耐药性基因信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				character.setOtherMsg("数据库服务异常,请重新修改");
			} else {
				character.setOtherMsg("修改耐药性基因信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改耐药性基因信息异常】："+character+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, character.getOtherMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PStrainCharacter character) {
		String errorMsg = "";
		String ids = character.getOtherMsg();
		try {
			int deleteNum = characterService.delete(ids);
			log.info("【菌株基本信息删除成功】："+ids+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条耐药性基因信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该耐药性基因信息无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除耐药性基因信息时发生未知异常,请联系维护人员";
			}
			log.info("【删除耐药性基因信息异常】："+ ids + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) LStrainCharacter character) {
		String errorMsg = "";
		try {
			PageResultModel<PStrainCharacter> resultModel = characterService.list(character);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询耐药性基因信息列表时发生未知异常,请联系维护人员";
			}
			log.info("【查询耐药性基因信息列表异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadStrain(@RequestParam("file") MultipartFile file){
		if (file == null) {
			return new AjaxResult(false, "上传的文件为空");
		}
		ImportResultModel resultModel = null;
		
		// 导入功能的日志记录下放至service层
		try {
			resultModel = characterUpDownService.importDatas(file.getInputStream(), user.getUsername());
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
			result = new AjaxResult(true, "成功导入" + resultModel.getSuccess() + "条基因记录");
		}
		return result;
	}
	
	@RequestMapping(value="/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("id") String id){
		String root = System.getProperty("drugPath");
		File file = new File(root + "gens" + System.currentTimeMillis() + ".xls");
		Object retObj = null;
		try {
			file = characterUpDownService.exportDatas(id, file);
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentDispositionFormData("attachment","gens.xls");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	        retObj = responseEntity;
		} catch (Exception e) {
			String errorMsg = "";
			if (e instanceof ExcelException) {
				e.printStackTrace();
				errorMsg = "生成基因execl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出基因时发生未知异常,请联系维护人员";
			}
			log.error("【导出基因异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
}
