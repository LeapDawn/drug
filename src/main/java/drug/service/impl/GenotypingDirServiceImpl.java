package drug.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import drug.commons.exception.DataViolationException;
import drug.commons.util.StringUtil;
import drug.dao.GenotypingDAO;
import drug.dao.GenotypingDirDAO;
import drug.dto.pageModel.PGenotypingDir;
import drug.dto.pageModel.ResultPageModel;
import drug.model.GenotypingDir;
import drug.service.GenotypingDirService;

@Service("genotypingDirService")
public class GenotypingDirServiceImpl implements GenotypingDirService {

	@Autowired
	private GenotypingDirDAO gtpDirDAO;
	@Autowired
	private GenotypingDAO gtpDAO;
	public void setGtpDirDAO(GenotypingDirDAO gtpDirDao) {
		this.gtpDirDAO = gtpDirDao;
	}
	public void setGtpDAO(GenotypingDAO gtpDAO) {
		this.gtpDAO = gtpDAO;
	}


	@Override
	public void save(PGenotypingDir pgtpDir) {
		GenotypingDir gtpDir = new GenotypingDir();
		BeanUtils.copyProperties(pgtpDir, gtpDir);
		gtpDirDAO.insert(gtpDir);
	}

	@Override
	@Transactional
	public void update(PGenotypingDir pgtpDir) {
		GenotypingDir gtpDir = new GenotypingDir();
		BeanUtils.copyProperties(pgtpDir, gtpDir);
		List<GenotypingDir> list = gtpDirDAO.selectByIds(new Integer[]{gtpDir.getId()});
		if (list == null || list.size() == 0) {
			throw new DataViolationException("没有该基因亚型记录");
		}
		String src = list.get(0).getGenotyping3();
		String tar = gtpDir.getGenotyping3();
		
		gtpDirDAO.update(gtpDir);
		if (!src.equals(tar)) {
			gtpDAO.updateGenotyping(src, tar);
		}
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
		
		// 获取待删除记录信息
		List<GenotypingDir> list = gtpDirDAO.selectByIds(nos);
		if (list == null || list.size() == 0) {
			return 0;
		}
		
		// 获取第三级亚型名称集合
		Set<String> genotyping3Set = new HashSet<String>();
		for (GenotypingDir gtpd : list) {
			String genotyping3 = gtpd.getGenotyping3();
			if (genotyping3 != null && !"".equals(genotyping3)) {
				genotyping3Set.add(genotyping3);
			}
		}
		
		// 判断待删除记录中是否包含已经被引用的记录
		if (genotyping3Set.size() > 0) {
			int num = gtpDAO.countGenotypings(genotyping3Set);
			if (num > 0) {
				throw new DataViolationException("亚型信息已经被引用，请在【菌株编号-亚型-基因】页面管理");
			}
		}
		return gtpDirDAO.delete(nos);
	}

	@Override
	public List<String> getGenotyping1() {
		return gtpDirDAO.selectGentyping1();
	}

	@Override
	public List<String> getGenotyping2(String gentyping1) {
		return gtpDirDAO.selectGentyping2(gentyping1);
	}

	@Override
	public List<String> getGenotyping3(String gentyping2) {
		return gtpDirDAO.selectGentyping3(gentyping2);
	}

	@Override
	public ResultPageModel<GenotypingDir> list(PGenotypingDir page) {
		Integer total = gtpDirDAO.count();
		ResultPageModel<GenotypingDir> resultModel = 
				new ResultPageModel<GenotypingDir>(total, page.getRows(), page.getPage());
		List<GenotypingDir> list = gtpDirDAO.selectList(
				(resultModel.getCurrentPage()-1)*resultModel.getRows(), resultModel.getRows());
		resultModel.setData(list);
		return resultModel;
	}
}
