package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.pageModel.PUsers;
import drug.model.Users;

public interface UsersDAO {
    int delete(String username);

    int insert(Users record);

    Users selectOne(@Param("username")String userName);

    int update(Users record);
    
    int count();
    
    List<Users> selectList(PUsers users);
}