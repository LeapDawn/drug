package drug.service;

import java.util.List;

import drug.dto.analysisModel.AGenView;
import drug.dto.listModel.LGenView;
import drug.dto.pageModel.PGenView;
import drug.dto.pageModel.PageResultModel;

public interface GenViewService {
	
	/**
	 * 查询基因检出率信息列表
	 * @param lgenView
	 * @return
	 */
	public PageResultModel<PGenView> list(LGenView lgenView);

	/**
	 * 获取基因检出率分析结果
	 * @param agenView
	 * @return
	 */
	public List<?> getAnalysisData(AGenView agenView);
}
