package drug.dao;

import drug.model.Medice;

public interface MediceMapper {
    int deleteByPrimaryKey(String medice);

    int insert(Medice record);

    int insertSelective(Medice record);

    Medice selectByPrimaryKey(String medice);

    int updateByPrimaryKeySelective(Medice record);

    int updateByPrimaryKey(Medice record);
}