package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.listModel.LGenView;
import drug.dto.pageModel.PGenView;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class GenViewTest {
	
	@Autowired
	private GenViewService gvService;
	public void setGvService(GenViewService gvService) {
		this.gvService = gvService;
	}
	
	@Test
	public void list() {
		LGenView lgv = new LGenView();
		lgv.setStrainno("13");
		lgv.setGenalias("S");
		lgv.setSampleno("2");
		lgv.setBeginDate("2013-07-12");
		lgv.setEndDate("2013-11-30");
		lgv.setGenotyping1(new String[]{"embsb"});
		lgv.setGenotyping2(new String[]{"ctx-m"});
		lgv.setGenotyping3(new String[]{"ctx-m-1g"});
		lgv.setAnimalname(new String[]{"鸡","猪"});
		PageResultModel<PGenView> result = gvService.list(lgv);
		System.out.println(result.getRows() + "/" + result.getTotal());
		System.out.println(result.getCurrentPage() + "/" + result.getPages());
		List<PGenView> data = result.getData();
		for (PGenView pGenView : data) {
			System.out.println(pGenView);
		}
	}
}
