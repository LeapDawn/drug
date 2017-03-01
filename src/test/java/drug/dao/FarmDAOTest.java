package drug.dao;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import drug.dto.analysisModel.ADrugView;
import drug.dto.listModel.LFarms;
import drug.model.Farms;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class FarmDAOTest {
	
	@Resource
	private FarmsDAO farmDAO;
	public void setFarmDAO(FarmsDAO farmDAO) {
		this.farmDAO = farmDAO;
	}

	@Test
	public void delete() {
		String[] farmName = new String[]{"一个测试","第二个"};
		long start = System.currentTimeMillis();
		farmDAO.delete(farmName);
		long end = System.currentTimeMillis();
		System.out.println("运行时间" + (end-start) + "ms");
	}
	
	@Test
	public void insert() {
		long start = System.currentTimeMillis();
		Farms farm = new Farms();
		farm.setFarmname("测试用例");
		farm.setFarmaddress("新地址");
		farm.setFarmprovince("广东");
		farm.setFarmbuilddate("1999-09-09");
		farm.setFarmlinkman("LJB");
		farm.setFarmphone("000000");
		farm.setFarmraisetype("公司");
		farm.setAdduser("测试人员");
		farm.setAddtime(new Timestamp(System.currentTimeMillis()));
		farmDAO.insert(farm);
		long end = System.currentTimeMillis();
		System.out.println((end-start) + "ms");
	}
	
	@Test
	public void update() {
		Farms farm = new Farms();
		farm.setFarmname("测试用例");
		farm.setFarmaddress("新地址");
		farm.setFarmprovince("广东");
		farm.setFarmbuilddate("2010-10-19");
		farm.setFarmlinkman("LJB");
		farm.setFarmphone("");
		farm.setFarmraisetype("公司+农户");
		farm.setAdduser("测试人员");
		farm.setAddtime(new Timestamp(System.currentTimeMillis()));
		farm.setFarmremarks("Remarks");
		long start = System.currentTimeMillis();
		farmDAO.update(farm);
		long end = System.currentTimeMillis();
		System.out.println("运行时间" + (end-start) + "ms");
	}
	
	@Test
	public void selectList() {
		LFarms farm = new LFarms();
//		farm.setFarmname("测试");
//		farm.setFarmaddress("地址");
//		farm.setFarmlinkman("L");
		farm.setFarmprovince("广东");
//		farm.setFarmraisetype("公司");
		farm.setOrder("farmName");
		farm.setSort("desc");
		farm.setPage(0);
		farm.setRows(10);
		long start = System.currentTimeMillis();
		List<Farms> list = farmDAO.selectList(farm);
		long end = System.currentTimeMillis();
		System.out.println(list);
		System.out.println("运行时间" + (end-start) + "ms");
	}
	
	@Test 
	public void count() {
		LFarms farm = new LFarms();
//		farm.setFarmname("测试");
//		farm.setFarmaddress("地址");
//		farm.setFarmlinkman("L");
		farm.setFarmprovince("广东");
//		farm.setFarmraisetype("公司");
//		farm.setOrder("farmName");
//		farm.setSort("desc");
		long start = System.currentTimeMillis();
		int count = farmDAO.count(farm);
		long end = System.currentTimeMillis();
		System.out.println("count : "+count);
		System.out.println("运行时间" + (end-start) + "ms");
	}
	
	@Test
	public void selectAll() {
		long start = System.currentTimeMillis();
		List<String> list = farmDAO.selectAll("广东");
		long end = System.currentTimeMillis();
		System.out.println(list);
		System.out.println("运行时间" + (end-start) + "ms");
	}
	
	@Test
	public void test() throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
		ADrugView drugView = new ADrugView();
		Class<?> clazz = drugView.getClass();
		Class<?> superclass = clazz.getSuperclass();
		Field[] fields = superclass.getDeclaredFields();
		for (Field field : fields) {
			if(field.getType().equals(Double.class)) {
				String name = field.getName();
				field.setAccessible(true);
				if (name.endsWith("1")) {
					field.set(drugView, 0D);
				} else {
					field.set(drugView, 2048D);
				}
			}
		}
//		ADrugViewAll adva = new ADrugViewAll();
		String s = new ObjectMapper().writeValueAsString(drugView);
		System.out.println(s);
		
//		Double a = 2048D;
//		Double b = 0.03;
//		Double c = 127D;
//		Double d = -32D;
//		boolean checkDate = MicDataCheck.checkData(a);	
//		boolean checkDate2 = MicDataCheck.checkData(b);
//		boolean checkDate3 = MicDataCheck.checkData(c);
//		boolean checkDate4 = MicDataCheck.checkData(d);
//		Assert.assertEquals(true, checkDate);
//		Assert.assertEquals(true, checkDate2);
//		Assert.assertEquals(false, checkDate3);
//		Assert.assertEquals(false, checkDate4);
	}
}
