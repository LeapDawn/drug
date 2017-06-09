package drug.service;

import java.util.List;

import drug.dto.pageModel.PGenotypingDir;
import drug.dto.pageModel.ResultPageModel;
import drug.model.GenotypingDir;

public interface GenotypingDirService {
	
	public void save(PGenotypingDir gtpDir);
	
	public void update(PGenotypingDir gtpDir);
	
	public int delete(String ids);
	
	public List<String> getGenotyping1();
	
	public List<String> getGenotyping2(String gentyping1);
	
	public List<String> getGenotyping3(String gentyping2);
	
	public ResultPageModel<GenotypingDir> list(PGenotypingDir page);
}
