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
import drug.dto.pageModel.PStrainToNotes;
import drug.dto.pageModel.PageResultModel;
import drug.service.StrainToNotesService;

@Controller
@RequestMapping("strainToNotes")
public class StrainToNotesAction extends BaseAction {
	
	@Autowired
	private StrainToNotesService strainService;
	public void setStrainService(StrainToNotesService strainService) {
		this.strainService = strainService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PStrainToNotes strain) {
		try {
			strainService.save(strain);
			log.info("【菌株编号信息添加成功】："+strain+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新的菌株编号信息添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				strain.setErrorMsg("该菌种代号已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				strain.setErrorMsg("该菌株编号信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				strain.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				strain.setErrorMsg("新增菌株编号信息时发生未知异常,请联系维护人员");
			}
			log.info("【添加菌株编号信息时异常】："+strain+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, strain.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PStrainToNotes strain) {
		try {
			strainService.update(strain);
			log.info("【采集菌株编号信息修改成功】："+strain+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "菌株编号信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	strain.setErrorMsg("菌株编号信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				strain.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				strain.setErrorMsg("修改菌株编号信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改菌株编号信息异常】："+strain+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, strain.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PStrainToNotes strain) {
		String notes = strain.getStrainnotes();
		String errorMsg = "";
		try {
			int deleteNum = strainService.delete(notes);
			log.info("【菌株编号信息删除成功】："+notes+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条菌株编号信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该菌株编号信息无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新修改";
			} else {
				errorMsg = "删除菌株编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【删除采集部位编号信息异常】："+notes + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false)  PStrainToNotes strain) {
		String errorMsg = "";
		try {
			PageResultModel<PStrainToNotes> resultModel = strainService.list(strain);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询菌株编号信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询菌株编号信息时异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/category/selection", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getCategory(@RequestBody(required=false) PStrainToNotes strain) {
		String gramstrain = "";
		if (strain != null && strain.getGramstain() != null) {
			gramstrain = strain.getGramstain().trim();
		}
		String errorMsg = "";
		try {
			List<String> categorys = strainService.getCategory(gramstrain);
			result = new AjaxResult(true, categorys);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取菌株信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取菌株信息时异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/strain/selection", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult getStrains(@RequestBody(required=false) PStrainToNotes strain) {
		String category = "";
		if (strain != null && strain.getStraincategory() != null) {
			category = strain.getStraincategory().trim();
		}
		String errorMsg = "";
		try {
			List<String> strains = strainService.getStrains(category);
			result = new AjaxResult(true, strains);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取菌种信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取菌种信息时异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getStrains() {
		String errorMsg = "";
		try {
			List<String> strains = strainService.getCategoryAndStrains();
			result = new AjaxResult(true, strains);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取菌株+菌种信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取菌株+菌种信息时异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
