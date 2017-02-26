package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import drug.dao.StrainToNotesDAO;
import drug.dto.pageModel.PStrainToNotes;
import drug.dto.pageModel.PageResultModel;
import drug.model.StrainToNotes;
import drug.service.StrainToNotesService;

@Service("strainToNotesService")
public class StrainToNotesServiceImpl implements StrainToNotesService{

	@Autowired
	private StrainToNotesDAO strainDAO;
	public void setStrainDAO(StrainToNotesDAO strainDAO) {
		this.strainDAO = strainDAO;
	}
	
	@Override
	public void save(PStrainToNotes pstrain) {
		String strainnotes = pstrain.getStrainnotes();
		StrainToNotes strain = strainDAO.select(strainnotes);
		if (strain != null) {
			throw new DuplicateKeyException("");
		}
		strain = new StrainToNotes();
		BeanUtils.copyProperties(pstrain, strain);
		strainDAO.insert(strain);
	}
	
	@Override
	public void update(PStrainToNotes pstrain) {
		StrainToNotes strain = new StrainToNotes();
		BeanUtils.copyProperties(pstrain, strain);
		strainDAO.update(strain);
	}
	
	@Override
	public int delete(String notes) {
		if (notes == null || notes.trim().equals("") || notes.trim().equals(",")){
			return 0;
		}
		
		String[] noteArray = notes.trim().split(",");
		int deleteNum = strainDAO.delete(noteArray);
		return deleteNum;
	}
	
	@Override
	public PageResultModel<PStrainToNotes> list(PStrainToNotes pstrain) {
		if (pstrain == null) {
			pstrain = new PStrainToNotes();
		}
		
		int total = strainDAO.count();
		PageResultModel<PStrainToNotes> resultModel = 
				new PageResultModel<PStrainToNotes>(total, pstrain.getRows(), pstrain.getPage());
		pstrain.setRows(resultModel.getRows());
		pstrain.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<StrainToNotes> list = strainDAO.selectList(pstrain);
		List<PStrainToNotes> plist = new ArrayList<PStrainToNotes>();
		for (StrainToNotes strainToNotes : list) {
			plist.add(this.changetoPageModel(strainToNotes));
		}
		resultModel.setData(plist);
		return resultModel;
		
	}
	
	@Override
	public List<String> getStrains(String category) {
		return strainDAO.selectStrains(category);
	}
	
	@Override
	public List<String> getCategory(String gram) {
		return strainDAO.selectCategory(gram);		
	}
	
	private PStrainToNotes changetoPageModel(StrainToNotes strain) {
		PStrainToNotes pstrain = new PStrainToNotes();
		BeanUtils.copyProperties(strain, pstrain);
		return pstrain;
	}

	@Override
	public List<String> getCategoryAndStrains() {
		List<String> strains = strainDAO.selectStrains("");
		List<String> category = strainDAO.selectCategory("");
		List<String> list = new ArrayList<String>(category);
		list.addAll(strains);
		list.remove("");
		return list;
	}
}
