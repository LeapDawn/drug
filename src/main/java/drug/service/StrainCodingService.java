package drug.service;

import java.util.List;

import drug.dto.listModel.LStrainCoding;
import drug.dto.pageModel.PStrainCoding;
import drug.dto.pageModel.PageResultModel;

public interface StrainCodingService {
	
	/**
	 * 新增菌株信息
	 * @param strain
	 */
	public void save(PStrainCoding strain);
	
	/**
	 * 更新菌株信息
	 * @param strain
	 */
	public void update(PStrainCoding strain);
	
	/**
	 * 删除菌株信息
	 * @param strainNos
	 * @return
	 */
	public int delete(String strainNos);
	
	/**
	 * 查询菌株信息列表
	 * @param lstrain
	 * @return
	 */
	public PageResultModel<PStrainCoding> list(LStrainCoding lstrain);
	
	/**
	 * 获取没有在MIC表中出现的菌株编号
	 * @param gram 格兰氏阴阳性
	 * @return
	 */
	public List<String> getStringNoNotInMic(String gram);
	
	/**
	 * 获取没有出现在基因表中出现的菌株编号
	 * @return
	 */
	public List<String> getStringNoNotInCharacter();
	
	/**
	 * 根据菌株编号获取菌株基本信息
	 * @param strainNo 菌株编号
	 */
	public PStrainCoding selectOne(String strainNo);
}
