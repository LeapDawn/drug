package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.model.GenotypingDir;

public interface GenotypingDirDAO {
	int insert(GenotypingDir record);
	
    int delete(Integer[] id);
    
    int update(GenotypingDir record);
    
    List<GenotypingDir> selectByIds(Integer[] id);
    
    String[] selectGentyping3ByIds(Integer[] id);
    
    List<String> selectGentyping1();
    
    List<String> selectGentyping2(@Param("genotyping1")String gentyping1);
    
    List<String> selectGentyping3(@Param("genotyping2")String gentyping2);
    
    List<GenotypingDir> selectList(@Param("skip")Integer skip, @Param("rows")Integer rows);
    
    Integer count();
}