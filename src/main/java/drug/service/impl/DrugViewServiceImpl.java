package drug.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.Transfer;
import drug.commons.util.YearStyleUtil;
import drug.dao.DrugViewDAO;
import drug.dao.StandardMicDAO;
import drug.dto.analysisModel.ADrugView;
import drug.dto.listModel.LDrugView;
import drug.dto.pageModel.PDrugView;
import drug.dto.pageModel.PageResultModel;
import drug.model.DrugView;
import drug.model.StandardMic;
import drug.service.DrugViewService;

@Service("drugViewService")
public class DrugViewServiceImpl implements DrugViewService {

	@Autowired
	private DrugViewDAO drugViewDAO;
	public void setDrugViewDAO(DrugViewDAO drugViewDAO) {
		this.drugViewDAO = drugViewDAO;
	}
	
	@Autowired
	private StandardMicDAO strandardMicDAO;
	public void setStrandardMicDAO(StandardMicDAO strandardMicDAO) {
		this.strandardMicDAO = strandardMicDAO;
	}

	@Override
	public PageResultModel<PDrugView> list(LDrugView ldrugView) {
		if (ldrugView == null) {
			ldrugView = new LDrugView();
		}
		int total = drugViewDAO.count(ldrugView);
		PageResultModel<PDrugView> resultModel =
				new PageResultModel<PDrugView>(total, ldrugView.getRows(), ldrugView.getPage());
		ldrugView.setRows(resultModel.getRows());
		ldrugView.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<DrugView> list = drugViewDAO.selectList(ldrugView);
		List<PDrugView> plist = new ArrayList<PDrugView>();
		for (DrugView drugView : list) {
			plist.add(Transfer.changeToPageModel(drugView));
		}
		resultModel.setData(plist);
		return resultModel;
	}
	
	@Override
	public List<?> getAnalysisData(ADrugView adrugView) {
		if (adrugView == null) {
			throw new DataViolationException("");
		}
		String drug = adrugView.getDrug();
		String strain = adrugView.getStrain();
		String[] drugs = drug.split(",");
		Float[] medlimits = new Float[drugs.length];
		List<StandardMic> StandardMicList = strandardMicDAO.selectAll();
		Boolean flag;   // 药物-菌种对应标识
		// 获取药物-菌种对应的折点
		for (int i = 0; i < drugs.length; i++) {
			flag = false;
			for (StandardMic standardMic : StandardMicList) {
				if (standardMic.getStrainname().equals(strain) 
						&& standardMic.getMedicalname().equals(drugs[i])) {
					medlimits[i] = standardMic.getMedlimit();
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new DataViolationException("药物标准[" 
						+ drugs[i] + "-" + strain + "]对应的药物标准不存在,请于[]编号管理-药物标准信息]查询正确的药物标准");
			}
		}
		
		// 对每个药物-菌种进行分析
		List<List<Map<String, Object>>> listPerDrug = new ArrayList<List<Map<String, Object>>>();
		for (int i = 0; i < drugs.length; i++) {
			adrugView.setDrug(drugs[i]);
			adrugView.setMedLimit(medlimits[i]);
			List<Map<String, Object>> tempList = drugViewDAO.drugResistance(adrugView);
			listPerDrug.add(tempList);
		}
		
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		if (adrugView.getStatisticsType().equals("")) {
			// 该分支:没有选择统计类型
			Map<String, Object> map = new HashMap<String, Object>();
			// 初始化chart
			ArrayList<List<Map<String, Object>>> initList = new ArrayList<List<Map<String,Object>>>(listPerDrug.size());
			for (int j = 0; j < listPerDrug.size(); j++) {
				initList.add(new ArrayList<Map<String, Object>>());
			}
			map.put("chart", initList);
			map.put("type", "");
			returnList.add(map);
			List<List<Map<String, Object>>> dataList = initList;
			for (int i = 0; i < listPerDrug.size(); i++) {
				List<Map<String, Object>> tempList = listPerDrug.get(i);
				List<Map<String, Object>> list = dataList.get(i);
				for (Map<String, Object> map2 : tempList) {
					Map<String,Object> minMap = new HashMap<String, Object>();
					minMap.put("yearStype", YearStyleUtil.getDate(String.valueOf(map2.get("yearStyle")), adrugView.getTimeWay()));
					minMap.put("Number", map2.get("Number"));
					minMap.put("Part", map2.get("Part"));
					minMap.put("Total", map2.get("total"));
					minMap.put("drug", drugs[i]);
					list.add(minMap);
				}
			}
		} else {
			// 遍历每个药物-菌种分析结果
			for (int i = 0; i < listPerDrug.size(); i++) {
				// 获取某个药物-菌种分析结果
				List<Map<String, Object>> tempList = listPerDrug.get(i);
				// 遍历该分析结果的数据
				for (Map<String, Object> map : tempList) {
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
						ArrayList<List<Map<String, Object>>> initList = new ArrayList<List<Map<String,Object>>>(listPerDrug.size());
						for (int j = 0; j < listPerDrug.size(); j++) {
							initList.add(new ArrayList<Map<String, Object>>());
						}
						targetMap.put("chart", initList);
					}
					
					// 将记录的值插入map
					@SuppressWarnings("unchecked")
					List<List<Map<String,Object>>> dataList = (List<List<Map<String, Object>>>) targetMap.get("chart");			
					List<Map<String, Object>> list = dataList.get(i);
					Map<String,Object> minMap = new HashMap<String, Object>();
					minMap.put("yearStype", YearStyleUtil.getDate(String.valueOf(map.get("yearStyle")), adrugView.getTimeWay()));
					minMap.put("Number", map.get("Number"));
					minMap.put("Part", map.get("Part"));
					minMap.put("Total", map.get("total"));
					minMap.put("drug", drugs[i]);
					list.add(minMap);
				}
			}
		}
		return returnList;
	}
}
