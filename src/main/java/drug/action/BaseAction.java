package drug.action;

import org.apache.log4j.Logger;

import drug.dto.AjaxResult;
import drug.dto.UsersFunction;

public class BaseAction {
	
	public static Logger log = Logger.getLogger("R");
	
	protected AjaxResult result;
	
	protected UsersFunction user = new UsersFunction();
	public void setUser(UsersFunction user) {
		this.user = user;
	}
}
