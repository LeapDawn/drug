package drug.service;

import java.io.File;
import java.sql.Timestamp;
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

import drug.commons.exception.ExeclException;
import drug.dto.listModel.LFarms;
import drug.dto.pageModel.PFarms;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class FarmServiceTest {

	@Autowired
	private FarmsService farmService;

	public void setFarmService(FarmsService farmService) {
		this.farmService = farmService;
	}

	@Test
	public void save() {
		PFarms farm = new PFarms();
		farm.setFarmname("测试用例");
		farm.setFarmaddress("新地址");
		farm.setFarmprovince("广东");
		farm.setFarmbuilddate("1999-09-09");
		farm.setFarmlinkman("LJB");
		farm.setFarmphone("000000");
		farm.setFarmraisetype("公司");
		farm.setAdduser("测试人员");
		farm.setAddtime(new Timestamp(System.currentTimeMillis()));
		long start = System.currentTimeMillis();
		try {
			farmService.save(farm);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该采样地已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性");
			} else if (e instanceof CannotCreateTransactionException) {
				System.out.println("开启事务失败,可能是数据库服务未打开");
			} else if (e instanceof DataAccessResourceFailureException) {
				System.out.println("连接数据库失败");
			} else {
				System.out.println("未知异常");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end - start) + "ms");
	}

	@Test
	public void update() {
		PFarms farm = new PFarms();
		farm.setFarmname("测试用例");
		farm.setFarmaddress("新地址");
		farm.setFarmprovince("广东");
		farm.setFarmbuilddate("1999-10-09");
		farm.setFarmlinkman("LJB");
		farm.setFarmphone("010100");
		farm.setFarmraisetype("公司+农户");
		farm.setFarmremarks("修改");
		farm.setAdduser("修改人员");
		long start = System.currentTimeMillis();
		try {
			farmService.update(farm);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性");
			} else if (e instanceof CannotCreateTransactionException) {
				System.out.println("开启事务失败,可能是数据库服务未打开");
			} else if (e instanceof DataAccessResourceFailureException) {
				System.out.println("连接数据库失败");
			} else {
				System.out.println("未知异常");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end - start) + "ms");
	}

	@Test
	public void show() {
		LFarms pfarm = new LFarms();
		pfarm.setPage(3);
		pfarm.setRows(5);
		pfarm.setFarmprovince("广东");
		pfarm.setOrder("farmName");
		pfarm.setSort("desc");
		PageResultModel<PFarms> resultPageModel = null;
		long start = System.currentTimeMillis();
		try {
			resultPageModel = farmService.list(pfarm);
		} catch (Exception e) {
			if (e instanceof CannotCreateTransactionException) {
				System.out.println("开启事务失败,可能是数据库服务未打开");
			} else if (e instanceof DataAccessResourceFailureException) {
				System.out.println("连接数据库失败");
			} else {
				System.out.println("未知异常");
			}
		}
		long end = System.currentTimeMillis();
		if (resultPageModel != null) {
			List<PFarms> list = resultPageModel.getData();
			for (PFarms pFarms : list) {
				System.out.println(pFarms);
			}
			System.out.println("total : " + resultPageModel.getTotal());
			System.out.println("pages : " + resultPageModel.getPages());
			System.out.println("currentPage : " + resultPageModel.getCurrentPage());
			System.out.println("rows : " + resultPageModel.getRows());
		}
		System.out.println("time:" + (end - start) + "ms");
	}

	@Test
	public void delete() {
//		String farmNames = "测试用例,失败用例,广州市天河牲畜交易市场";
		String farmNames = "测试,测试2";
		long start = System.currentTimeMillis();
		try {
			farmService.delete(farmNames);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				System.out.println("违反数据完整性,采样地被引用");
			} else if (e instanceof CannotCreateTransactionException) {
				System.out.println("开启事务失败,可能是数据库服务未打开");
			} else if (e instanceof DataAccessResourceFailureException) {
				System.out.println("连接数据库失败");
			} else {
				System.out.println("未知异常");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end - start) + "ms");
	}

	@Test
	public void getFarmsByProvince() {
		String province = "广东";
//		String province = "";
		long start = System.currentTimeMillis();
		List<String> list = null;
		try {
			list = farmService.getFarmsByProvince(province);
		} catch (Exception e) {
			if (e instanceof CannotCreateTransactionException) {
				System.out.println("开启事务失败,可能是数据库服务未打开");
			} else if (e instanceof DataAccessResourceFailureException) {
				System.out.println("连接数据库失败");
			} else {
				System.out.println("未知异常");
			}
		}
		
		long end = System.currentTimeMillis();
		if (list != null && !list.isEmpty()) {
			for (String pFarms : list) {
				System.out.println(pFarms);
			}
		}
		System.out.println("time:" + (end - start) + "ms");
	}
	
//	@Test
//	public void export() throws ExeclException {
//		String farmNames = "上海奉贤鸡场,上海浦东新场镇长桥畜牧场,内蒙古奈伦";
//		File file = new File("e:/abc.xls");
//		file = farmService.exportFarms(farmNames, file);
//	}
}
