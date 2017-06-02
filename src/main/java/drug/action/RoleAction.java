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
import drug.dto.pageModel.PRole;
import drug.dto.pageModel.PageResultModel;
import drug.model.Role;
import drug.service.RoleService;

@Controller("roleAction")
@RequestMapping("/role")
public class RoleAction extends BaseAction {
	@Autowired
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@RequestMapping(value = "/addition", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult add(@RequestBody PRole role) {
		try {
			roleService.save(role);
			log.info("【新的角色添加成功】："+user+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "新角色【"+role.getRolename()+"】添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "";
			if (e instanceof DuplicateKeyException) {
				errorMsg = "该角色名已经存在";
			} else if (e instanceof DataIntegrityViolationException) {
				errorMsg = "该角色不存在";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新添加";
			} else {
				errorMsg = "新增角色时发生未知异常,请联系维护人员";
			}
			log.error("【添加新的角色信息异常】："+ role + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult update(@RequestBody PRole role) {
		try {
			roleService.update(role);
			log.info("【角色信息修改成功】："+role+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "角色信息【"+role.getRolename()+"】修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			String errorMsg = "";
		    if (e instanceof DataIntegrityViolationException) {
		    	errorMsg = "该角色不存在";
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新修改";
			} else {
				errorMsg = "修改角色信息时发生未知异常,请联系维护人员";
			}
			log.error("【修改角色信息信息异常】："+role+ e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/deletion", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(@RequestBody PRole role) {
		String roleno = role.getRoleno();
		String errorMsg = "";
		try {
			int num = roleService.delete(roleno);
			log.info("【角色信息删除成功】："+roleno+"【"+user.getUsername()+"】");
			result = new AjaxResult(true, "成功删除 "+ num +"条角色信息");
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataViolationException) {
	    		errorMsg = e.getMessage();
			} else if (e instanceof DataIntegrityViolationException) {
				errorMsg = "该角色无法被删除";
			}  else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新删除";
			} else {
				errorMsg = "删除角色名时发生未知异常,请联系维护人员";
			}
			log.error("【删除角色信息异常】："+ roleno + " " + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/selection", method=RequestMethod.GET)
	@ResponseBody
	public AjaxResult getRoles() {
		String errorMsg = "";
		try {
			List<Role> roles = roleService.getRoles();
			result = new AjaxResult(true, roles);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "获取角色信息时发生未知异常,请联系维护人员";
			}
			log.error("【获取角色信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
	
	@RequestMapping(value = "/list", method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult list(@RequestBody(required=false) PRole role) {
		String errorMsg = "";
		try {
			PageResultModel<PRole> resultModel = roleService.list(role);
			result = new AjaxResult(true, resultModel);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				errorMsg = "数据库服务异常,请重新获取";
			} else {
				errorMsg = "查询角色列表时发生未知异常,请联系维护人员";
			}
			log.error("【查询角色列表信息异常】：" + errorMsg + e +"【"+user.getUsername()+"】");
			result = new AjaxResult(false, errorMsg);
		}
		return result;
	}
}
