package drug.dao;

import java.util.List;

import drug.dto.pageModel.PAnimal;
import drug.model.DetailAnimal;

public interface DetailAnimalDAO {
    int delete(Integer[] animalnos);

    int insert(DetailAnimal record);

    List<DetailAnimal> select(PAnimal animal);

    List<DetailAnimal> selectAll();

    int count();
    
    int update(DetailAnimal record);
}