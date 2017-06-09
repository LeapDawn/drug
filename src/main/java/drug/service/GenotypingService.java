package drug.service;

import drug.dto.pageModel.PGenotyping;
import drug.dto.pageModel.ResultPageModel;
import drug.model.Genotyping;

public interface GenotypingService {

	public void save(PGenotyping pgtp);

	public void update(PGenotyping pgtp);
	
	public int delete(String ids);
	
	public ResultPageModel<Genotyping> list(PGenotyping pgtp);
}
