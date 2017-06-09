package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.listModel.LGenView;
import drug.model.GenView;

public interface GenViewDAO {
	
	int count(LGenView lgenview);
	
	List<GenView> selectList(LGenView lgenview);
	
	List<GenView> selectById(@Param("ids")Integer[] ids);
}