package drug.service;

import java.util.List;

import drug.dto.pageModel.PStandardMic;
import drug.dto.pageModel.PageResultModel;

public interface StandardMicService {

	/**
	 * 新增药物标准信息
	 * @param pstandardMic
	 */
	public void save(PStandardMic pstandardMic);
	
	/**
	 * 更新药物标准信息
	 * @param pstandardMic
	 */
	public void update(PStandardMic pstandardMic);
	
	/**
	 * 删除药物标准信息
	 * @param pstandardMic
	 * @return
	 */
	public int delete(PStandardMic[] pstandardMic);
	
	/**
	 * 查询药物标准信息列表
	 * @param pstandardMic
	 * @return
	 */
	public PageResultModel<PStandardMic> list(PStandardMic pstandardMic);
	
	/**
	 * 获取标准表中的菌株
	 * @return
	 */
	public List<String> getStrains();
	
	
	/**
	 * 获取标准表中的标准名
	 * @return
	 */
	public List<String> getStandards();
	
	/**
	 * 获取标准表中的药物
	 * @return
	 */
	public List<String> getDrug();
}
