package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.StringUtil;
import drug.dao.GenotypingDAO;
import drug.dao.GenotypingDirDAO;
import drug.dto.pageModel.PGenotyping;
import drug.dto.pageModel.ResultPageModel;
import drug.model.Genotyping;
import drug.service.GenotypingService;

@Service("genotypingService")
public class GenotypingServiceImpl implements GenotypingService {

	@Autowired
	private GenotypingDAO gtpDAO;
	@Autowired
	private GenotypingDirDAO gtpDirDAO;
	public void setGtpDAO(GenotypingDAO gtpDAO) {
		this.gtpDAO = gtpDAO;
	}
	public void setGtpDirDAO(GenotypingDirDAO gtpDirDAO) {
		this.gtpDirDAO = gtpDirDAO;
	}

	@Override
	public void save(PGenotyping pgtp) {
		Genotyping gtp = new Genotyping();
		BeanUtils.copyProperties(pgtp, gtp);
		checkGenotyping(gtp.getGenotyping());
		gtpDAO.insert(gtp);
	}

	@Override
	public void update(PGenotyping pgtp) {
		Genotyping gtp = new Genotyping();
		BeanUtils.copyProperties(pgtp, gtp);
		checkGenotyping(gtp.getGenotyping());
		gtpDAO.update(gtp);
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
		return gtpDAO.delete(nos);
	}

	@Override
	public ResultPageModel<Genotyping> list(PGenotyping pgtp) {
		String gtp = pgtp.getGenotyping();
		int total = gtpDAO.count(gtp);
		ResultPageModel<Genotyping> resultModel = 
				new ResultPageModel<Genotyping>(total, pgtp.getRows(), pgtp.getPage());
		List<Genotyping> list = gtpDAO.selectList(gtp,
				(resultModel.getCurrentPage()-1)*resultModel.getRows(), resultModel.getRows());
		resultModel.setData(list);
		return resultModel;
	}

	/** 检查基因亚型是否存在 */
	private void checkGenotyping(String genotyping) {
		List<String> genotypings = gtpDirDAO.selectGentyping3(null);
		if (!genotypings.contains(genotyping)) {
			throw new DataViolationException("基因亚型【" + genotyping + "】不存在，请在【菌株编号-基因亚型管理】添加");
		}
	}
}
