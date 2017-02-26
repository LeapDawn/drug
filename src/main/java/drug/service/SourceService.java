package drug.service;

import java.util.List;

import drug.dto.pageModel.PSource;
import drug.dto.pageModel.PageResultModel;
import drug.model.Source;

public interface SourceService {
	
	/**
	 * 新增来源
	 * @param psource
	 */
	public void save(PSource psource);
	
	/**
	 * 更新来源信息
	 * @param psource
	 */
	public void update(PSource psource);
	
	/**
	 * 删除来源记录
	 * @param sourceNos
	 * @return 被删除的数目
	 */
	public int delete(String sourceNos);
	
	/**
	 * 获取来源列表(分页)
	 * @param psource
	 * @return
	 */
	public PageResultModel<PSource> list(PSource psource);
	
	/**
	 * 获取来源集合
	 * @return
	 */
	public List<Source> getSources();
}
