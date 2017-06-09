package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.listModel.LStrainCoding;
import drug.model.StrainCoding;

public interface StrainCodingDAO {
    int delete(String[] strainno);

    int insert(StrainCoding record);

    int update(StrainCoding record);
    
    int count(LStrainCoding strainCoding);
    
    List<StrainCoding> selectList(LStrainCoding strainCoding);
    
    List<String> selectNoInMic(@Param("gram") String gram);
    
    List<String> selectNoInCharacter();
    
    StrainCoding select(@Param("no") String strainno);
    
    List<StrainCoding> selectStrainNoByAliasOrNo(@Param("no") String no, @Param("alias")String alias);
    
    List<String> selectStrainNoByAliasAndNo(@Param("no") String no, @Param("alias")String alias);
    
    List<StrainCoding> selectByNos(String[] strainNos);
    
    List<String> selectStrainNoByAlias(@Param("alias")String alias);
}