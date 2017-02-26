package drug.action;

import org.apache.log4j.Logger;

import drug.dto.AjaxResult;
import drug.model.Users;

public class BaseAction {
	
	public static Logger log = Logger.getLogger("R");
	
	protected AjaxResult result;
	
	// TODO 在登录验证时,获取user并赋值给当前action(实现登录验证时在修正)
	protected Users user = new Users();
	public void setUser(Users user) {
		this.user = user;
	}
}
