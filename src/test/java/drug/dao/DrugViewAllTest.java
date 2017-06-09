package drug.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.analysisModel.ADrugViewAll;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class DrugViewAllTest {

	@Autowired
	private DrugViewAllDAO dvaDAO;
	@Autowired 
	private DetailAnimalDAO animaliDAO;
	

	@Test
	public void test() {
//		ADrugViewAll ldv = new ADrugViewAll();
//		ldv.setSampleno("15");
//		ldv.setStrain("埃希菌属");
//		ldv.setStatisticsType("1");
//		ldv.setTimeWay("1");
//		List<Map<String, Object>> strainCheckPro = dvaDAO.strainCheckPro(ldv);
//		for (Map<String, Object> map : strainCheckPro) {
//			System.out.println(map);
//		}
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(20);
		set.add(60);
		 Set<Integer> selectNosBySuper = animaliDAO.selectNosBySuper(set);
		System.out.println(selectNosBySuper);
	}

}
