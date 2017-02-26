package drug.service;

import java.util.List;

import drug.dto.analysisModel.ADrugView;
import drug.dto.listModel.LDrugView;
import drug.dto.pageModel.PDrugView;
import drug.dto.pageModel.PageResultModel;

public interface DrugViewService {
	
	/**
	 * 查询耐药性分析信息列表
	 * @param ldrugView
	 * @return
	 */
	public PageResultModel<PDrugView> list(LDrugView ldrugView);

	/**
	 * 获取耐药性分析结果
	 * @param adrugViewAll
	 * @return
	 */
	public List<?> getAnalysisData(ADrugView adrugView);
}
