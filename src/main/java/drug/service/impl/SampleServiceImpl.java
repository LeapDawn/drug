package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.Province;
import drug.commons.util.Transfer;
import drug.dao.SampleDAO;
import drug.dao.SourceDAO;
import drug.dto.listModel.LSample;
import drug.dto.pageModel.PSample;
import drug.dto.pageModel.PageResultModel;
import drug.model.Sample;
import drug.model.Source;
import drug.service.SampleService;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	
	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private SampleDAO sampleDAO;
	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	
	@Autowired
	private SourceDAO sourceDAO;
	public void setSourceDAO(SourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}
	
	@Override
	public void save(PSample psample) {
		psample.setSampleno(generateSampleNo(psample));
		Sample sample = Transfer.changeToEntity(psample);
		sampleDAO.insert(sample);
	}
	
	@Override
	public void update(PSample psample) {
		Sample sample = Transfer.changeToEntity(psample);
		sampleDAO.update(sample);
	}
	
	@Override
	public int delete(String sampleNos) {
		if (sampleNos == null || "".equals(sampleNos) || ",".equals(sampleNos.trim())) {
			return 0;
		}
		String[] sampleNoArray = sampleNos.trim().split(",");
		int deleteNum = sampleDAO.delete(sampleNoArray);
		return deleteNum;
	}
	
	@Override
	public List<String> getSampleNos() {
		return sampleDAO.selectSampleNos();
	}
	
	@Override
	public PageResultModel<PSample> list(LSample lsample) {
		if (lsample == null) {
			lsample = new LSample();
		}
		int total = sampleDAO.count(lsample);
		PageResultModel<PSample> resultModel =
				new PageResultModel<PSample>(total, lsample.getRows(), lsample.getPage());
		lsample.setRows(resultModel.getRows());
		lsample.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<Sample> list = sampleDAO.selectList(lsample);
		List<PSample> plist = new ArrayList<PSample>();
		for (Sample sample : list) {
			plist.add(Transfer.changetoPageModel(sample));
		}
		resultModel.setData(plist);
		return resultModel;
	}
	
	/**
	 * 生成样品编号,年份+省份+来源+动物+num
	 * @param psample
	 * @return
	 */
	private String generateSampleNo(PSample psample) {
		// 验证year
		String date = psample.getSampledateStr();
		if (date == null || date.length() != 10){
			throw new DataViolationException("添加失败,日期格式错误,实例:2016-01-01");
		}
		// 验证省份
		String yearStr = date.substring(2, 4);
		String province = Province.getProvinceNo(psample.getSampleprovince());
		if (province == null || province.trim().equals("")) {
			throw new DataViolationException("添加失败,省份[" + psample.getSampleprovince() + "]错误");
		}
		// 验证动物编号
		if (psample.getAnimalno() == null) {
			throw new DataViolationException("添加失败,动物不能为空");
		}
		String animalNo = String.valueOf(psample.getAnimalno());

		// 验证来源
		String sourceName = psample.getSamplesource();
		if (sourceName == null) {
			throw new DataViolationException("添加失败,来源不能为空");
		}
		List<Source> sourcelist = sourceDAO.selectAll();
		if(sourcelist == null || sourcelist.size() == 0) {
			throw new DataViolationException("添加失败,不存在该来源,请在[编号管理-来源编号]页面添加来源");
		}
		String sourceNo = null;
		for (Source source : sourcelist) {
			if (source.getSourcename().trim().equals(sourceName.trim())) {
				sourceNo = String.valueOf(source.getSourceno());
				break;
			}
		}
		if (sourceNo == null) {
			throw new DataViolationException("添加失败,不存在来源[" + sourceName + "],请在[编号管理-来源编号]页面添加来源");
		}
		String prefixNo = yearStr + province + sourceNo + animalNo;
		return generateSuffixNo(prefixNo);
	}
	
	// 获取样品编号的后缀部分
	private String generateSuffixNo(String prefixNo) {
		String maxNo = sampleDAO.selectMaxNo(prefixNo);
		if (maxNo == null || "".equals(maxNo)) {
			return prefixNo + "001";
		} else {
			return String.valueOf(Long.valueOf(maxNo) + 1);
		}
	}
}
