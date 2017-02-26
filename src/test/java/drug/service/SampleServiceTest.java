package drug.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import drug.dto.listModel.LSample;
import drug.dto.pageModel.PSample;
import drug.dto.pageModel.PageResultModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml" })
public class SampleServiceTest {

	@Autowired
	private SampleService sampleService;

	public void setSampleService(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@Test
	public void save() {
		PSample psample = new PSample();
		psample.setAnimalno(60);
		psample.setFarmname("广州市天河牲畜交易市");
		psample.setSampleanimalage("7");
		psample.setSamplecollectionpart("01");
		psample.setSamplecollector("scau");
		psample.setSampledateStr("2017-01-09");
		psample.setSamplefarmaddr("养殖场地址");
		psample.setSamplemedicalhistory("no");
		psample.setSampleprovince("广东");
		psample.setSampleremarks("备注信息11111111");
		psample.setSamplesource("养殖场");

		try {
			sampleService.save(psample);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-------------");
		}
		System.out.println(psample);
	}

	@Test
	public void update() {
		PSample psample = new PSample();
		psample.setSampleno("1751360002");
		psample.setAnimalno(60);
		psample.setFarmname("广州市天河牲畜交易市场");
		psample.setSampleanimalage("9");
		psample.setSamplecollectionpart("01");
		psample.setSamplecollector("sca1u");
		psample.setSampledateStr("2018-01-09");
		psample.setSamplefarmaddr("养1殖场地址");
		psample.setSamplemedicalhistory("no");
		psample.setSampleprovince("广东s");
		psample.setSampleremarks("备注信息");
		psample.setSamplesource("养殖场");

		try {
			sampleService.update(psample);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-------------");
		}
		System.out.println(psample);
	}

	@Test
	public void delete() {
		String ids = "1751360002,1751360001,1751360003";
		sampleService.delete(ids);
	}
	
	@Test
	public void getSampleNos() {
		List<String> sampleNos = sampleService.getSampleNos();
		int n = 10 < sampleNos.size()?10:sampleNos.size();
		for (int i = 0; i < n; i++) {
			System.out.println(sampleNos.get(i));
		}
		System.out.println("size:" + sampleNos.size());
	}
	
	@Test
	public void list() {
		LSample sample = new LSample();
//		sample.setAnimalno(new Integer[]{60,10});
//		sample.setSampleprovince(new String[]{"广东","内蒙古"});
//		sample.setSamplesource(new String[]{"屠宰场","养殖场"});
//		sample.setPage(3);
//		sample.setRows(10);
		sample.setSampleno("142532");
		sample.setAnimalno(new Integer[]{23});
		sample.setSampleprovince(new String[]{"山东"});
		sample.setSamplesource(new String[]{"养殖场"});
		sample.setSamplefarmaddr("烟台市海阳市行村镇");
		sample.setFarmname("山东烟");
		sample.setSamplecollectionpart(new String[]{"01"});
		sample.setBeginDate("2014-06-01");
		sample.setEndDate("2016-10-01");
		sample.setPage(1);
		sample.setRows(10);
		
		
		PageResultModel<PSample> result = sampleService.list(sample);
		System.out.println("total:" + result.getTotal());
		List<PSample> data = result.getData();
		for (PSample pSample : data) {
			System.out.println(pSample);
		}
	
	}
}
