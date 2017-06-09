package drug.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.commons.exception.ExcelException;
import drug.dao.StrainMicDAO;
import drug.dto.listModel.LStrainMic;
import drug.dto.pageModel.PStrainMic;
import drug.dto.pageModel.UploadResultModel;
import drug.service.impl.UploadUpdateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class StrainMicServiceTest {

	@Autowired
	private StrainMicService micService;
	@Resource(name = "negativeMicUpDown")
	private UploadUpdateService uploadUpdate;

	public void setUploadUpdate(UploadUpdateService uploadUpdate) {
		this.uploadUpdate = uploadUpdate;
	}

	public void setMicService(StrainMicService micService) {
		this.micService = micService;
	}

	@Autowired
	private StrainMicDAO micDAO;

	public void setMicDAO(StrainMicDAO micDAO) {
		this.micDAO = micDAO;
	}

	@Test
	public void uploadUpdate() throws FileNotFoundException, ExcelException {
		UploadResultModel resultModel = uploadUpdate.uploadUpdate(
				new FileInputStream("e://mic.xls1"),"阴性", "");
		List<?> data = resultModel.getData();
		System.out.println(data);
	}

	@Test
	public void save() {
		PStrainMic pstrainMic = new PStrainMic();
		// pstrainMic.setStrainno("155126019540");
		// pstrainMic.setStrainno("155126019440");
		pstrainMic.setStrainno("1111111");
		pstrainMic.setAmp(8D);
		pstrainMic.setCip(1D);
		pstrainMic.setCli(2D);
		pstrainMic.setCqm(4D);
		pstrainMic.setEry(16D);
		pstrainMic.setFfc(32D);
		pstrainMic.setFox(64D);
		pstrainMic.setGen(128D);
		pstrainMic.setLzd(256D);
		pstrainMic.setOxa(512D);
		pstrainMic.setPen(1024D);
		pstrainMic.setRif(2048D);
		pstrainMic.setTet(0.5D);
		pstrainMic.setTia(0.06D);
		pstrainMic.setVal(0.25);
		pstrainMic.setVan(0.125);

		// pstrainMic.setCaz(8D);
		// pstrainMic.setCqm(8D);
		// pstrainMic.setCtx(8D);
		// pstrainMic.setFox(8D);
		// pstrainMic.setAmk(8D);
		// pstrainMic.setApr(8D);
		// pstrainMic.setGen(1D);
		// pstrainMic.setNeo(0.3);
		// pstrainMic.setStr(0.7);
		// pstrainMic.setDox(128D);
		// pstrainMic.setTet(4D);
		// pstrainMic.setChl(1024D);
		// pstrainMic.setFfc(32D);
		// pstrainMic.setCl(0.25);
		// pstrainMic.setImp(0D);
		// pstrainMic.setMem(16D);
		// pstrainMic.setFos(512D);
		// pstrainMic.setSxt(0.06);
		// pstrainMic.setOqx(2048D);
		// pstrainMic.setOperator("测试");
		// pstrainMic.setMicdetectiontype("自由");
		// pstrainMic.setRemark("备注");
		micService.save(pstrainMic);

	}

	@Test
	public void update() {
		PStrainMic pstrainMic = new PStrainMic();
		// pstrainMic.setStrainno("155126019540");
		pstrainMic.setStrainno("155126019440");
		// pstrainMic.setStrainno("1111111");
		// pstrainMic.setAmp(2048D);
		// pstrainMic.setCip(1024D);
		// pstrainMic.setCli(512D);
		// pstrainMic.setCqm(256D);
		// pstrainMic.setEry(128D);
		// pstrainMic.setFfc(64D);
		// pstrainMic.setFox(32D);
		// pstrainMic.setGen(16D);
		// pstrainMic.setLzd(8D);
		// pstrainMic.setOxa(4D);
		// pstrainMic.setPen(2D);
		// pstrainMic.setRif(1D);
		// pstrainMic.setTet(0.6D);
		// pstrainMic.setTia(0.5D);
		// pstrainMic.setVal(0.3);
		// pstrainMic.setVan(0.12);

		pstrainMic.setCaz(2048D);
		pstrainMic.setCqm(1024D);
		pstrainMic.setCtx(512D);
		pstrainMic.setFox(256D);
		pstrainMic.setAmk(128D);
		pstrainMic.setApr(64D);
		pstrainMic.setGen(32D);
		pstrainMic.setNeo(16D);
		pstrainMic.setStr(8D);
		pstrainMic.setDox(4D);
		pstrainMic.setTet(2D);
		pstrainMic.setChl(1D);
		pstrainMic.setFfc(0.6);
		pstrainMic.setCl(0.5);
		pstrainMic.setImp(0.3);
		pstrainMic.setMem(0.25);
		pstrainMic.setFos(0.12);
		pstrainMic.setSxt(0.125);
		pstrainMic.setOqx(0.128D);
		micService.update(pstrainMic);
	}

	@Test
	public void delete() {
		String nos = "155126019440,155126019540";
		// micService.delete(nos);
	}

	@Test
	public void list() {
		LStrainMic lmic = new LStrainMic();
		lmic.setGram("阴性");
		lmic.setStrainno("15");
		lmic.setMicalias("GDK");
		lmic.setCategory(new String[] { "埃希菌属", "沙门菌属" });

		lmic.setPage(5);
		lmic.setRows(10);
		lmic.setOrder("strainno");
		lmic.setSort("desc");

		micDAO.count(lmic);

		// ResultPageModel<PStrainMic> list = micService.list(lmic);
		// List<PStrainMic> data = list.getData();
		// for (PStrainMic pStrainMic : data) {
		// System.out.println(pStrainMic);
		// }
		//
		// System.out.println("total:" + list.getTotal());
		// System.out.println("Pages:" + list.getPages());
		// System.out.println("Rows:" + list.getRows());
		// System.out.println("curr:" + list.getCurrentPage());
	}

	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		Field[] fields = LStrainMic.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().equals(Double.class)) {
				String name = field.getName();
				name = name.substring(0, name.length() - 1);
				if (!list.contains(name)) {
					aa(name);
					list.add(name);
				}
			}
		}

	}

	private void aa(String str) {
		System.out.print("<if test=\"");
		System.out.print(str.toLowerCase() + "1");
		System.out.println("!=null\">");
		System.out.print("	and " + str.toUpperCase() + " <![CDATA[>=]]> #{");
		System.out.print(str.toLowerCase() + "1");
		System.out.println(",jdbcType=DOUBLE}");
		System.out.println("</if>");
		System.out.print("<if test=\"");
		System.out.print(str.toLowerCase() + "2");
		System.out.println("!=null\">");
		System.out.print("  and " + str.toUpperCase() + " <![CDATA[<=]]> #{");
		System.out.print(str.toLowerCase() + "2");
		System.out.println(",jdbcType=DOUBLE}");
		System.out.println("</if>");
	}
}
