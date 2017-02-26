package drug.dao;

import drug.model.RoleFunction;

public interface RoleFunctionMapper {
    int deleteByPrimaryKey(Integer rfno);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    RoleFunction selectByPrimaryKey(Integer rfno);

    int updateByPrimaryKeySelective(RoleFunction record);

    int updateByPrimaryKey(RoleFunction record);
}