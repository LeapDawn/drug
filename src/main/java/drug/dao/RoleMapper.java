package drug.dao;

import drug.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleno);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleno);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}