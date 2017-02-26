package drug.dao;

import java.util.List;

import drug.dto.listModel.LFarms;
import drug.model.Farms;

public interface FarmsDAO {
    public int delete(String[] farmname);

    public int insert(Farms farm);

    public int update(Farms farm);

    public List<Farms> selectList(LFarms farm);
    
    public List<String> selectAll(String province);
    
    public int count(LFarms farm);
    
    public List<Farms> selectByNames(String[] farmname);
}