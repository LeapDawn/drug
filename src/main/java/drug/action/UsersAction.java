package drug.action;

import javax.servlet.http.HttpSession;

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
import drug.dto.pageModel.PUsers;
import drug.dto.pageModel.PageResultModel;
import drug.model.Users;
import drug.service.UsersService;

@Controller("usersAction")
@RequestMapping("/user")
public class UsersAction extends BaseAction{
	
	@Autowired
	private UsersService usersService;
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PUsers users) {
		try {
			usersService.save(users);
			log.info("【新的用户添加成功】："+user+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新用户【"+users.getUsername()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "";
			if (e instanceof DuplicateKeyException) {
				errorMsg = "该用户名已经存在";
			} else if (e instanceof DataIntegrityViolationException) {
				errorMsg = "该角色不存在";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新添加";
			} else {
				errorMsg = "新增用户时发生未知异常,请联系维护人员";
			}
			log.error("【添加新的用户信息异常】："+ users + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PUsers users) {
		try {
			usersService.update(users);
			log.info("【用户信息修改成功】："+users+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "用户信息【"+users.getUsername()+"】修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "";
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该角色不存在";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新修改";
			} else {
				errorMsg = "修改用户信息时发生未知异常,请联系维护人员";
			}
			log.error("【修改用户信息信息异常】："+users+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PUsers user) {
		String username = user.getUsername();
		String errorMsg = "";
		try {
			usersService.delete(username);
			log.info("【来源信息删除成功】："+username+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除用户【" + username + "】");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataViolationException) {
	    		errorMsg = e.getMessage();
			} else if (e instanceof DataIntegrityViolationException) {
				errorMsg = "该用户名无法被删除";
			}  else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除用户名时发生未知异常,请联系维护人员";
			}
			log.error("【删除用户信息异常】："+username + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false)  PUsers user) {
		String errorMsg = "";
		try {
			PageResultModel<PUsers> resultModel = usersService.list(user);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询用户列表时发生未知异常,请联系维护人员";
			}
			log.error("【查询用户列表信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(@RequestBody PUsers puser, HttpSession httpSession){
		String errorMsg = "";
		try {
			Users user = usersService.login(puser.getUsername(), puser.getPassword());
			httpSession.setAttribute("user", user);
			result = new AjaxResult(true, "");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataViolationException) {
				errorMsg = e.getMessage();
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询用户列表时发生未知异常,请联系维护人员";
			}
			log.error("【用户登录异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
