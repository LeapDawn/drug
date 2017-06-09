package drug.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import drug.dto.pageModel.PAnimal;
import drug.model.DetailAnimal;

public interface DetailAnimalDAO {
    int delete(Integer[] animalnos);

    int insert(DetailAnimal record);

    List<DetailAnimal> select(PAnimal animal);

    List<DetailAnimal> selectAll();
    
    Set<Integer> selectNosByName(String[] names);

    Set<Integer> selectNosBySuper(@Param("nos")Set<Integer> nos);
    
    int count();
    
    int update(DetailAnimal record);
}