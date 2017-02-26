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

import drug.commons.exception.DataViolationException;
import drug.dto.AjaxResult;
import drug.dto.pageModel.PSource;
import drug.dto.pageModel.PageResultModel;
import drug.model.Source;
import drug.service.SourceService;

@Controller
@RequestMapping("/source")
public class SourceAction extends BaseAction{
	
	@Autowired
	private SourceService sourceService;
	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PSource source) {
		try {
			sourceService.save(source);
			log.info("【来源信息添加成功】："+source+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新来源【"+source.getSourcename()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				source.setErrorMsg("该来源编号已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				source.setErrorMsg("来源信息存在不合法信息,请重新添加");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				source.setErrorMsg("数据库服务异常,请重新添加");
			} else {
				source.setErrorMsg("新增来源时发生未知异常,请联系维护人员");
			}
			log.error("【添加来源信息异常】："+source+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, source.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PSource source) {
		try {
			sourceService.update(source);
			log.info("【来源信息修改成功】："+source+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "来源【"+source.getSourcename()+"】修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
				source.setErrorMsg("来源信息存在不合法信息,请重新修改");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				source.setErrorMsg("数据库服务异常,请重新修改");
			} else {
				source.setErrorMsg("修改来源时发生未知异常,请联系维护人员");
			}
			log.error("【修改来源信息异常】："+source+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, source.getErrorMsg());
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PSource source) {
		String ids = source.getIds();
		String errorMsg = "";
		try {
			int deleteNum = sourceService.delete(ids);
			log.info("【来源信息删除成功】："+ids+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "来源信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataViolationException) {
	    		errorMsg = e.getMessage();
			} else if (e instanceof DataIntegrityViolationException) {
				errorMsg = "存在来源信息无法被删除";
			}  else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除来源信息时发生未知异常,请联系维护人员";
			}
			log.error("【删除来源信息异常】："+ids + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getSources() {
		String errorMsg = "";
		try {
			List<Source> sources = sourceService.getSources();
			result = new AjaxResult(true, sources);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取来源时发生未知异常,请联系维护人员";
			}
			log.error("【获取来源信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PSource source) {
		String errorMsg = "";
		try {
			PageResultModel<PSource> resultModel = sourceService.list(source);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询来源信息时发生未知异常,请联系维护人员";
			}
			log.error("【查询来源信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
