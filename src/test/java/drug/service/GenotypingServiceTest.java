package drug.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.commons.exception.DataViolationException;
import drug.dto.pageModel.PGenotyping;
import drug.dto.pageModel.ResultPageModel;
import drug.model.Genotyping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class GenotypingServiceTest {
	
	@Autowired
	private GenotypingService gtpService;
	public void setGtpService(GenotypingService gtpService) {
		this.gtpService = gtpService;
	}
	
	@Test
	public void testSave() {
		for (int i = 1; i <= 50; i++) {
			Genotyping gtp = new Genotyping();
			gtp.setGenname("CTX-M-" + i);
			gtp.setGenotyping("ctx-m-1g");
			gtp.setRemark("");
			try {
//				gtpService.save(gtp);
			} catch (DataViolationException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Test
	public void update() {
		Genotyping gtp = new Genotyping();
		gtp.setId(1);
		gtp.setGenname("CTX-M-15");
		gtp.setGenotyping("ctx-m-9g");
		gtp.setRemark("");
		try {
//			gtpService.update(gtp);
		} catch (DataViolationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void delete() {
		System.out.println(gtpService.delete("40,41,42,43"));
	}
	
	@Test
	public void list() {
		PGenotyping pgtp = new PGenotyping();
		pgtp.setGenotyping("ctx-m-1g");
		pgtp.setPage(5);
		pgtp.setRows(5);
		ResultPageModel<Genotyping> result = gtpService.list(pgtp);
		System.out.println(result.getCurrentPage() + "/" + result.getPages());
		for (Genotyping pgt : result.getData()) {
			System.out.println(pgt);
		}
	}
}
