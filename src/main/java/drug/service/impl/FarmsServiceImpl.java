package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.util.Transfer;
import drug.dao.FarmsDAO;
import drug.dto.listModel.LFarms;
import drug.dto.pageModel.PFarms;
import drug.dto.pageModel.PageResultModel;
import drug.model.Farms;
import drug.service.FarmsService;

@Service("farmService")
public class FarmsServiceImpl implements FarmsService{

	@Autowired
	private FarmsDAO farmDAO;
	public void setFarmDAO(FarmsDAO farmDAO) {
		this.farmDAO = farmDAO;
	}

	/**
	 * 添加新的采样地
	 * @param pfarm 采样地页面模型
	 */
	@Override
	public void save(PFarms pfarm) {
		Farms farm = Transfer.changeToEntity(pfarm);
		farmDAO.insert(farm);
	}

	/**
	 * 修改采样地信息
	 * @param pfarm 采样地页面模型
	 */
	@Override
	public void update(PFarms pfarm) {
		Farms farm = Transfer.changeToEntity(pfarm);
		farmDAO.update(farm);
	}

	/**
	 * 查询采样地列表
	 * @param pfarm 采样地搜索条件对象(包含页码和记录数)
	 * @return 查询结果
	 */
	@Override
	public PageResultModel<PFarms> list(LFarms lfarm) {
		if (lfarm == null) {
			lfarm = new LFarms();
		}
		int total = farmDAO.count(lfarm);
		PageResultModel<PFarms> resultModel = new PageResultModel<PFarms>(total, lfarm.getRows(), lfarm.getPage());
		// 更新rows属性和page属性,此处page属性不再为页码,改为limit的第一个参数(跳过的记录数)
		lfarm.setRows(resultModel.getRows());
		lfarm.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<Farms> farmsList = farmDAO.selectList(lfarm);
		
		List<PFarms> pfarmList = new ArrayList<PFarms>();
		for (Farms farms : farmsList) {
			pfarmList.add(Transfer.changeToPageModel(farms));
		}
		resultModel.setData(pfarmList);
		return resultModel;
	}

	/**
	 * 删除采样地
	 * @param farmNames 采样地名称
	 * @return 被删除的记录数
	 */
	@Override
	public int delete(String farmNames) {
		if (farmNames == null || farmNames.trim().equals("") || farmNames.trim().equals(",")) {
			return 0;
		}
		String[] farmNameArray = farmNames.split(",");
		int deleteNum = farmDAO.delete(farmNameArray);
		return deleteNum;
	}

	/**
	 * 获取采样地(名称)
	 * @param province 省份
	 * @return 查询集合
	 */
	@Override
	public List<String> getFarmsByProvince(String province) {
		List<String> farmsList = farmDAO.selectAll(province);
		return farmsList;
	}
}
