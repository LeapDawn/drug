package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.listModel.LStrainCharacter;
import drug.dto.pageModel.PStrainCharacter;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class StrainCharacterServiceTest {

	@Autowired
	private StrainCharacterService characterService;
	public void setCharacterService(StrainCharacterService characterService) {
		this.characterService = characterService;
	}
	
	
	@Test
	public void save() {
		PStrainCharacter pcharacter = new PStrainCharacter();
		pcharacter.setStrainno("155136605240");
		pcharacter.setGenname("oqxB");
		pcharacter.setIseq("aaa");
		pcharacter.setReplicon("bbb");
		pcharacter.setGentc("ccc");
		pcharacter.setOperator("操作人");
		pcharacter.setGenremarks("备注");
		characterService.save(pcharacter);
		System.out.println(pcharacter);
	}
	
	@Test
	public void update() {
		PStrainCharacter pcharacter = new PStrainCharacter();
		pcharacter.setId(831);
		pcharacter.setStrainno("155136605241");
		pcharacter.setGenname("oqxD");
		pcharacter.setIseq("abc");
		pcharacter.setReplicon("bac");
		pcharacter.setGentc("cba");
		pcharacter.setOperator("操作人1");
		pcharacter.setGenremarks("备注1");
		characterService.update(pcharacter);
	}
	
	@Test
	public void delete() {
		String ids = "831,832,833";
		characterService.delete(ids);
	}
	
	@Test
	public void list() {
		LStrainCharacter lcharacter = new LStrainCharacter();
//		lcharacter.setStrainno("13");
//		lcharacter.setGenname("oq");
//		lcharacter.setGenalias("XC");
		lcharacter.setGentc("2");
		lcharacter.setIseq("1");
		lcharacter.setReplicon("3");
		lcharacter.setOperator("4");
//		lcharacter.setOrder("strainNo");
//		lcharacter.setSort("desc");
//		lcharacter.setPage(2);
//		lcharacter.setRows(10);
		
		PageResultModel<PStrainCharacter> list = characterService.list(lcharacter);
		List<PStrainCharacter> data = list.getData();
		for (PStrainCharacter pStrainCharacter : data) {
			System.out.println(pStrainCharacter);
		}
		System.out.println(list.getCurrentPage()+"/" +list.getPages());
		System.out.println(list.getTotal());
	}
}
