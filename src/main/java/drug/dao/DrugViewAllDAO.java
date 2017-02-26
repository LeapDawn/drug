package drug.dao;

import java.util.List;
import java.util.Map;

import drug.dto.analysisModel.ADrugViewAll;
import drug.dto.listModel.LDrugViewAll;
import drug.model.DrugViewAll;

public interface DrugViewAllDAO {
	
	int count(LDrugViewAll drugViewAll);
	
	List<DrugViewAll> selectList(LDrugViewAll drugViewAll);
	
	List<Map<String, Object>> strainCheckPro(ADrugViewAll drugViewAll);
	
	List<DrugViewAll> selectById(String[] ids);
}