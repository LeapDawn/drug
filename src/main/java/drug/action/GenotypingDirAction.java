package drug.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import drug.commons.exception.DataViolationException;
import drug.dto.AjaxResult;
import drug.dto.pageModel.PGenotypingDir;
import drug.dto.pageModel.ResultPageModel;
import drug.model.GenotypingDir;
import drug.service.GenotypingDirService;

@Controller
@RequestMapping("/genotypingDir")
public class GenotypingDirAction extends BaseAction {
	
	@Autowired
	private GenotypingDirService gtpdService;
	public void setGtpdService(GenotypingDirService gtpdService) {
		this.gtpdService = gtpdService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PGenotypingDir pgtpd) {
		try {
			gtpdService.save(pgtpd);
			log.info("【基因亚型添加成功】："+pgtpd+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新的基因亚型【"+pgtpd.getGenotyping1() 
					+ "/"+pgtpd.getGenotyping2() + "/"+pgtpd.getGenotyping2() +"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				pgtpd.setErrorMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				pgtpd.setErrorMsg("基因亚型信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				pgtpd.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				pgtpd.setErrorMsg("新增基因亚型时发生未知异常,请联系维护人员");
			}
			log.info("【添加基因亚型信息异常】："+pgtpd+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, pgtpd.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PGenotypingDir pgtpd) {
		try {
			gtpdService.update(pgtpd);
			log.info("【基因亚型信息修改成功】："+pgtpd+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "基因亚型信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				pgtpd.setErrorMsg(e.getMessage());
			} else if (e instanceof DataIntegrityViolationException) {
				pgtpd.setErrorMsg("基因亚型信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				pgtpd.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				pgtpd.setErrorMsg("修改基因亚型信息时发生未知异常,请联系维护人员");
			}
			log.info("【修改基因亚型信息异常】："+pgtpd+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, pgtpd.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PGenotypingDir pgtpd) {
		String ids = pgtpd.getIds();
		String errorMsg = "";
		try {
			int deleteNum = gtpdService.delete(ids);
			log.info("【基因亚型信息删除成功】："+ids+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条基因亚型信息");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该基因亚型无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除基因亚型时发生未知异常,请联系维护人员";
			}
			log.info("【删除基因亚型信息异常】："+ids + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PGenotypingDir pgtpd) {
		String errorMsg = "";
		try {
			ResultPageModel<GenotypingDir> resultModel = gtpdService.list(pgtpd);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询基因亚型信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询基因亚型信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection/genotyping1", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGenotyping1() {
		String errorMsg = "";
		try {
			List<String> list = gtpdService.getGenotyping1();
			result = new AjaxResult(true, list);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取基因亚型信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取基因亚型信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection/genotyping2", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGenotyping2(@RequestParam(value="genotyping1",required=false)String getGenotyping1) {
		String errorMsg = "";
		try {
			List<String> list = gtpdService.getGenotyping2(getGenotyping1);
			result = new AjaxResult(true, list);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取基因亚型信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取基因亚型信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection/genotyping3", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getGenotyping3(@RequestParam(value="genotyping2",required=false)String getGenotyping2) {
		String errorMsg = "";
		try {
			List<String> list = gtpdService.getGenotyping3(getGenotyping2);
			result = new AjaxResult(true, list);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取基因亚型信息时发生未知异常,请联系维护人员";
			}
			log.info("【获取基因亚型信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
