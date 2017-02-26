package drug.service;

import drug.dto.listModel.LStrainCharacter;
import drug.dto.pageModel.PStrainCharacter;
import drug.dto.pageModel.PageResultModel;

public interface StrainCharacterService {

	/**
	 * 新增基因信息
	 * @param character
	 */
	public void save(PStrainCharacter pcharacter);

	/**
	 * 更新基因信息
	 * @param character
	 */
	public void update(PStrainCharacter pcharacter);

	/**
	 * 删除基因信息
	 * @param strainnos
	 * @return
	 */
	public int delete(String ids);

	/**
	 * 查询基因信息列表
	 * @param lcharacter
	 * @return
	 */
	public PageResultModel<PStrainCharacter> list(LStrainCharacter lcharacter);
}
