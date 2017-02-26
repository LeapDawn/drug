package drug.dao;

import java.util.List;

import drug.dto.listModel.LStrainCharacter;
import drug.model.StrainCharacter;

public interface StrainCharacterDAO {
    int delete(Integer[] ids);

    int insert(StrainCharacter record);

    int update(StrainCharacter record);
    
    int count(LStrainCharacter character);
    
    List<StrainCharacter> selectList(LStrainCharacter character);
    
    List<StrainCharacter> selectByIds(String[] ids);
}