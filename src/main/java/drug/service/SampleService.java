package drug.service;

import java.util.List;

import drug.dto.listModel.LSample;
import drug.dto.pageModel.PSample;
import drug.dto.pageModel.PageResultModel;

public interface SampleService {
	
	/**
	 * 新增样品信息
	 * @param psample
	 */
	public void save(PSample psample);
	
	/**
	 * 修改样品信息
	 * @param psample
	 */
	public void update(PSample psample);
	
	/**
	 * 删除样品信息
	 * @param sampleNos
	 * @return 删除记录数
	 */
	public int delete(String sampleNos);
	
	/**
	 * 获取样品编号
	 * @return
	 */
	public List<String> getSampleNos();
	
	/**
	 * 查询样品列表
	 * @return
	 */
	public PageResultModel<PSample> list(LSample lsample);
	
}
