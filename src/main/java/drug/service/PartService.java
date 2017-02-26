package drug.service;

import java.util.List;

import drug.dto.pageModel.PPart;
import drug.dto.pageModel.PageResultModel;
import drug.model.CollectionPart;

public interface PartService {

	/**
	 * 新增采集部位编号
	 * @param ppart
	 */
	public void save(PPart ppart);
	
	/**
	 * 更新采集部位编号信息
	 * @param ppart
	 */
	public void update(PPart ppart);
	
	/**
	 * 删除采集部位编号
	 * @param partNos
	 * @return 
	 */
	public int delete(String partNos);
	
	/**
	 * 获取采集部位编号集合
	 * @return
	 */
	public List<CollectionPart> getParts();
	
	/**
	 * 查询采集部位编号列车
	 * @param ppart
	 * @return
	 */
	public PageResultModel<PPart> list(PPart ppart);
}
