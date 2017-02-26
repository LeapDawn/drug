package drug.dao;

import java.util.List;

import drug.dto.pageModel.PLinkURL;
import drug.model.LinkURL;

public interface LinkURLDAO {
	
	int insert(LinkURL linkURL);
	
	int update(LinkURL linkURL);
	
	int delete(String[] names);
	
	List<LinkURL> selectList(PLinkURL linkURL);
	
	int count();
}
