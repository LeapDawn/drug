package drug.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.model.StrainCoding;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class StrainCodingDAOTest {

	@Autowired
	private StrainCodingDAO strainDAO;

	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}

	@Test
	public void test() {
		StrainCoding select = strainDAO.select("111111");
		System.out.println(select);
	}
}
