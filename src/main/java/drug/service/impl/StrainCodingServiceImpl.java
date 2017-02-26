package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.Transfer;
import drug.dao.SampleDAO;
import drug.dao.StrainCodingDAO;
import drug.dao.StrainToNotesDAO;
import drug.dto.listModel.LStrainCoding;
import drug.dto.pageModel.PStrainCoding;
import drug.dto.pageModel.PageResultModel;
import drug.model.Sample;
import drug.model.StrainCoding;
import drug.model.StrainToNotes;
import drug.service.StrainCodingService;

@Service("strainCodingService")
public class StrainCodingServiceImpl implements StrainCodingService {

	@Autowired
	private StrainCodingDAO strainDAO;
	@Autowired
	private StrainToNotesDAO strainToNotesDAO;
	@Autowired
	private SampleDAO sampleDAO;

	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}
	public void setStrainToNotesDAO(StrainToNotesDAO strainToNotesDAO) {
		this.strainToNotesDAO = strainToNotesDAO;
	}

	@Override
	public void save(PStrainCoding pstrain) {
		pstrain.setStrainno(generateStrainNo(pstrain));
		checkForSave(pstrain);
		StrainCoding strain = Transfer.changeToEntity(pstrain);
		strainDAO.insert(strain);
	}

	@Override
	public void update(PStrainCoding pstrain) {
		checkForUpdate(pstrain);
		StrainCoding strain = new StrainCoding();
		BeanUtils.copyProperties(pstrain, strain);
		strainDAO.update(strain);
	}

	@Override
	public int delete(String strainNos) {
		if (strainNos == null || "".equals(strainNos) || ",".equals(strainNos.trim())) {
			return 0;
		}
		String[] nos = strainNos.split(",");
		int deleteNum = strainDAO.delete(nos);
		return deleteNum;
	}

	@Override
	public PageResultModel<PStrainCoding> list(LStrainCoding lstrain) {
		if (lstrain == null) {
			lstrain = new LStrainCoding();
		}
		int total = strainDAO.count(lstrain);
		PageResultModel<PStrainCoding> resultModel =
				new PageResultModel<PStrainCoding>(total, lstrain.getRows(), lstrain.getPage());
		lstrain.setRows(resultModel.getRows());
		lstrain.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<StrainCoding> list = strainDAO.selectList(lstrain);
		List<PStrainCoding> plist = new ArrayList<PStrainCoding>();
		for (StrainCoding strain : list) {
			plist.add(Transfer.changetoPageModel(strain));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	@Override
	public List<String> getStringNoNotInMic(String gram) {
		return strainDAO.selectNoInMic(gram);
	}

	@Override
	public List<String> getStringNoNotInCharacter() {
		return strainDAO.selectNoInCharacter();
	}

	
	@Override
	public PStrainCoding selectOne(String strainNo) {
		StrainCoding strain = strainDAO.select(strainNo);
		if(strain != null) {
			return Transfer.changetoPageModel(strain);
		} else {
			return null;
		}
	}
	
	/**
	 * 生成菌株编号
	 * @param pstrain
	 * @return
	 */
	private String generateStrainNo(PStrainCoding pstrain) {
		if (pstrain == null || pstrain.getSampleno() == null || pstrain.getSampleno().trim().equals("")) {
			throw new DataViolationException("菌株编号不能为空");
		}
		Sample sample = sampleDAO.select(pstrain.getSampleno().trim());
		if (sample == null) {
			throw new DataViolationException("样品编号[" + pstrain.getSampleno() + "]不存在");
		}
		
		String category = pstrain.getStraincategory();
		String straintype = pstrain.getStraintype();
		if (category == null || "".equals(category.trim())) {
			throw new DataViolationException("菌属信息不能为空");
		}
		if (straintype == null) {
			straintype = "";
		}
		StrainToNotes note = strainToNotesDAO.selectNote(category, straintype);
		if (note == null || "".equals(note.getStrainnotes().trim())) {
			throw new DataViolationException("["+category + "-"+straintype +"]"
					+ "不存在,请在编号管理-菌株编号页面进行添加");
		}
		pstrain.setGramstain(note.getGramstain());
		return pstrain.getSampleno()+note.getStrainnotes();
	}
	
	/**
	 * 检查菌株信息是否存在(新增)
	 * 判断条件:
	 * 1.菌株+菌种
	 * 2.内部编号
	 * @param pstrain
	 */
	private void checkForSave(PStrainCoding pstrain){
		if (pstrain == null || pstrain.getSampleno() == null || pstrain.getSampleno().trim().equals("")) {
			throw new DataViolationException("缺少菌株必要信息");
		}
		String strainNo = pstrain.getStrainno();
		String strainalias = pstrain.getStrainalias();
		List<String> list = strainDAO.selectStrainNoByAliasOrNo(strainNo, strainalias);
		if (list != null && list.size() > 0) {
			String no = list.get(0);
			if (no.equals(strainNo)) {
				throw new DataViolationException("已存在样品编号["+pstrain.getSampleno() + "]["+pstrain.getStraincategory() + "-"+pstrain.getStraintype() +"]"
						+ "对应的菌株信息");
			} else {
				throw new DataViolationException("已存在内部编号["+strainalias+"]"
						+ "对应的菌株信息");
			}
		}
	}

	/**
	 * 检查内部编号(更新)
	 * @param pstrain
	 */
	private void checkForUpdate(PStrainCoding pstrain) {
		if (pstrain == null || pstrain.getStrainno() == null || pstrain.getStrainno().trim().equals("")) {
			throw new DataViolationException("缺少菌株必要信息");
		}
		String strainNo = pstrain.getStrainno();
		String strainalias = pstrain.getStrainalias();
		if (strainalias == null || strainalias.equals("")) {
			return;
		}
		List<String> list = strainDAO.selectStrainNoByAliasAndNo(strainNo, strainalias);
		if (list != null && list.size() > 0) {
			throw new DataViolationException("已存在内部编号["+strainalias+"]"
					+ "对应的菌株信息");
		}
	}
	
}
