package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.StringUtil;
import drug.commons.util.Transfer;
import drug.dao.StrainCharacterDAO;
import drug.dao.StrainCodingDAO;
import drug.dto.listModel.LStrainCharacter;
import drug.dto.pageModel.PStrainCharacter;
import drug.dto.pageModel.PageResultModel;
import drug.model.StrainCharacter;
import drug.service.StrainCharacterService;

@Service("characterService")
public class StrainCharacterServiceImpl implements StrainCharacterService {

	@Autowired
	private StrainCharacterDAO characterDAO;
	@Autowired
	private StrainCodingDAO strainDAO;

	public void setCharacterDAO(StrainCharacterDAO characterDAO) {
		this.characterDAO = characterDAO;
	}

	public void setStrainDAO(StrainCodingDAO strainDAO) {
		this.strainDAO = strainDAO;
	}

	@Override
	public void save(PStrainCharacter pcharacter) {
		if (pcharacter == null || pcharacter.getStrainno() == null
				|| pcharacter.getStrainno().trim().equals("")
				|| strainDAO.select(pcharacter.getStrainno().trim()) == null) {
			throw new DataViolationException("该菌株编号不存在");
		}
		StrainCharacter character = Transfer.changeToEntity(pcharacter);
		characterDAO.insert(character);
	}

	@Override
	public void update(PStrainCharacter pcharacter) {
		StrainCharacter character = Transfer.changeToEntity(pcharacter);
		characterDAO.update(character);
	}

	@Override
	public int delete(String ids) {
		if (ids == null || "".equals(ids.trim()) || ",".equals(ids.trim())) {
			return 0;
		}
		String[] idsStr = ids.split(",");
		List<Integer> delList =  new ArrayList<Integer>();
		for (String str : idsStr) {
			if (StringUtil.isInt(str)) {
				delList.add(Integer.valueOf(str));
			}
		}
		
		Integer[] nos = new Integer[delList.size()];
		for (int i = 0; i < nos.length; i++) {
			nos[i] = delList.get(i);
		}
		int deleteNum = characterDAO.delete(nos);
		return deleteNum;
	}

	@Override
	public PageResultModel<PStrainCharacter> list(LStrainCharacter lcharacter) {
		if (lcharacter == null) {
			lcharacter = new LStrainCharacter();
		}
		int total = characterDAO.count(lcharacter);
		PageResultModel<PStrainCharacter> resultModel =
				new PageResultModel<PStrainCharacter>(total, lcharacter.getRows(), lcharacter.getPage());
		lcharacter.setRows(resultModel.getRows());
		lcharacter.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<StrainCharacter> list = characterDAO.selectList(lcharacter);
		List<PStrainCharacter> plist = new ArrayList<PStrainCharacter>();
		for (StrainCharacter character : list) {
			plist.add(Transfer.changetoPageModel(character));
		}
		resultModel.setData(plist);
		return resultModel;
	}

	
}
