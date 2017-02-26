package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.util.StringUtil;
import drug.dao.DetailAnimalDAO;
import drug.dto.pageModel.PAnimal;
import drug.dto.pageModel.PageResultModel;
import drug.model.DetailAnimal;
import drug.service.AnimalService;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService{

	@Autowired
	private DetailAnimalDAO animalDAO;
	public void setAnimalDAO(DetailAnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}

	@Override
	public void save(PAnimal panimal) {
		DetailAnimal animal = new DetailAnimal();
		BeanUtils.copyProperties(panimal, animal);
		animalDAO.insert(animal);
	}

	@Override
	public void update(PAnimal panimal) {
		DetailAnimal animal = new DetailAnimal();
		BeanUtils.copyProperties(panimal, animal);
		animalDAO.update(animal);
	}

	@Override
	public int delete(String animalNos) {
		if (animalNos == null || "".equals(animalNos.trim()) || ",".equals(animalNos.trim())) {
			return 0;
		}
		String[] animalNosStr = animalNos.split(",");
		List<Integer> delList =  new ArrayList<Integer>();
		for (String str : animalNosStr) {
			if (StringUtil.isInt(str)) {
				delList.add(Integer.valueOf(str));
			}
		}
		
		Integer[] nos = new Integer[delList.size()];
		for (int i = 0; i < nos.length; i++) {
			nos[i] = delList.get(i);
		}
		int deleteNum = animalDAO.delete(nos);
		return deleteNum;
	}

	@Override
	public List<DetailAnimal> getAnimals() {
		List<DetailAnimal> list = animalDAO.selectAll();
		return list;
	}

	@Override
	public PageResultModel<PAnimal> list(PAnimal panimal) {
		if (panimal == null) {
			panimal = new PAnimal();
		}
		int total = animalDAO.count();
		PageResultModel<PAnimal> resultModel = 
				new PageResultModel<PAnimal>(total, panimal.getRows(), panimal.getPage());
		panimal.setRows(resultModel.getRows());
		panimal.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<DetailAnimal> list = animalDAO.select(panimal);
		List<PAnimal> plist = new ArrayList<PAnimal>();
		for (DetailAnimal animal : list) {
			plist.add(this.changetoPageModel(animal));
		}
		resultModel.setData(plist);
		return resultModel;
	}
	
	private PAnimal changetoPageModel(DetailAnimal animal) {
		PAnimal panimal = new PAnimal();
		BeanUtils.copyProperties(animal, panimal);
		return panimal;
	}

}
