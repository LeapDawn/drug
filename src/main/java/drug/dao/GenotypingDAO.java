package drug.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import drug.model.Genotyping;

public interface GenotypingDAO {
	int insert(Genotyping record);
	
	int update(Genotyping record);
	
	int updateGenotyping(@Param("src")String src, @Param("tar")String tar);
	
    int delete(Integer[] id);

    int count(@Param("genotyping")String genotyping);
    
    int countGenotypings(@Param("genotypingSet")Set<String> genotyping);
    
	List<Genotyping> selectList(@Param("genotyping") String genotyping,
			@Param("skip") int skip, @Param("rows")int rows);
}