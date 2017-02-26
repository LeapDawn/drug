package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import drug.dto.analysisModel.ADrugViewAll;
import drug.dto.listModel.LDrugViewAll;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class DrugViewAllServiceTest {

	@Autowired
	private DrugViewAllService drugViewAllService;
	public void setDrugViewAllService(DrugViewAllService drugViewAllService) {
		this.drugViewAllService = drugViewAllService;
	}

	@Test
	public void list() {
		LDrugViewAll ldva = new LDrugViewAll();
		ldva.setAnimalAge("");
//		ldva.setAnimalNo(new Integer[]{60,21});
		ldva.setBeginDate("2012-01-01");
		ldva.setEndDate("2017-01-01");
		ldva.setCategory(new String[]{"埃希菌属","沙门菌属"});
		ldva.setFarmaddr("");
		ldva.setFarmname("广州");
		ldva.setPage(2);
		ldva.setRows(5);
//		ldva.setPartNo(new String[]{"01","02","00"});
		ldva.setProvince(new String[]{"广东","北京"});
		ldva.setSampleno("");
		drugViewAllService.list(ldva);
	}
	
	@Test
	public void getData() throws JsonProcessingException {
		ADrugViewAll ldva = new ADrugViewAll();
		ldva.setStrain("埃希菌属,沙文菌属");
//		ldva.setTimeWay("1");
//		ldva.setStatisticsType("1");
		List<?> list = drugViewAllService.getAnalysisData(ldva);
//		System.out.println(list);
		String s = new ObjectMapper().writeValueAsString(list);
		System.out.println(s);
		
	}
}
