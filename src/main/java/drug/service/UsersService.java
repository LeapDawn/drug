package drug.service;

import drug.dto.pageModel.PUsers;
import drug.dto.pageModel.PageResultModel;
import drug.model.Users;

public interface UsersService {
	
	/**
	 * 新增用户
	 * @param user
	 */
	public void save(PUsers user);

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void update(PUsers user);

	/**
	 * 删除用户
	 * @param userName
	 */
	public void delete(String userName);

	/**
	 * 获取用户列表
	 * @param user
	 * @return 
	 */
	public PageResultModel<PUsers> list(PUsers user);

	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public Users login(String userName, String password);
}
