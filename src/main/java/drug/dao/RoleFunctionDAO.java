package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleFunctionDAO {
    int deleteByRole(String roleno);

    int insert(@Param("roleno") String roleno, @Param("functions")List<String> functions);

    List<String> selectByRole(String roleno);
}