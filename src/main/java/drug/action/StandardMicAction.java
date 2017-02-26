package drug.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import drug.dto.AjaxResult;
import drug.dto.pageModel.PStandardMic;
import drug.dto.pageModel.PageResultModel;
import drug.service.StandardMicService;

@Controller
@RequestMapping("/standardmic")
public class StandardMicAction extends BaseAction {
	
	@Autowired
	private StandardMicService standardMicService;
	public void setStandardMicService(StandardMicService standardMicService) {
		this.standardMicService = standardMicService;
	}

	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PStandardMic standardMic) {
		try {
			standardMicService.save(standardMic);
			log.info("【药品标准信息添加成功】："+standardMic+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新的药品标准信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				standardMic.setErrorMsg("该药品标准信息已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				standardMic.setErrorMsg("该药品标准信息信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				standardMic.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				standardMic.setErrorMsg("新增药品标准信息时发生未知异常,请联系维护人员");
			}
			log.info("【添加药品标准信息时异常】："+standardMic+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, standardMic.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PStandardMic standardMic) {
		try {
			standardMicService.update(standardMic);
			log.info("【药品标准信息修改成功】："+standardMic+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "药品标准信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	standardMic.setErrorMsg("该药品标准信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				standardMic.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				standardMic.setErrorMsg("修改药品标准信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改药品标准信息时异常】："+standardMic+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, standardMic.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PStandardMic[] pstandardMics) {
		String errorMsg = "";
		try {
			int deleteNum = standardMicService.delete(pstandardMics);
			log.info("【药品标准信息删除成功】："+pstandardMics+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条药品标准信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该药品标准信息无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除药品标准信息时发生未知异常,请联系维护人员";
			}
			log.info("【删除药品标准信息时异常】："+pstandardMics + " " + errorMsg +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/strain/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getStrains() {
		String errorMsg = "";
		try {
			List<String> strains = standardMicService.getStrains();
			result = new AjaxResult(true, strains);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取菌株时发生未知异常,请联系维护人员";
			}
			log.info("【获取菌株时异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/standard/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getStandards() {
		String errorMsg = "";
		try {
			List<String> standards = standardMicService.getStandards();
			result = new AjaxResult(true, standards);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取标准信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取标准编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/drug/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getDrug() {
		String errorMsg = "";
		try {
			List<String> standards = standardMicService.getDrug();
			result = new AjaxResult(true, standards);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取标准信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取标准编号信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PStandardMic standardMic) {
		String errorMsg = "";
		try {
			PageResultModel<PStandardMic> resultModel = standardMicService.list(standardMic);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询药品标准信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询药品标准信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
