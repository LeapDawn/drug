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

import drug.dao.StandardMicDAO;
import drug.dto.pageModel.PStandardMic;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class StandardMicServiceTest {

	@Autowired
	private StandardMicService standardMicService;

	public void setStandardMicService(StandardMicService standardMicService) {
		this.standardMicService = standardMicService;
	}

	@Autowired
	private StandardMicDAO dao;
	public void setDao(StandardMicDAO dao) {
		this.dao = dao;
	}

	@Test
	public void save() {
		PStandardMic standardMic = new PStandardMic();
		standardMic.setMedicalname("测试");
		standardMic.setStandardname("测试2");
		standardMic.setStrainname("测试2");
		standardMic.setRemark("备注");
		standardMic.setMedlimit(12F);
		try {
			standardMicService.save(standardMic);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该药品标准信息已经存在");
			} else if (e instanceof DataIntegrityViolationException) {
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
	public void update() {
		PStandardMic standardMic = new PStandardMic();
		standardMic.setMedicalname("测试");
		standardMic.setStandardname("测试2");
		standardMic.setStrainname("测试2");
		standardMic.setRemark("");
		standardMic.setMedlimit(18F);
		try {
			standardMicService.update(standardMic);
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
	public void list() {
		PStandardMic PstandardMic = new PStandardMic();
		PstandardMic.setPage(3);
		PstandardMic.setRows(5);
		PageResultModel<PStandardMic> model = null;
		try {
			model = standardMicService.list(PstandardMic);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
		for (PStandardMic psm : model.getData()) {
			System.out.println(psm);
		}
	}

	@Test
	public void delete() {
		List<PStandardMic> list = new ArrayList<PStandardMic>();
		PStandardMic standardMic = new PStandardMic();
		standardMic.setMedicalname("测试");
		standardMic.setStandardname("测试2");
		standardMic.setStrainname("测试2");
		list.add(standardMic);
		standardMic = new PStandardMic();
		standardMic.setMedicalname("测试");
		standardMic.setStandardname("测试2");
		standardMic.setStrainname("测试");
		list.add(standardMic);
		standardMic = new PStandardMic();
		standardMic.setMedicalname("测试");
		standardMic.setStandardname("测试");
		standardMic.setStrainname("测试");
		list.add(standardMic);
		standardMic = new PStandardMic();
		standardMic.setMedicalname("测试1");
		standardMic.setStandardname("测试2");
		standardMic.setStrainname("测试");
		list.add(standardMic);
		PStandardMic[] array = new PStandardMic[list.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		try {
			standardMicService.delete(array);
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
	public void getStrain() {
		try {
			List<String> strains = standardMicService.getStrains();
			System.out.println(strains);
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
	
	@Test
	public void getStandard(){
		try {
			List<String> standards = standardMicService.getStandards();
			System.out.println(standards);
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
