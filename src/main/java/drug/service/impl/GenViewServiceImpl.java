package drug.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.util.Transfer;
import drug.commons.util.YearStyleUtil;
import drug.dao.DetailAnimalDAO;
import drug.dao.GenViewDAO;
import drug.dto.analysisModel.AGenView;
import drug.dto.listModel.LGenView;
import drug.dto.pageModel.PGenView;
import drug.dto.pageModel.PageResultModel;
import drug.model.GenView;
import drug.service.GenViewService;

@Service("genViewService")
public class GenViewServiceImpl implements GenViewService {

	@Autowired
	private GenViewDAO genViewDAO;
	@Autowired
	private DetailAnimalDAO animalDAO;
	public void setAnimalDAO(DetailAnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}
	public void setGenViewDAO(GenViewDAO genViewDAO) {
		this.genViewDAO = genViewDAO;
	}

	@Override
	public PageResultModel<PGenView> list(LGenView lgenView) {
		if (lgenView == null) {
			lgenView = new LGenView();
		}
		animalNosHandle(lgenView);
		int total = genViewDAO.count(lgenView);
		PageResultModel<PGenView> resultModel =
				new PageResultModel<PGenView>(total, lgenView.getRows(), lgenView.getPage());
		lgenView.setRows(resultModel.getRows());
		lgenView.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<GenView> list = genViewDAO.selectList(lgenView);
		List<PGenView> plist = new ArrayList<PGenView>();
		for (GenView genview : list) {
			plist.add(Transfer.changeToPageModel(genview));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	@Override
	public List<?> getAnalysisData(AGenView agenView) {
		return analyse(agenView, false);
	}
	
	@Override
	public List<?> getIntervalAnalysisData(AGenView agenView) {
		return analyse(agenView, true);
	}
	
	private List<?> analyse(AGenView agenView, boolean isInterval) {
		if (agenView == null) {
			agenView = new AGenView();
		}
		// 待分析基因
		String[] gens = agenView.getGen().split(",");
		List<List<Map<String, Object>>> listPerGen = new ArrayList<List<Map<String, Object>>>();
		for (int i = 0; i < gens.length; i++) {
			agenView.setGen(gens[i]);
			List<Map<String, Object>> tempList = null;
			if (isInterval) {
				tempList = genViewDAO.genIntervalCheckPro(agenView);
			} else {
				tempList = genViewDAO.genCheckPro(agenView);
			}
			
			listPerGen.add(tempList);
		}
		
		List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
		if (listPerGen == null || listPerGen.size() == 0) {
			return returnList;
		}
		
		// 将数据封装成Map
		if (agenView.getStatisticsType().equals("")) {
			// 该分支:没有选择统计类型
			Map<String, Object> map = new HashMap<String, Object>();
			// 初始化chart数组
			ArrayList<List<Map<String, Object>>> initList = new ArrayList<List<Map<String,Object>>>(listPerGen.size());
			for (int j = 0; j < listPerGen.size(); j++) {
				initList.add(new ArrayList<Map<String, Object>>());
			}
			map.put("chart", initList);
			map.put("type", "");
			returnList.add(map);
			List<List<Map<String, Object>>> dataList = initList;
			for (int i = 0; i < listPerGen.size(); i++) {
				List<Map<String, Object>> tempList = listPerGen.get(i);
				List<Map<String, Object>> list = dataList.get(i);
				for (Map<String, Object> map2 : tempList) {
					Map<String,Object> minMap = new HashMap<String, Object>();
					minMap.put("yearStype", YearStyleUtil.getDate(String.valueOf(map2.get("yearStyle")), agenView.getTimeWay()));
					minMap.put("Number", map2.get("Number"));
					minMap.put("Part", map2.get("Part"));
					minMap.put("Total", map2.get("total"));
					minMap.put("gen", gens[i]);
					list.add(minMap);
				}
			}
		} else {
			// 遍历每个基因的分析结果,构建返回给前端的Map
			for (int i = 0; i < listPerGen.size(); i++) {
				// 获取某一个基因的分析结果
				List<Map<String, Object>> tempList = listPerGen.get(i);
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
						ArrayList<List<Map<String, Object>>> initList = new ArrayList<List<Map<String,Object>>>(listPerGen.size());
						for (int j = 0; j < listPerGen.size(); j++) {
							initList.add(new ArrayList<Map<String, Object>>());
						}
						targetMap.put("chart", initList);
					}

					// 将记录的值插入map
					@SuppressWarnings("unchecked")
					List<List<Map<String,Object>>> dataList = (List<List<Map<String, Object>>>) targetMap.get("chart");						
					List<Map<String, Object>> list = dataList.get(i);
					Map<String,Object> minMap = new HashMap<String, Object>();
					minMap.put("yearStype", YearStyleUtil.getDate(String.valueOf(map.get("yearStyle")), agenView.getTimeWay()));
					minMap.put("Number", map.get("Number"));
					minMap.put("Part", map.get("Part"));
					minMap.put("Total", map.get("total"));
					minMap.put("strain", gens[i]);
					list.add(minMap);
				}
			}
		}
		return returnList;
	}
	
	/**
	 * 动物筛选条件，对大类做处理
	 * @param ldrugView
	 */
	private void animalNosHandle(LGenView lgenView) {
		String[] animalNames = lgenView.getAnimalname();
		if (animalNames != null && animalNames.length > 0) {
			Set<Integer> nos = animalDAO.selectNosByName(animalNames);
			Set<Integer> superNos = new HashSet<Integer>();
			for (Integer i : nos) {
				if (i % 10 == 0) {
					superNos.add(i);
				}
			}
			if (superNos.size() > 0) {
				nos.addAll(animalDAO.selectNosBySuper(superNos));
			}
			lgenView.setAnimalNos(nos);
		}
	}
}
