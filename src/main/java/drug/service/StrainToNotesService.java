package drug.service;

import java.util.List;

import drug.dto.pageModel.PStrainToNotes;
import drug.dto.pageModel.PageResultModel;

public interface StrainToNotesService {

	/**
	 * 新增菌种编号信息
	 * @param pstrain
	 */
	public void save(PStrainToNotes pstrain);

	/**
	 * 更新菌种编号信息
	 * @param pStrain
	 */
	public void update(PStrainToNotes pStrain);

	/**
	 * 删除菌种编号信息
	 * @param notes
	 * @return 
	 */
	public int delete(String notes);

	/**
	 * 查询菌种编号信息列表
	 * @param pstrain
	 * @return 
	 */
	public PageResultModel<PStrainToNotes> list(PStrainToNotes pstrain);

	/**
	 * 根据菌属名获取菌种名集合
	 * @param category 菌属名
	 */
	public List<String> getStrains(String category);

	/**
	 * 根据格兰氏阴阳性获取菌属名集合
	 * @param gram 格兰氏阴阳性
	 */
	public List<String> getCategory(String gram);
	
	/**
	 * 获取菌属和菌株
	 * @return
	 */
	public List<String> getCategoryAndStrains();
}
