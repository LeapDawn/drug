package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.excelModel.GenViewExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.StringUtil;
import drug.commons.util.Transfer;
import drug.dao.GenViewDAO;
import drug.dto.pageModel.PGenView;
import drug.dto.pageModel.UploadResultModel;
import drug.model.GenView;
import drug.service.UpDownService;

@Service("genViewDown")
public class GenViewDownService implements UpDownService {

	@Autowired
	private GenViewDAO genViewDAO;
	public void setGenViewDAO(GenViewDAO genViewDAO) {
		this.genViewDAO = genViewDAO;
	}

	@Override
	public UploadResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		return null;
	}

	@Override
	public File exportDatas(String ids, File file) throws ExcelException {
		if (ids == null || ids.trim().equals("") || ids.trim().equals(",")) {
			throw new DataViolationException("没有选择导出的基因检出率信息");
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
		List<GenView> list = genViewDAO.selectById(nos);
		List<Map<String, Object>> bodyList = this.retransfer(list);
		
		ExeclUtil execlUtil = new ExeclUtil();	
		execlUtil.setHeadArray(GenViewExcel.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "基因检出率信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}

	private List<Map<String, Object>> retransfer(List<GenView> gvList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (GenView gv : gvList) {
			Map<String, Object> map = new HashMap<String, Object>();
			PGenView pgv = Transfer.changeToPageModel(gv);
			map.put(GenViewExcel.SAMPLENO, pgv.getSampleno());
			map.put(GenViewExcel.STRAINNO, pgv.getStrainno());
			map.put(GenViewExcel.ALIAS, pgv.getGenalias());
			map.put(GenViewExcel.ANIMALNAME, pgv.getAnimalname());
			map.put(GenViewExcel.GENNAME, pgv.getGenname());
			map.put(GenViewExcel.GENOTYPING1, pgv.getGenotyping1());
			map.put(GenViewExcel.GENOTYPING2, pgv.getGenotyping2());
			map.put(GenViewExcel.GENOTYPING3, pgv.getGenotyping3());
			map.put(GenViewExcel.REMARKS, pgv.getGenremarks());
			map.put(GenViewExcel.SAMPLEDATE, pgv.getSampledate());
			map.put(GenViewExcel.SAMPLEPROVINCE, pgv.getSampleProvince());
			map.put(GenViewExcel.SAMPLESOURCE, pgv.getSampleSource());
			map.put(GenViewExcel.FARMNAME, pgv.getFarmName());
			list.add(map);
		}
		return list;
	}

}
