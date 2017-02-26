package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.dao.CollectionPartDAO;
import drug.dto.pageModel.PPart;
import drug.dto.pageModel.PageResultModel;
import drug.model.CollectionPart;
import drug.service.PartService;

@Service("partService")
public class PartServiceImpl implements PartService{

	@Autowired
	private CollectionPartDAO partDAO;
	public void setPartDAO(CollectionPartDAO partDAO) {
		this.partDAO = partDAO;
	}

	@Override
	public void save(PPart ppart) {
		CollectionPart part = new CollectionPart();
		BeanUtils.copyProperties(ppart, part);
		partDAO.insert(part);
	}

	@Override
	public void update(PPart ppart) {
		CollectionPart part = new CollectionPart();
		BeanUtils.copyProperties(ppart, part);
		partDAO.update(part);
	}

	@Override
	public int delete(String partNos) {
		if (partNos == null || "".equals(partNos.trim()) || ",".equals(partNos.trim())) {
			return 0;
		}
		String[] nos = partNos.trim().split(",");
		int delNum = partDAO.delete(nos);
		return delNum;
	}

	@Override
	public List<CollectionPart> getParts() {
		List<CollectionPart> list = partDAO.selectAll();
		return list;
	}

	@Override
	public PageResultModel<PPart> list(PPart ppart) {
		if (ppart == null) {
			ppart = new PPart();
		}
		int total = partDAO.count();
		PageResultModel<PPart> resultModel = 
				new PageResultModel<PPart>(total, ppart.getRows(), ppart.getPage());
		ppart.setRows(resultModel.getRows());
		ppart.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<CollectionPart> list = partDAO.select(ppart);
		List<PPart> plist = new ArrayList<PPart>();
		for (CollectionPart collectionPart : list) {
			plist.add(this.changetoPageModel(collectionPart));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	private PPart changetoPageModel(CollectionPart part) {
		PPart panimal = new PPart();
		BeanUtils.copyProperties(part, panimal);
		return panimal;
	}
}
