package drug.dao;

import java.util.List;

import drug.model.Function;

public interface FunctionDAO {
	
    Function selectByRole(String roleno);

    List<Function> selectAll();
}