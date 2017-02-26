package drug.service;

import drug.dto.pageModel.PLinkURL;
import drug.dto.pageModel.PageResultModel;
import drug.model.LinkURL;

public interface LinkURLService {
	
	/**
	 * 新增链接
	 * @param linkURL
	 */
	public void save(PLinkURL linkURL);
	
	/**
	 * 更新链接信息
	 * @param linkURL
	 */
	public void update(PLinkURL linkURL);
	
	/**
	 * 删除链接信息
	 * @param names
	 * @return
	 */
	public int delete(String names);
	
	/**
	 * 查询链接信息列表
	 * @param linkURL
	 * @return
	 */
	public PageResultModel<LinkURL> list(PLinkURL linkURL); 
}
