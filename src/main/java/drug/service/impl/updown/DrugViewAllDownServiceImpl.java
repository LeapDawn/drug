package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drug.commons.excelModel.DrugViewAllExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dao.DrugViewAllDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PDrugViewAll;
import drug.model.DrugViewAll;
import drug.service.UpDownService;

@Service("drugViewAllDown")
/**
 * 检出率信息导出
 */
public class DrugViewAllDownServiceImpl implements UpDownService {

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private DrugViewAllDAO dvaDAO;
	public void setDvaDAO(DrugViewAllDAO dvaDAO) {
		this.dvaDAO = dvaDAO;
	}

	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		return null;
	}

	@Override
	public File exportDatas(String ids, File file) throws ExcelException {
		if (ids == null || ids.trim().equals("") || ids.trim().equals(",")){
			throw new DataViolationException("没有选择导出的检出率信息");
		}
		String[] nos = ids.trim().split(",");
		List<DrugViewAll> list = dvaDAO.selectById(nos);
		
		List<Map<String, Object>> bodyList = this.retransfer(list);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(DrugViewAllExcel.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "检出率信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}

	private List<Map<String, Object>> retransfer(List<DrugViewAll> dvalist) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DrugViewAll dva : dvalist) {
			Map<String, Object> map = new HashMap<String, Object>();
			PDrugViewAll pdva = Transfer.changeToPageModel(dva);
			map.put(DrugViewAllExcel.SAMPLENO, pdva.getSampleno());
			map.put(DrugViewAllExcel.STRAINNO, pdva.getStrainno());
			map.put(DrugViewAllExcel.ALIAS, pdva.getStrainalias());
			map.put(DrugViewAllExcel.SAMPLEDATE, pdva.getSampledate());
			map.put(DrugViewAllExcel.SAMPLEPROVINCE, pdva.getSampleprovince());
			map.put(DrugViewAllExcel.FARMNAME, pdva.getFarmname());
			map.put(DrugViewAllExcel.SAMPLEFARMADDR, pdva.getSamplefarmaddr());
			map.put(DrugViewAllExcel.ANIMALNAME, pdva.getAnimalname());
			map.put(DrugViewAllExcel.SAMPLEANIMALAGE, pdva.getSampleanimalage());
			map.put(DrugViewAllExcel.SAMPLESOURCE, pdva.getSamplesource());
			map.put(DrugViewAllExcel.PARTNAME, pdva.getSamplecollectionpart());
			map.put(DrugViewAllExcel.SAMPLECOLLECTOR, pdva.getSamplecollector());
			map.put(DrugViewAllExcel.SAMPLEMEDICALHISTORY, pdva.getSamplemedicalhistory());
			map.put(DrugViewAllExcel.SAMPLEREMARKS, pdva.getSampleremarks());
			map.put(DrugViewAllExcel.STRAINCATEGORY, pdva.getStraincategory());
			map.put(DrugViewAllExcel.STRAINTYPE, pdva.getStraintype());
			map.put(DrugViewAllExcel.STRAINSTORAGEDATE, pdva.getStrainstoragedate());
			map.put(DrugViewAllExcel.SEROTYPE, pdva.getSerotype());
			map.put(DrugViewAllExcel.STRAINMLST, pdva.getStrainmlst());
			map.put(DrugViewAllExcel.STRAINPLG, pdva.getStrainplg());
			map.put(DrugViewAllExcel.OPERATOR, pdva.getStrainoperator());
			map.put(DrugViewAllExcel.STRAINPARTER, pdva.getStrainparter());
			map.put(DrugViewAllExcel.STRAINREMARKS, pdva.getStrainremarks());
			list.add(map);
		}
		return list;
	}
}
