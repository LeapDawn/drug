package drug.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.util.Transfer;
import drug.commons.util.YearStyleUtil;
import drug.dao.DrugViewAllDAO;
import drug.dto.analysisModel.ADrugViewAll;
import drug.dto.listModel.LDrugViewAll;
import drug.dto.pageModel.PDrugViewAll;
import drug.dto.pageModel.PageResultModel;
import drug.model.DrugViewAll;
import drug.service.DrugViewAllService;

@Service("drugViewAllService")
public class DrugViewAllServiceImpl implements DrugViewAllService {

	@Autowired
	private DrugViewAllDAO drugViewAllDAO;

	public void setDrugViewAllDAO(DrugViewAllDAO drugViewAllDAO) {
		this.drugViewAllDAO = drugViewAllDAO;
	}

	@Override
	public PageResultModel<PDrugViewAll> list(LDrugViewAll ldrugViewAll) {
		if (ldrugViewAll == null) {
			ldrugViewAll = new LDrugViewAll();
		}
		int total = drugViewAllDAO.count(ldrugViewAll);
		PageResultModel<PDrugViewAll> resultModel = new PageResultModel<PDrugViewAll>(
				total, ldrugViewAll.getRows(), ldrugViewAll.getPage());
		ldrugViewAll.setRows(resultModel.getRows());
		ldrugViewAll.setPage((resultModel.getCurrentPage() - 1)
				* resultModel.getRows());

		List<DrugViewAll> list = drugViewAllDAO.selectList(ldrugViewAll);
		List<PDrugViewAll> plist = new ArrayList<PDrugViewAll>();
		for (DrugViewAll drugViewAll : list) {
			plist.add(Transfer.changeToPageModel(drugViewAll));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	@Override
	public List<?> getAnalysisData(ADrugViewAll adrugViewAll) {
		if (adrugViewAll == null) {
			adrugViewAll = new ADrugViewAll();
		}
		// 待分析的菌属/菌种
		String strain = adrugViewAll.getStrain();
		String[] strains = strain.split(",");
		// 对每个菌属/菌种进行分析
		List<List<Map<String, Object>>> listPerStrain = new ArrayList<List<Map<String, Object>>>();
		for (int i = 0; i < strains.length; i++) {
			adrugViewAll.setStrain(strains[i]);
			List<Map<String, Object>> tempList = drugViewAllDAO
					.strainCheckPro(adrugViewAll);
			listPerStrain.add(tempList);
		}

		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		
		if (listPerStrain.isEmpty()) {
			return returnList;
		}

		// 将数据封装成Map
		if (adrugViewAll.getStatisticsType().equals("")) {
			// 该分支:没有选择统计类型
			Map<String, Object> map = new HashMap<String, Object>();
			// 初始化chart数组
			ArrayList<List<Map<String, Object>>> initList = new ArrayList<List<Map<String,Object>>>(listPerStrain.size());
			for (int j = 0; j < listPerStrain.size(); j++) {
				initList.add(new ArrayList<Map<String, Object>>());
			}
			map.put("chart", initList);
			map.put("type", "");
			returnList.add(map);
			List<List<Map<String, Object>>> dataList = initList;
			for (int i = 0; i < listPerStrain.size(); i++) {
				List<Map<String, Object>> tempList = listPerStrain.get(i);
				List<Map<String, Object>> list = dataList.get(i);
				for (Map<String, Object> map2 : tempList) {
					Map<String,Object> minMap = new HashMap<String, Object>();
					minMap.put("yearStype", YearStyleUtil.getDate(String.valueOf(map2.get("yearStyle")), adrugViewAll.getTimeWay()));
					minMap.put("Number", map2.get("Number"));
					minMap.put("Part", map2.get("Part"));
					minMap.put("Total", map2.get("total"));
					minMap.put("strain", strains[i]);
					list.add(minMap);
				}
			}
		} else {
			// 遍历每个菌属/菌种的分析结果,构建返回给前端的Map
			for (int i = 0; i < listPerStrain.size(); i++) {
				// 获取某一个菌属/菌种的分析结果
				List<Map<String, Object>> tempList = listPerStrain.get(i);
				// 遍历该分析结果的记录
				for (Map<String, Object> map : tempList) {
					// 获取相对应的type(动物/养殖场/省份/...)
					String type = (String) map.get("type");
					Map<String, Object> targetMap = null;
					for (Map<String,Object> map2 : returnList) {
						if (map2.get("type").equals(type)) {
							targetMap = map2;
							break;
						}
					}
					// 若没有获取到对应的type,初始化该type
					if (targetMap == null) {
						targetMap = new HashMap<String, Object>();
						returnList.add(targetMap);
						targetMap.put("type", type);
						ArrayList<List<Map<String, Object>>> initList = new ArrayList<List<Map<String,Object>>>(listPerStrain.size());
						for (int j = 0; j < listPerStrain.size(); j++) {
							initList.add(new ArrayList<Map<String, Object>>());
						}
						targetMap.put("chart", initList);
					}

					// 将记录的值插入map
					@SuppressWarnings("unchecked")
					List<List<Map<String,Object>>> dataList = (List<List<Map<String, Object>>>) targetMap.get("chart");						
					List<Map<String, Object>> list = dataList.get(i);
					Map<String,Object> minMap = new HashMap<String, Object>();
					minMap.put("yearStype", YearStyleUtil.getDate(String.valueOf(map.get("yearStyle")), adrugViewAll.getTimeWay()));
					minMap.put("Number", map.get("Number"));
					minMap.put("Part", map.get("Part"));
					minMap.put("Total", map.get("total"));
					minMap.put("strain", strains[i]);
					list.add(minMap);
				}
			}
		}
		return returnList;
	}
}
