package drug.service;

import drug.dto.listModel.LStrainMic;
import drug.dto.pageModel.PStrainMic;
import drug.dto.pageModel.PageResultModel;

public interface StrainMicService {
	
	/**
	 * 新增阴阳性菌株信息
	 * @param pstrainMic
	 */
	public void save(PStrainMic pstrainMic);
	
	/**
	 * 修改阴阳性菌株信息
	 * @param pstrainMic
	 */
	public void update(PStrainMic pstrainMic);
	
	/**
	 * 删除阴阳性菌株信息
	 * @param strainNos
	 * @return
	 */
	public int delete(String strainNos);
	
	/**
	 * 查询阴阳性菌株信息列表
	 * @param lstrainMic
	 * @return
	 */
	public PageResultModel<PStrainMic> list(LStrainMic lstrainMic);
}
