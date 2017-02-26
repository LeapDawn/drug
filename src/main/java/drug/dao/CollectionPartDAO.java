package drug.dao;

import java.util.List;

import drug.dto.pageModel.PPart;
import drug.model.CollectionPart;

public interface CollectionPartDAO {
	public int delete(String[] partnos);

	public int insert(CollectionPart record);

	public List<CollectionPart> select(PPart part);

	public int update(CollectionPart record);

	public int count();
	
	public List<CollectionPart> selectAll();
}