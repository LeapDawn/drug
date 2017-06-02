package drug.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import drug.dto.listModel.LStrainMic;
import drug.model.StrainMic;

public interface StrainMicDAO {
	int delete(@Param("strainno")String[] strainno, @Param("gram")String gramStrain);

	int insert(StrainMic record);

	int count(LStrainMic strainMic);

	List<StrainMic> selectList(LStrainMic strainMic);

	int update(StrainMic record);
	
	List<StrainMic> selectByNos(@Param("strainnos")String[] nos, @Param("gram")String gram);
}