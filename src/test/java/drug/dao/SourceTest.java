package drug.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.pageModel.PSource;
import drug.model.Source;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SourceTest {

	@Autowired
	private SourceDAO sourceDAO;
	public void setSourceDAO(SourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	@Test
	public void delete() {
		Integer[] no = new Integer[]{520,954,321};
		long start = System.currentTimeMillis();
		sourceDAO.delete(no);
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end-start) + "ms");
	}

	@Test
	public void insert() {
		Source source = new Source();
		source.setSourceno((int)(Math.random()*900) + 100);
		source.setSourcename("janvier");
		long start = System.currentTimeMillis();
		sourceDAO.insert(source);
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end-start) + "ms");
	}

	@Test
	public void select() {
		PSource psource = new PSource();
		psource.setPage(2);
		psource.setRows(3);
		long start = System.currentTimeMillis();
		List<Source> list = sourceDAO.select(psource);
		long end = System.currentTimeMillis();
		for (Source source : list) {
			System.out.println(source);
		}
		System.out.println("time:" + (end-start) + "ms");
	}

	@Test
	public void selectAll() {
		long start = System.currentTimeMillis();
		List<Source> list = sourceDAO.selectAll();
		long end = System.currentTimeMillis();
		for (Source source : list) {
			System.out.println(source);
		}
		System.out.println("time:" + (end-start) + "ms");
	}
	
	@Test
	public void update() {
		Source source = new Source();
		source.setSourceno(520);
		source.setSourcename("janviercheng");
		long start = System.currentTimeMillis();
		sourceDAO.update(source);
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end-start) + "ms");
	}

	@Test
	public void count() {
		long start = System.currentTimeMillis();
		int count = sourceDAO.count();
		long end = System.currentTimeMillis();
		System.out.println("count:" + count);
		System.out.println("time:" + (end-start) + "ms");
	}
}
