package drug.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.util.Transfer;
import drug.dao.DetailAnimalDAO;
import drug.dao.GenViewDAO;
import drug.dto.analysisModel.AGenView;
import drug.dto.listModel.LGenView;
import drug.dto.pageModel.PGenView;
import drug.dto.pageModel.PageResultModel;
import drug.model.GenView;
import drug.service.GenViewService;

@Service("genViewService")
public class GenViewServiceImpl implements GenViewService {

	@Autowired
	private GenViewDAO genViewDAO;
	@Autowired
	private DetailAnimalDAO animalDAO;
	public void setAnimalDAO(DetailAnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}
	public void setGenViewDAO(GenViewDAO genViewDAO) {
		this.genViewDAO = genViewDAO;
	}

	@Override
	public PageResultModel<PGenView> list(LGenView lgenView) {
		if (lgenView == null) {
			lgenView = new LGenView();
		}
		animalNosHandle(lgenView);
		int total = genViewDAO.count(lgenView);
		PageResultModel<PGenView> resultModel =
				new PageResultModel<PGenView>(total, lgenView.getRows(), lgenView.getPage());
		lgenView.setRows(resultModel.getRows());
		lgenView.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<GenView> list = genViewDAO.selectList(lgenView);
		List<PGenView> plist = new ArrayList<PGenView>();
		for (GenView genview : list) {
			plist.add(Transfer.changeToPageModel(genview));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	@Override
	public List<?> getAnalysisData(AGenView agenView) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 动物筛选条件，对大类做处理
	 * @param ldrugView
	 */
	private void animalNosHandle(LGenView lgenView) {
		String[] animalNames = lgenView.getAnimalname();
		if (animalNames != null && animalNames.length > 0) {
			Set<Integer> nos = animalDAO.selectNosByName(animalNames);
			Set<Integer> superNos = new HashSet<Integer>();
			for (Integer i : nos) {
				if (i % 10 == 0) {
					superNos.add(i);
				}
			}
			if (superNos.size() > 0) {
				nos.addAll(animalDAO.selectNosBySuper(superNos));
			}
			lgenView.setAnimalNos(nos);
		}
	}
}
