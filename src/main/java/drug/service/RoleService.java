package drug.service;

import java.util.List;

import drug.dto.pageModel.PRole;
import drug.dto.pageModel.PageResultModel;
import drug.model.Role;

public interface RoleService {
	
	/**
	 * 新增角色信息
	 * @param prole
	 */
	public void save(PRole prole);
	
	/**
	 * 更新角色信息
	 * @param prole
	 */
	public void update(PRole prole);
	
	/**
	 * 删除角色
	 * @param roleno
	 * @return 
	 */
	public int delete(String roleno);
	
	/**
	 * 获取角色集合(下拉框)
	 * @return
	 */
	public List<Role> getRoles();
	
	/**
	 * 查询角色列表
	 * @param prole
	 * @return
	 */
	public PageResultModel<PRole> list(PRole prole);
	
}
