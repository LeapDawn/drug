package drug.service;

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

import drug.dto.pageModel.PPart;
import drug.dto.pageModel.PageResultModel;
import drug.model.CollectionPart;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class PartServiceTest {

	@Autowired
	private PartService partService;
	public void setPartService(PartService partService) {
		this.partService = partService;
	}

	@Test
	public void save() {
		PPart ppart = new PPart();
		ppart.setPartno("998");
		ppart.setPartname("janviercheng");
//		ppart.setPartremarks(" 备注");
		try {
			partService.save(ppart);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该采集部位编号已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性");
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
		PPart ppart = new PPart();
		ppart.setPartno("998");
		ppart.setPartname("janviercg");
		ppart.setPartremarks(null);
		try {
			partService.update(ppart);
		} catch (Exception e) {
			e.printStackTrace();
		    if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
	}

	@Test
	public void list() {
		PPart ppart = new PPart();
		ppart.setPage(3);
		ppart.setRows(3);
		PageResultModel<PPart> model = null;
		try {
			model = partService.list(ppart);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
		for (PPart part : model.getData()) {
			System.out.println(part);
		}
	}

	@Test
	public void delete() {
		String deletenos = "998,999";
		try {
			partService.delete(deletenos);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			}  else {
				System.out.println("未知异常");
			}
		}
	}

	@Test
	public void getAnimals() {
		try {
			List<CollectionPart> parts = partService.getParts();
			for (CollectionPart part : parts) {
				System.out.println(part);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
	}
}
