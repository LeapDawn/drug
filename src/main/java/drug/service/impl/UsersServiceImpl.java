package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.Transfer;
import drug.dao.FunctionDAO;
import drug.dao.UsersDAO;
import drug.dto.UsersFunction;
import drug.dto.pageModel.PUsers;
import drug.dto.pageModel.PageResultModel;
import drug.model.Users;
import drug.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDAO usersDAO;
	@Autowired
	private FunctionDAO functionDAO;
	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}
	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}


	@Override
	public void save(PUsers puser) {
		Users users = new Users();
		BeanUtils.copyProperties(puser, users);
		usersDAO.insert(users);
	}

	@Override
	public void update(PUsers puser) {
		Users users = new Users();
		BeanUtils.copyProperties(puser, users);
		usersDAO.update(users);
	}

	@Override
	public void delete(String userName) {
		if (userName == null || userName.equals("yinxing") || userName.equals("root") || userName.equals("yangxing")) {
			throw new DataViolationException("用户【" + userName + "】不允许被删除");
		}
		usersDAO.delete(userName);
	}

	@Override
	public PageResultModel<PUsers> list(PUsers puser) {
		if (puser == null) {
			puser = new PUsers();
		}
		int total = usersDAO.count();
		PageResultModel<PUsers> resultModel = 
				new PageResultModel<PUsers>(total, puser.getRows(), puser.getPage());
		// 更新rows属性和page属性,此处page属性不再为页码,改为limit的第一个参数(跳过的记录数)
		puser.setRows(resultModel.getRows());
		puser.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<Users> list = usersDAO.selectList(puser);
		List<PUsers> plist = new ArrayList<PUsers>();
		for (Users users : list) {
			plist.add(Transfer.changetoPageModel(users));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	@Override
	public UsersFunction login(String userName, String password) {
		if (userName == null || userName.trim().equals("") 
				|| password == null || password.trim().equals("")) {
			throw new DataViolationException("用户名和密码不能为空");
		}
		
		Users users = usersDAO.selectOne(userName.trim());
		
		if (users == null) {
			throw new DataViolationException("用户名不存在");
		} else if(!users.getPassword().equals(password.trim())){
			throw new DataViolationException("密码错误");
		} else {
			UsersFunction userFunction = new UsersFunction();
			userFunction.setUsername(users.getUsername());
			// 获取用户权限url
			String roleNo = users.getRole();
			if (roleNo == null || roleNo.equals("")) {
				userFunction.setFunctions(new ArrayList<String>());
			} else {
				userFunction.setFunctions(functionDAO.selectURLByRole(roleNo));
			}
			
			return userFunction;
		}
	}
}
