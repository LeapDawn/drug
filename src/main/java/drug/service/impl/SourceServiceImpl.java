package drug.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.util.StringUtil;
import drug.dao.SourceDAO;
import drug.dto.pageModel.PSource;
import drug.dto.pageModel.PageResultModel;
import drug.model.Source;
import drug.service.SourceService;

@Service("sourceService")
public class SourceServiceImpl implements SourceService {

	@Autowired
	private SourceDAO sourceDAO;
	public void setSourceDAO(SourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	/**
	 * 新增来源
	 * @param psource
	 */
	@Override
	public void save(PSource psource) {
		Source source = new Source();
		BeanUtils.copyProperties(psource, source);
		sourceDAO.insert(source);
	}

	/**
	 * 更新来源信息
	 * @param psource
	 */
	@Override
	public void update(PSource psource) {
		Source source = new Source();
		BeanUtils.copyProperties(psource, source);
		sourceDAO.update(source);
	}
	
	/**
	 * 删除来源记录
	 * @param sourceNos
	 * @return 被删除的数目
	 */
	@Override
	public int delete(String sourceNos) {
		if (sourceNos == null || "".equals(sourceNos.trim()) || ",".equals(sourceNos.trim())) {
			return 0;
		}
		String[] sourceNoStr = sourceNos.split(",");
		List<Integer> delList =  new ArrayList<Integer>();
		for (String str : sourceNoStr) {
			if (StringUtil.isInt(str)) {
				delList.add(Integer.valueOf(str));
			}
		}
		// 判断数据是否已经被sample引用,若存在,抛出异常
		List<Integer> list = sourceDAO.selectNosInSample();
		StringBuffer sb = new StringBuffer();
		for (Integer no : delList) {
			if (list.contains(no)) {
				sb.append(no + ",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length()-1);
			String errorMsg = "删除失败,其中编号为[" + sb.toString() + "]的来源编号已被引用";
			throw new DataViolationException(errorMsg);
		}
		
		Integer[] nos = new Integer[delList.size()];
		for (int i = 0; i < nos.length; i++) {
			nos[i] = delList.get(i);
		}
 		int deleteNum = sourceDAO.delete(nos);
		return deleteNum;
	}

	/**
	 * 获取来源列表(分页)
	 * @param psource
	 * @return
	 */
	@Override
	public PageResultModel<PSource> list(PSource psource) {
		if (psource == null) {
			psource = new PSource();
		}
		int total = sourceDAO.count();
		PageResultModel<PSource> resultModel = 
				new PageResultModel<PSource>(total, psource.getRows(), psource.getPage());
		// 更新rows属性和page属性,此处page属性不再为页码,改为limit的第一个参数(跳过的记录数)
		psource.setRows(resultModel.getRows());
		psource.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<Source> list = sourceDAO.select(psource);
		List<PSource> plist = new ArrayList<PSource>();
		for (Source source : list) {
			plist.add(this.changetoPageModel(source));
		}
		resultModel.setData(plist);
		return resultModel;
	}
	
	/**
	 * 获取来源集合
	 * @return
	 */
	@Override
	public List<Source> getSources() {
		List<Source> list = sourceDAO.selectAll();
		return list;
	}
	
	
	private PSource changetoPageModel(Source source) {
		PSource psource = new PSource();
		BeanUtils.copyProperties(source, psource);
		return psource;
	}
}
