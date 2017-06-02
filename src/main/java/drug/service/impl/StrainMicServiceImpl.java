package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.MicDataCheck;
import drug.commons.util.Transfer;
import drug.dao.StrainCodingDAO;
import drug.dao.StrainMicDAO;
import drug.dto.listModel.LStrainMic;
import drug.dto.pageModel.PStrainMic;
import drug.dto.pageModel.PageResultModel;
import drug.model.StrainCoding;
import drug.model.StrainMic;
import drug.service.StrainMicService;

@Service("strainMicService")
public class StrainMicServiceImpl implements StrainMicService {

	@Autowired
	private StrainMicDAO micDAO;
	@Autowired
	private StrainCodingDAO strainDAO;

	public void setMicDAO(StrainMicDAO micDAO) {
		this.micDAO = micDAO;
	}

	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}

	@Override
	public void save(PStrainMic pstrainMic) {
		if (pstrainMic == null || pstrainMic.getStrainno() == null
				|| pstrainMic.getStrainno().trim().equals("")){
			throw new DataViolationException("菌株编号不能为空");
		}
		StrainCoding strain = strainDAO.select(pstrainMic.getStrainno().trim());
		if (strain == null) {
			throw new DataViolationException("该菌株编号不存在");
		} else if (!strain.getGramstain().equals(pstrainMic.getGramstain())) {
			throw new DataViolationException("该菌株为" + strain.getGramstain() + "菌株");
		}
		StrainMic mic = Transfer.changeToEntity(pstrainMic);
		MicDataCheck.checkAllDate(mic);
		micDAO.insert(mic);
	}

	@Override
	public void update(PStrainMic pstrainMic) {
		StrainMic mic = Transfer.changeToEntity(pstrainMic);
		MicDataCheck.checkAllDate(mic);
		micDAO.update(mic);
	}

	@Override
	public int delete(String strainNos, String gramStrain) {
		if (strainNos == null || "".equals(strainNos) || ",".equals(strainNos.trim())) {
			return 0;
		}
		String[] nos = strainNos.split(",");
		return micDAO.delete(nos, gramStrain);
	}

	@Override
	public PageResultModel<PStrainMic> list(LStrainMic lstrainMic) {
		if (lstrainMic == null) {
			lstrainMic = new LStrainMic();
		}
		int total = micDAO.count(lstrainMic);
		PageResultModel<PStrainMic> resultModel =
				new PageResultModel<PStrainMic>(total, lstrainMic.getRows(), lstrainMic.getPage());
		lstrainMic.setRows(resultModel.getRows());
		lstrainMic.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<StrainMic> list = micDAO.selectList(lstrainMic);
		List<PStrainMic> plist = new ArrayList<PStrainMic>();
		for (StrainMic strainMic : list) {
			plist.add(Transfer.changetoPageModel(strainMic));
		}
		resultModel.setData(plist);
		return resultModel;
	}

}
