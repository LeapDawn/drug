package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.pageModel.PStrainToNotes;
import drug.model.StrainToNotes;

public interface StrainToNotesDAO {
    int delete(String[] strainNotes);

    int insert(StrainToNotes record);

    List<StrainToNotes> selectList(PStrainToNotes strainToNotes);

    int count();
    
    StrainToNotes selectNote(@Param("category") String category, @Param("strain")String strain);
    
    List<String> selectCategory(@Param("gram")String gram);
    
    List<String> selectStrains(@Param("category") String category);
    
    int update(StrainToNotes record);
    
    StrainToNotes select(String note);
    
    List<StrainToNotes> selectAll();
}