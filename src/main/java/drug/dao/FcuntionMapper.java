package drug.dao;

import drug.model.Fcuntion;

public interface FcuntionMapper {
    int deleteByPrimaryKey(String functionno);

    int insert(Fcuntion record);

    int insertSelective(Fcuntion record);

    Fcuntion selectByPrimaryKey(String functionno);

    int updateByPrimaryKeySelective(Fcuntion record);

    int updateByPrimaryKey(Fcuntion record);
}