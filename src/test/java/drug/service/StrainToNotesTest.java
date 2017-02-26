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

import drug.dto.pageModel.PStrainToNotes;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class StrainToNotesTest {
	
	@Autowired
	private StrainToNotesService strainService;
	public void setStrainService(StrainToNotesService strainService) {
		this.strainService = strainService;
	}
	
	@Test
	public void save(){
		PStrainToNotes pstrain = new PStrainToNotes();
		pstrain.setStrainnotes("99");
		pstrain.setStrainname("菌种名称");
		pstrain.setStraincategory("菌属名称");
		pstrain.setGramstain("阳性");
		pstrain.setStrainremark("remark");
		
		try {
			strainService.save(pstrain);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				System.out.println("该菌株编号已经存在");
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
	public void update(){
		PStrainToNotes pstrain = new PStrainToNotes();
		pstrain.setStrainnotes("99");
		pstrain.setStrainname("菌种名称");
		pstrain.setStraincategory("菌属名称");
		pstrain.setGramstain("阴性");
		pstrain.setStrainremark("");
		try {
			strainService.update(pstrain);
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
	public void delete(){
		String notes = "96,97,98,99";
		try {
			strainService.delete(notes);
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
	public void list(){
		PStrainToNotes pstrain = new PStrainToNotes();
		pstrain.setPage(5);
		pstrain.setRows(5);
		try {
			PageResultModel<PStrainToNotes> list = strainService.list(pstrain);
			for (PStrainToNotes item : list.getData()) {
				System.out.println(item);
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
	
	@Test
	public void getStrains(){
//		String category = "";
		String category = "葡萄球菌属";
		try {
			List<String> list = strainService.getStrains(category);
			System.out.println(list);
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
	public void getCategory(){
		String garm = "阴性";
		try {
			List<String> list = strainService.getCategory(garm);
			System.out.println(list);
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
