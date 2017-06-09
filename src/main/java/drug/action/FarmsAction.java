package drug.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
import drug.dto.listModel.LFarms;
import drug.dto.pageModel.UploadResultModel;
import drug.dto.pageModel.PFarms;
import drug.dto.pageModel.PageResultModel;
import drug.service.FarmsService;
import drug.service.UpDownService;

@Controller
@RequestMapping("/farm")
public class FarmsAction extends BaseAction{
	
	@Autowired
	private FarmsService farmService;
	public void setFarmService(FarmsService farmService) {
		this.farmService = farmService;
	}
	
	@Resource(name="farmsUpDown")
	private UpDownService updownService;
	public void setUpdownService(UpDownService updownService) {
		this.updownService = updownService;
	}

	/**
	 * 新增采样地
	 * @param farm 采样地信息实体
	 * @return 操作结果
	 */
	@RequestMapping(value="/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PFarms farm) {
		farm.setAddtime(new Timestamp(System.currentTimeMillis()));
		farm.setAdduser(user.getUsername());
		try {
			farmService.save(farm);
			log.info("【采样地信息添加成功】："+farm.toString()+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "采样地信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				farm.setErrorMsg("该采样地信息已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				farm.setErrorMsg("采样地信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException || 
					e instanceof DataAccessResourceFailureException) {
				farm.setErrorMsg("数据库服务异常,请重新添加");
			}  else {
				farm.setErrorMsg("新增采样地时发生未知异常,请联系维护人员");
			}
			result = new AjaxResult(false, farm.getErrorMsg());
			log.error("【添加采样地异常】：" + farm + e +"【"+user.getUsername()+"】");
		}
		return result;
	}
	
	/**
	 * 更新采样地信息
	 * @param farm 采样地信息实体
	 * @return 操作结果
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PFarms farm) {
		try {
			farmService.update(farm);
			log.info("【修改采样地信息为】："+farm.toString()+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "采样地信息更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataIntegrityViolationException) {
				farm.setErrorMsg("采样地信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException || 
					e instanceof DataAccessResourceFailureException) {
				farm.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				farm.setErrorMsg("修改时发生未知异常,请联系维护人员");
			}
			result = new AjaxResult(false, farm.getErrorMsg());
			log.error("【修改采样地异常】：" + farm + e +"【"+user.getUsername()+"】");
		}
		return result;
	}
	
	/**
	 * 查询采样地列表
	 * @param farm 采样地信息实体
	 * @return 操作结果
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) LFarms farm) {
		String errorMsg = "";
		try {
			PageResultModel<PFarms> resultModel = farmService.list(farm);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg="数据库服务异常,查询失败";
			} else {
				errorMsg = "查询采样地时发生未知异常,请联系维护人员";
			}
			result = new AjaxResult(false, errorMsg);
			log.error("【查询采样地异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
		}
		return result;
	}
	
	/**
	 * 查询采样地列表
	 * @param farm 采样地信息实体
	 * @return 操作结果
	 */
	@RequestMapping(value="/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PFarms farm) {
		String farmNames = farm.getFarmname();
		String errorMsg = "";
		try {
			int deleteNum = farmService.delete(farmNames);
			result = new AjaxResult(true, "成功删除" + deleteNum + "条采样地信息");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataIntegrityViolationException) {
				errorMsg = "采样地信息已经被引用,无法删除";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg="数据库服务异常,查询失败";
			} else {
				errorMsg="删除采样地时发生未知异常,请联系维护人员";
			}
			result = new AjaxResult(false, errorMsg);
			log.error("【删除采样地异常】：" + "[" + farmNames + "]" + errorMsg + e +"【"+user.getUsername()+"】");
		}
		return result;
	}
	
	/**
	 * 根据省份查询采样地名称
	 * @param farm 采样地信息实体
	 * @return 操作结果
	 */
	@RequestMapping(value="/selection", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult selectNames(@RequestBody PFarms farm) {
		String province = farm.getFarmprovince();
		String errorMsg = "";
		try {
			List<String> list = farmService.getFarmsByProvince(province);
			result = new AjaxResult(true, list);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg="数据库服务异常,查询失败";
			} else {
				errorMsg = "查询采样地时发生未知异常,请联系维护人员";
			}
			result = new AjaxResult(false, errorMsg);
			log.error("【查询采样地异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
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
			return new AjaxResult(false, "未知异常");
		}
		
		// 是否全部导入成功
		if (resultModel.getError() > 0) {
			result = new AjaxResult(false, resultModel);
		} else {
			result = new AjaxResult(true, "成功导入" + resultModel.getSuccess() + "条采样地记录");
		}
		return result;
	}
	
	@RequestMapping(value="/download", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object download(@RequestParam("farmname") String farmname){
		String root = System.getProperty("drugPath");
		File file = new File(root + "farms" + System.currentTimeMillis() + ".xls");
		Object retObj = null;
		try {
			file = updownService.exportDatas(farmname, file);
			HttpHeaders headers = new HttpHeaders();  
	        headers.setContentDispositionFormData("attachment","farms.xls");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
	        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	        retObj = responseEntity;
		} catch (Exception e) {
			String errorMsg = "";
			if (e instanceof ExcelException) {
				e.printStackTrace();
				errorMsg = "生成采样地execl文件失败";
			} else if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof IOException) {
				errorMsg = "下载execl文件失败";
			} else if (e instanceof CannotCreateTransactionException || e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,查询失败";
			} else {
				errorMsg = "导出采样地时发生未知异常,请联系维护人员";
			}
			log.error("【导出采样地异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			retObj = new AjaxResult(false, errorMsg);
		} finally{
			if (file.exists()) {
	        	file.delete();
	        }
		}
		return retObj;
	}
}
