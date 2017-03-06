package drug.dao;

import java.util.List;

import drug.model.Function;

public interface FunctionDAO {
	
    List<String> selectURLByRole(String roleno);

    List<Function> selectAll();
    
}