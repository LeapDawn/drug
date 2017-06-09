package drug.action;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import drug.dto.AjaxResult;
import drug.dto.UsersFunction;

/**
 * 基础Action
 */
@CrossOrigin
public class BaseAction {
	
	public static Logger log = Logger.getLogger("R");
	
	protected AjaxResult result;
	
	protected UsersFunction user = new UsersFunction();
	public void setUser(UsersFunction user) {
		this.user = user;
	}
}
