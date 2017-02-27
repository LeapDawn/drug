package drug.dao;

import java.util.List;

import drug.dto.pageModel.PRole;
import drug.model.Role;

public interface RoleDAO {
    int delete(String[] roleno);

    int insert(Role role);

    Role select(String roleno);
    
    List<Role> selectList(PRole prole);

    int update(Role role);
    
    int count();
    
    List<Role> selectAll();
}