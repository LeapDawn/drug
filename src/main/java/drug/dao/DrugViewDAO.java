package drug.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import drug.dto.analysisModel.ADrugView;
import drug.dto.listModel.LDrugView;
import drug.model.DrugView;

public interface DrugViewDAO {
    int count(LDrugView ldrugView);

    List<DrugView> selectList(LDrugView ldrugView);
    
    List<Map<String, Object>> drugResistance(ADrugView drugViewAll);
    
    List<DrugView> selectById(@Param("ids") String[] ids, @Param("gram") String gram);
}