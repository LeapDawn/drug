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

import drug.dto.pageModel.PSource;
import drug.model.Source;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class SourceServiceTest {
	
	@Autowired
	private SourceService sourceService;
	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
	}

	@Test
	public void save() {
		PSource source = new PSource();
		source.setSourceno(999);
		source.setSourcename("janviercheng");
		try {
			sourceService.save(source);
		} catch (Exception e) {
			if (e instanceof DuplicateKeyException) {
				System.out.println("该来源已经存在");
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
		PSource source = new PSource();
		source.setSourceno(999);
		source.setSourcename("janvier");
		try {
			sourceService.update(source);
		} catch (Exception e) {
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
		PSource psource = new PSource();
		psource.setPage(2);
		psource.setRows(4);
		List<PSource> list = null;
		try {
			list = sourceService.list(psource).getData();
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
		for (PSource item : list) {
			System.out.println(item);
		}
	}

	@Test
	public void delete() {
		String delNos = "999,998,997";
		try {
			sourceService.delete(delNos);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性");
			} else if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
	}

	@Test
	public void getSources() {
		List<Source> list = null;
		try {
			list = sourceService.getSources();
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
		
		for (Source source : list) {
			System.out.println(source);
		}
	}
}
