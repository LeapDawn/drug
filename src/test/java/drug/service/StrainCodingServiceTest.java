package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.listModel.LStrainCoding;
import drug.dto.pageModel.PStrainCoding;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class StrainCodingServiceTest {
	@Autowired
	private StrainCodingService strainService;

	public void setStrainService(StrainCodingService strainService) {
		this.strainService = strainService;
	}

	@Test
	public void save() {
		PStrainCoding strain = new PStrainCoding();
		strain.setSampleno("1551366046");
		strain.setStraincategory("葡萄球菌属");
		strain.setStraintype("金黄色葡萄球菌");
		strain.setStrainstoragedateStr("2017-01-10");
		strain.setOperator("小博哥");
		strain.setFarmName("11");
		strain.setStrainremarks("132");
		strain.setStrainalias("ABCFG");
		strain.setStrainmlst("123");
		strain.setStrainplg("321");
		strain.setSerotype("213");
		strain.setStrainparter("parter");
		strain.setOtherMsg("test");
		strainService.save(strain);
		System.out.println(strain);
	}

	@Test
	public void update() {
		PStrainCoding strain = new PStrainCoding();
		strain.setStrainno("155136604611");
		strain.setSampleno("1551366046");
		strain.setStraincategory("葡萄球菌属");
		strain.setStraintype("金黄色葡萄球菌");
		strain.setStrainstoragedateStr("2017-01-10");
		strain.setOperator("小博哥");
		strain.setFarmName("11aa");
		strain.setStrainremarks("132aa");
		strain.setStrainalias("ABCFGaa");
		strain.setStrainmlst("123aa");
		strain.setStrainplg("321aa");
		strain.setSerotype("213aa");
		strain.setStrainparter("parteraa");
		strain.setOtherMsg("testaa");
		strainService.update(strain);
	}

	@Test
	public void delete() {
		String nos = "155136604840";
		strainService.delete(nos);
	}

	@Test
	public void selectList() {
		LStrainCoding strainCoding = new LStrainCoding();
		strainCoding.setStrainno("15");
		strainCoding.setSampleno("15");
		strainCoding.setStraincategory(new String[]{"葡萄球菌属","埃希菌属"});
		strainCoding.setStraintype(new String[]{"大肠埃希菌"});
		strainCoding.setStrainalias("GD");
		strainCoding.setBeginDate("2015-06-01");
		strainCoding.setEndDate("2015-06-30");
		strainCoding.setOperator("郭");
		strainCoding.setPage(5);
		strainCoding.setRows(10);
		PageResultModel<PStrainCoding> list = strainService.list(strainCoding);
		List<PStrainCoding> data = list.getData();
		for (PStrainCoding pStrainCoding : data) {
			System.out.println(pStrainCoding);
		}
		System.out.println("total:" + list.getTotal());
		System.out.println("pages:" + list.getPages());
		System.out.println("rows:" + list.getRows());
		System.out.println("cu_page:" + list.getCurrentPage());
	}

	@Test
	public void getStringNoNotInMic() {
		String gram = "阳性";
		List<String> list = strainService.getStringNoNotInMic(gram);
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println("total:" + list.size());
	}

	@Test
	public void getStringNoNotInCharacter() {
		List<String> list = strainService.getStringNoNotInCharacter();
		// for (String string : list) {
		// System.out.println(string);
		// }
		System.out.println("total:" + list.size());
	}
}
