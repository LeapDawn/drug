package drug.service;

import java.util.List;

import drug.dto.listModel.LFarms;
import drug.dto.pageModel.PFarms;
import drug.dto.pageModel.PageResultModel;

public interface FarmsService {
	
	/**
	 * 添加新的采样地
	 * @param pfarm 采样地页面模型
	 */
	public void save(PFarms pfarm);
	
	/**
	 * 修改采样地信息
	 * @param pfarm 采样地页面模型
	 */
	public void update(PFarms pfarm);
	
	/**
	 * 查询采样地列表
	 * @param pfarm 采样地搜索条件对象(包含页码和记录数)
	 * @return 查询结果
	 */
	public PageResultModel<PFarms> list(LFarms lfarm);
	
	/**
	 * 删除采样地
	 * @param farmNames 采样地名称
	 * @return 被删除的记录数
	 */
	public int delete(String farmNames);
	
	/**
	 * 获取采样地(id+名称)
	 * @param province 省份
	 * @return 查询集合
	 */
	public List<String> getFarmsByProvince(String province);
	
}
