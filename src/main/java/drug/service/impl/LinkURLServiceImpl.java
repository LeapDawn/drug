package drug.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.dao.LinkURLDAO;
import drug.dto.pageModel.PLinkURL;
import drug.dto.pageModel.PageResultModel;
import drug.model.LinkURL;
import drug.service.LinkURLService;

@Service("linkURLService")
public class LinkURLServiceImpl implements LinkURLService{

	@Autowired
	private LinkURLDAO linkURLDAO;
	public void setLinkURLDAO(LinkURLDAO linkURLDAO) {
		this.linkURLDAO = linkURLDAO;
	}
	
	@Override
	public void save(PLinkURL plinkURL) {
		LinkURL linkURL = new LinkURL();
		BeanUtils.copyProperties(plinkURL, linkURL);
		linkURLDAO.insert(linkURL);
	}
	
	@Override
	public void update(PLinkURL plinkURL) {
		LinkURL linkURL = new LinkURL();
		BeanUtils.copyProperties(plinkURL, linkURL);
		linkURLDAO.update(linkURL);
	}
	
	@Override
	public int delete(String names) {
		if (names == null || "".equals(names.trim()) || ",".equals(names.trim())) {
			return 0;
		}
		String[] nameArray = names.trim().split(",");
		int deleteNum = linkURLDAO.delete(nameArray);
		return deleteNum;
	}
	
	@Override
	public PageResultModel<LinkURL> list(PLinkURL linkURL) {
		if (linkURL == null) {
			linkURL = new PLinkURL();
		}
		int total = linkURLDAO.count();
		PageResultModel<LinkURL> resultModel = 
				new PageResultModel<LinkURL>(total, linkURL.getRows(), linkURL.getPage());
		linkURL.setRows(resultModel.getRows());
		linkURL.setPage((resultModel.getCurrentPage()-1)*resultModel.getRows());
		
		List<LinkURL> list = linkURLDAO.selectList(linkURL);
		resultModel.setData(list);
		return resultModel;
	}
}
