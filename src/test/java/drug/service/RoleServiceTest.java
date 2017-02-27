package drug.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.CannotCreateTransactionException;

import drug.dto.pageModel.PRole;
import drug.dto.pageModel.PageResultModel;
import drug.model.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class RoleServiceTest {
	
	@Autowired
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Test
	public void save() {
		PRole prole = new PRole();
		prole.setRoleno("001");
		prole.setRolename("测试员");
		prole.setRemark("备注");
		List<String> list = new ArrayList<String>();
		list.add("1234");
		list.add("5678");
		prole.setFunctions(list);
		try {
			roleService.save(prole);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该名称ID已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反完整性");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
	}
	
	@Test
	public void update() {
		PRole prole = new PRole();
		prole.setRoleno("001");
		prole.setRolename("测试人员");
		prole.setRemark("备注");
		List<String> list = new ArrayList<String>();
		list.add("1234");
		prole.setFunctions(list);
		try {
			roleService.update(prole);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该名称ID已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反完整性");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
	}
	
	@Test
	public void delete() {
		try {
			roleService.delete("001");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该名称ID已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反完整性");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
	}
	
	@Test
	public void getRoles(){
		List<Role> roles = null;
		try {
			roles = roleService.getRoles();
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
		for (Role role : roles) {
			System.out.println(role);
		}
	}
	
	@Test
	public void list(){
		PRole prole = new PRole();
		prole.setPage(1);
		prole.setRows(3);
		List<PRole> list = null;
		try {
			PageResultModel<PRole> result = roleService.list(prole);
			list = result.getData();
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
		for (PRole role : list) {
			System.out.println(role);
		}
	}
}
