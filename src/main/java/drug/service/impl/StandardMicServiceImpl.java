package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.dao.StandardMicDAO;
import drug.dto.pageModel.PStandardMic;
import drug.dto.pageModel.PageResultModel;
import drug.model.StandardMic;
import drug.model.StandardMicKey;
import drug.service.StandardMicService;

@Service("standardMicService")
public class StandardMicServiceImpl implements StandardMicService {
	
	@Autowired
	private StandardMicDAO standardMicDAO;
	public void setStandardMicDAO(StandardMicDAO standardMicDAO) {
		this.standardMicDAO = standardMicDAO;
	}
	
	@Override
	public void save(PStandardMic pstandardMic) {
		StandardMic standardMic = new StandardMic();
		BeanUtils.copyProperties(pstandardMic, standardMic);
		standardMicDAO.insert(standardMic);
	}

	@Override
	public void update(PStandardMic pstandardMic) {
		StandardMic standardMic = new StandardMic();
		BeanUtils.copyProperties(pstandardMic, standardMic);
		standardMicDAO.update(standardMic);
	}

	@Override
	public int delete(PStandardMic[] pstandardMic) {
		if (pstandardMic == null || pstandardMic.length == 0) {
			return 0;
		}
		StandardMicKey[] keys = new StandardMicKey[pstandardMic.length];
		StandardMicKey key = null;
		for (int i = 0; i < pstandardMic.length; i++) {
			key = new StandardMicKey();
			BeanUtils.copyProperties(pstandardMic[i], key);
			keys[i] = key;
		}
		int delNum = standardMicDAO.delete(keys);
		return delNum;
	}

	@Override
	public PageResultModel<PStandardMic> list(PStandardMic pstandardMic) {
		if (pstandardMic == null) {
			pstandardMic = new PStandardMic();
		}
		int total = standardMicDAO.count(pstandardMic);
		PageResultModel<PStandardMic> resultModel = 
				new PageResultModel<PStandardMic>(total, pstandardMic.getRows(), pstandardMic.getPage());
		pstandardMic.setRows(resultModel.getRows());
		pstandardMic.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<StandardMic> list = standardMicDAO.selectList(pstandardMic);
		List<PStandardMic> plist = new ArrayList<PStandardMic>();
		for (StandardMic standardMic : list) {
			plist.add(this.changetoPageModel(standardMic));
		}
		
		resultModel.setData(plist);
		return resultModel;
	}

	@Override
	public List<String> getStrains() {
		return standardMicDAO.selectStrain();
	}

	@Override
	public List<String> getStandards() {
		return standardMicDAO.selectStandard();
	}
	
	@Override
	public List<String> getDrug() {
		return standardMicDAO.selectDrug();
	}

	private PStandardMic changetoPageModel(StandardMic standardMic) {
		PStandardMic pstandardMic = new PStandardMic();
		BeanUtils.copyProperties(standardMic, pstandardMic);
		return pstandardMic;
	}

}
