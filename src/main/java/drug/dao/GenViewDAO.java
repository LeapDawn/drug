package drug.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import drug.dto.analysisModel.AGenView;
import drug.dto.listModel.LGenView;
import drug.model.GenView;

public interface GenViewDAO {

	int count(LGenView lgenview);

	List<GenView> selectList(LGenView lgenview);

	List<GenView> selectById(@Param("ids") Integer[] ids);

	List<Map<String, Object>> genCheckPro(AGenView agenView);

	List<Map<String, Object>> genIntervalCheckPro(AGenView agenView);
}