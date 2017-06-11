package drug.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.analysisModel.AGenView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class DrugViewAllTest {

	@Autowired
	private DrugViewAllDAO dvaDAO;
	@Autowired
	private DetailAnimalDAO animaliDAO;
	@Autowired
	private GenViewDAO gvDAO;

	@Test
	public void test() {
		// ADrugViewAll ldv = new ADrugViewAll();
		// ldv.setSampleno("15");
		// ldv.setStrain("埃希菌属");
		// ldv.setStatisticsType("1");
		// ldv.setTimeWay("1");
		// List<Map<String, Object>> strainCheckPro =
		// dvaDAO.strainCheckPro(ldv);
		// for (Map<String, Object> map : strainCheckPro) {
		// System.out.println(map);
		// }
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(20);
		set.add(60);
		Set<Integer> selectNosBySuper = animaliDAO.selectNosBySuper(set);
		System.out.println(selectNosBySuper);
	}

	@Test
	public void gencheck() {
		AGenView agv = new AGenView();
		agv.setGen("CTX-M-14");
		agv.setStatisticsType("4");
		agv.setTimeWay("4");
		List<Map<String, Object>> genCheckPro = gvDAO.genCheckPro(agv);
		for (Map<String, Object> map : genCheckPro) {
			System.out.println(map);
		}
	}

	@Test
	public void genIntervalcheck() {
		AGenView agv = new AGenView();
		agv.setGen("CTX-M-15");
		agv.setStatisticsType("4");
		agv.setTimeWay("4");
		agv.setInterval("48");
		List<Map<String, Object>> genCheckPro = gvDAO.genIntervalCheckPro(agv);
		for (Map<String, Object> map : genCheckPro) {
			System.out.println(map);
		}
	}
}
