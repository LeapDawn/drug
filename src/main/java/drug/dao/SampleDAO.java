package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.listModel.LSample;
import drug.model.Sample;

public interface SampleDAO {
	
    int delete(String[] samplenos);

    int insert(Sample record);

    List<Sample> selectList(LSample sample);

    int update(Sample record);
    
    List<String> selectSampleNos();
    
    int count(LSample sample);
    
    List<String> selectSources();
    
    String selectMaxNo(@Param("str") String str);
    
    Sample select(@Param("sampleno") String sampleno);
    
    List<Sample> selectByNos(String[] nos);
}