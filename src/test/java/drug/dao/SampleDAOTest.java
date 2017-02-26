package drug.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.listModel.LSample;
import drug.dto.pageModel.PSample;
import drug.model.Sample;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SampleDAOTest {
	
	@Autowired
	private SampleDAO sampleDAO;
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	
	
	@Test
	public void count() throws ParseException{
		LSample sample = new LSample();
		sample.setAnimalno(new Integer[]{45,20});
//		sample.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-01"));
//		sample.setEndDate(new Date());
		int count = sampleDAO.count(sample);
		System.out.println(count);
	}
	
	@Test
	public void selectList() throws ParseException {
		LSample lsample = new LSample();
		lsample.setAnimalno(new Integer[]{45,20});
//		lsample.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse("2013-01-01"));
//		lsample.setEndDate(new Date());
		lsample.setBeginDate("2013-01-01");
		lsample.setEndDate("2016-12-31");
		lsample.setRows(10);
		lsample.setPage(10);
		List<Sample> list = sampleDAO.selectList(lsample);
		System.out.println(list.size());
		System.out.println(list.get(0).getSampledate());
		Sample sample = list.get(0);
		PSample psample = new PSample();
		BeanUtils.copyProperties(lsample, psample);
		Date date = sample.getSampledate();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		psample.setSampledateStr(dateStr);
		System.out.println(psample);
	}

	@Test
	public void selectMaxNo(){
		String maxNo = sampleDAO.selectMaxNo("1251160");
		System.out.println(maxNo);
	}
	
	@Test
	public void test() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(new Integer(250));
		list.add(300);
		list.add(20000);
		list.add(new Integer(30000));
		System.out.println(list.contains(250));
		System.out.println(list.contains(300));
		System.out.println(list.contains(new Integer(250)));
		System.out.println(list.contains(new Integer(300)));
		System.out.println(list.contains(new Integer(20000)));
		System.out.println(list.contains(new Integer(30000)));
	}
	
	@Test
	public void select() {
		Sample select = sampleDAO.select("1251121001");
		System.out.println(select);
	}
}
