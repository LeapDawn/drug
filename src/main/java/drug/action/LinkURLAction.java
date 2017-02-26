package drug.action;

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
import drug.dto.pageModel.PLinkURL;
import drug.dto.pageModel.PageResultModel;
import drug.model.LinkURL;
import drug.service.LinkURLService;

@Controller
@RequestMapping("linkurl")
public class LinkURLAction extends BaseAction {
	
	@Autowired
	private LinkURLService linkURLService;
	public void setLinkURLService(LinkURLService linkURLService) {
		this.linkURLService = linkURLService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PLinkURL linkURL) {
		String errorMsg = "";
		try {
			linkURLService.save(linkURL);
			log.info("【链接添加成功】："+linkURL+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新链接信息【"+linkURL.getName()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				errorMsg = "该链接信息已经存在";
			} else if (e instanceof DataIntegrityViolationException) {
				errorMsg = "链接信息存在不合法信息,请重新添加";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新添加";
			} else {
				errorMsg = "新增链接信息时发生未知异常,请联系维护人员";
			}
			log.info("【添加链接信息异常】："+linkURL+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PLinkURL linkURL) {
		String errorMsg = "";
		try {
			linkURLService.update(linkURL);
			log.info("【链接信息修改成功】："+linkURL+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "链接信息【"+linkURL.getName()+"】修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "链接信息存在不合法信息,请重新修改";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新修改";
			} else {
				errorMsg = "修改链接信息时发生未知异常,请联系维护人员";
			}
			log.info("【修改采链接信息异常】："+linkURL+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PLinkURL linkURL) {
		String names = linkURL.getName();
		String errorMsg = "";
		try {
			int deleteNum = linkURLService.delete(names);
			log.info("【链接信息删除成功】："+names+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除" + deleteNum + "条链接信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该链接信息已被引用,无法被删除";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除链接信息时发生未知异常,请联系维护人员";
			}
			log.info("【删除链接信息异常】：" + names + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PLinkURL linkURL) {
		String errorMsg = "";
		try {
			PageResultModel<LinkURL> resultModel = linkURLService.list(linkURL);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询链接信息时发生未知异常,请联系维护人员";
			}
			log.info("【查询链接信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
}
