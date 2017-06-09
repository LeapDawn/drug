package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.pageModel.PGenotypingDir;
import drug.dto.pageModel.ResultPageModel;
import drug.model.GenotypingDir;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class GenotypingDirServiceTest {
	
	@Autowired
	private GenotypingDirService gtpdService;
	public void setGtpdService(GenotypingDirService gtpdService) {
		this.gtpdService = gtpdService;
	}
	
	@Test
	public void testSave() {
		GenotypingDir gtpd = new GenotypingDir();
		gtpd.setGenotyping1("embsb");
		gtpd.setGenotyping2("ctx-m");
		gtpd.setGenotyping3("ctx-m-9g");
		gtpd.setRemark("测试");
//		gtpdService.save(gtpd);
	}
	
	@Test
	public void testUpdate() {
		GenotypingDir gtpd = new GenotypingDir();
		gtpd.setId(1);
		gtpd.setGenotyping1("embsp");
		gtpd.setGenotyping2("CTX-M");
		gtpd.setGenotyping3("CTX-M-1g");
		gtpd.setRemark("");
//		gtpdService.update(gtpd);
	}
	
	@Test
	public void testdelete() {
		System.out.println(gtpdService.delete("1"));
	}
	
	
	@Test
	public void getGenotyping1() {
		List<String> genotyping1 = gtpdService.getGenotyping1();
		System.out.println(genotyping1);
	}
	
	@Test
	public void getGenotyping2() {
		List<String> genotyping1 = gtpdService.getGenotyping2("embsp");
		System.out.println(genotyping1);
	}
	
	@Test
	public void getGenotyping3() {
		List<String> genotyping1 = gtpdService.getGenotyping3("CTX-M");
		System.out.println(genotyping1);
	}
	
	@Test
	public void list() {
		PGenotypingDir page = new PGenotypingDir();
		page.setPage(1);
		page.setRows(10);
		ResultPageModel<GenotypingDir> result = gtpdService.list(page);
		System.out.println(result);
	}
}
