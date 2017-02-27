package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drug.commons.util.Transfer;
import drug.dao.RoleDAO;
import drug.dao.RoleFunctionDAO;
import drug.dto.pageModel.PRole;
import drug.dto.pageModel.PageResultModel;
import drug.model.Role;
import drug.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private RoleFunctionDAO rfDAO;
	
	@Override
	@Transactional
	public void save(PRole prole) {
		Role role = Transfer.changeToEntity(prole);
		List<String> functions = prole.getFunctions();
		roleDAO.insert(role);
		if (functions != null && functions.size() > 0) {
			rfDAO.insert(role.getRoleno(), functions);
		}
	}

	@Override
	@Transactional
	public void update(PRole prole) {
		Role role = Transfer.changeToEntity(prole);
		roleDAO.update(role);
		List<String> functions = prole.getFunctions();
		rfDAO.deleteByRole(prole.getRoleno());
		if (functions != null && functions.size() > 0) {
			rfDAO.insert(prole.getRoleno(), functions);
		}
	}

	@Override
	public int delete(String roleno) {
		if (roleno == null || roleno.trim().equals("") || roleno.trim().equals(",")) {
			return 0;
		}
		String[] rolenoArray = roleno.split(",");
		return roleDAO.delete(rolenoArray);
	}

	@Override
	public List<Role> getRoles() {
		return roleDAO.selectAll();
	}

	@Override
	public PageResultModel<PRole> list(PRole prole) {
		if (prole == null) {
			prole = new PRole();
		}
		int total = roleDAO.count();
		PageResultModel<PRole> resultModel = 
				new PageResultModel<PRole>(total, prole.getRows(), prole.getPage());
		prole.setRows(resultModel.getRows());
		prole.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<Role> list = roleDAO.selectList(prole);
		List<PRole> plist = new ArrayList<PRole>();
		for (Role role : list) {
			plist.add(Transfer.changeToPageModel(role, rfDAO.selectByRole(role.getRoleno())));
		}
		resultModel.setData(plist);
		return resultModel;
	}
	
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public RoleFunctionDAO getRfDAO() {
		return rfDAO;
	}

	public void setRfDAO(RoleFunctionDAO rfDAO) {
		this.rfDAO = rfDAO;
	}
}
