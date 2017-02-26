package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.pageModel.PSource;
import drug.model.Source;

public interface SourceDAO {
    int delete(Integer[] sourceno);

    int insert(Source source);

    List<Source> select(PSource source);
    
    List<Source> selectAll();

    List<Integer> selectNosInSample();
    
    int update(Source record);
    
    int count();
    
    int selectNoByName(@Param("sourceName") String sourceName);
}