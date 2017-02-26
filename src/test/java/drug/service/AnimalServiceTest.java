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

import drug.dto.pageModel.PAnimal;
import drug.dto.pageModel.PageResultModel;
import drug.model.DetailAnimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class AnimalServiceTest {

	@Autowired
	private AnimalService animalService;
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}

	@Test
	public void save() {
		PAnimal panimal = new PAnimal();
		panimal.setAnimalno(995);
		panimal.setAnimalname("janvier");
		panimal.setAnimalremarks("备注");
		try {
			animalService.save(panimal);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该动物编号已经存在");
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
		PAnimal panimal = new PAnimal();
		panimal.setAnimalno(995);
		panimal.setAnimalname("janviercheng");
		panimal.setAnimalremarks("");
		try {
			animalService.update(panimal);
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
		PAnimal panimal = new PAnimal();
		panimal.setPage(3);
		panimal.setRows(5);
		PageResultModel<PAnimal> model = null;
		try {
			model = animalService.list(panimal);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CannotCreateTransactionException 
					|| e instanceof DataAccessResourceFailureException) {
				System.out.println("数据库服务未打开");
			} else {
				System.out.println("未知异常");
			}
		}
		for (PAnimal animal : model.getData()) {
			System.out.println(animal);
		}
	}

	@Test
	public void delete() {
		String deletenos = "995,996,99ac,997";
		try {
			animalService.delete(deletenos);
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
			List<DetailAnimal> animals = animalService.getAnimals();
			for (DetailAnimal detailAnimal : animals) {
				System.out.println(detailAnimal);
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
