package drug.dao;

import java.util.List;

import drug.dto.pageModel.PStandardMic;
import drug.model.StandardMic;
import drug.model.StandardMicKey;

public interface StandardMicDAO {
    int delete(StandardMicKey[] keys);

    int insert(StandardMic record);

    List<StandardMic> selectList(PStandardMic pstandardMic);
    
    List<StandardMic> selectAll();

    int update(StandardMic record);

    int count(PStandardMic pstandardMic);
    
    List<String> selectStrain();
    
    List<String> selectStandard();
    
    List<String> selectDrug();
    
//    List<PStandardMic> selectPage(PStandardMic pstandardMic);
}