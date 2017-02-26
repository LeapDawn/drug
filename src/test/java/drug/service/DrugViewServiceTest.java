package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import drug.dto.analysisModel.ADrugView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class DrugViewServiceTest {
	@Autowired
	private DrugViewService drugViewService;
	public void setDrugViewService(DrugViewService drugViewService) {
		this.drugViewService = drugViewService;
	}
	
	@Test
	public void getData() throws JsonProcessingException {
		ADrugView ldv = new ADrugView();
		ldv.setStrain("肠杆菌科");
		ldv.setDrug("AMK,AMP");
		ldv.setTimeWay("2");
		ldv.setStrainno("1");
		ldv.setBeginDate("2014-01-01");
//		ldv.setStatisticsType("1");
		List<?> list = drugViewService.getAnalysisData(ldv);
//		System.out.println(list);
		String s = new ObjectMapper().writeValueAsString(list);
		System.out.println(s);
	}
}
