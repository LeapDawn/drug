package drug.service;

import java.util.List;

import drug.dto.analysisModel.ADrugViewAll;
import drug.dto.listModel.LDrugViewAll;
import drug.dto.pageModel.PDrugViewAll;
import drug.dto.pageModel.PageResultModel;

public interface DrugViewAllService {

	/**
	 * 查询检出率分析信息列表
	 * @param ldrugViewAll
	 * @return
	 */
	public PageResultModel<PDrugViewAll> list(LDrugViewAll ldrugViewAll);
	
	/**
	 * 获取检出率分析结果
	 * @param adrugViewAll
	 * @return
	 */
	public List<?> getAnalysisData(ADrugViewAll adrugViewAll);
}
