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

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExeclException;
import drug.commons.execlModel.DrugViewAllExecl;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dao.DrugViewAllDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PDrugViewAll;
import drug.model.DrugViewAll;
import drug.service.UpDownService;

@Service("drugViewAllDown")
public class DrugViewAllDownServiceImpl implements UpDownService {

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private DrugViewAllDAO dvaDAO;
	public void setDvaDAO(DrugViewAllDAO dvaDAO) {
		this.dvaDAO = dvaDAO;
	}

	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExeclException {
		return null;
	}

	@Override
	public File exportDatas(String ids, File file) throws ExeclException {
		if (ids == null || ids.trim().equals("") || ids.trim().equals(",")){
			throw new DataViolationException("没有选择导出的检出率信息");
		}
		String[] nos = ids.trim().split(",");
		List<DrugViewAll> list = dvaDAO.selectById(nos);
		
		List<Map<String, Object>> bodyList = this.retransfer(list);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(DrugViewAllExecl.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "检出率信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExeclException("写入execl文件失败");
		}
		return file;
	}

	private List<Map<String, Object>> retransfer(List<DrugViewAll> dvalist) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DrugViewAll dva : dvalist) {
			Map<String, Object> map = new HashMap<String, Object>();
			PDrugViewAll pdva = Transfer.changeToPageModel(dva);
			map.put(DrugViewAllExecl.SAMPLENO, pdva.getSampleno());
			map.put(DrugViewAllExecl.STRAINNO, pdva.getStrainno());
			map.put(DrugViewAllExecl.ALIAS, pdva.getStrainalias());
			map.put(DrugViewAllExecl.SAMPLEDATE, pdva.getSampledate());
			map.put(DrugViewAllExecl.SAMPLEPROVINCE, pdva.getSampleprovince());
			map.put(DrugViewAllExecl.FARMNAME, pdva.getFarmname());
			map.put(DrugViewAllExecl.SAMPLEFARMADDR, pdva.getSamplefarmaddr());
			map.put(DrugViewAllExecl.ANIMALNAME, pdva.getAnimalname());
			map.put(DrugViewAllExecl.SAMPLEANIMALAGE, pdva.getSampleanimalage());
			map.put(DrugViewAllExecl.SAMPLESOURCE, pdva.getSamplesource());
			map.put(DrugViewAllExecl.PARTNAME, pdva.getSamplecollectionpart());
			map.put(DrugViewAllExecl.SAMPLECOLLECTOR, pdva.getSamplecollector());
			map.put(DrugViewAllExecl.SAMPLEMEDICALHISTORY, pdva.getSamplemedicalhistory());
			map.put(DrugViewAllExecl.SAMPLEREMARKS, pdva.getSampleremarks());
			map.put(DrugViewAllExecl.STRAINCATEGORY, pdva.getStraincategory());
			map.put(DrugViewAllExecl.STRAINTYPE, pdva.getStraintype());
			map.put(DrugViewAllExecl.STRAINSTORAGEDATE, pdva.getStrainstoragedate());
			map.put(DrugViewAllExecl.SEROTYPE, pdva.getSerotype());
			map.put(DrugViewAllExecl.STRAINMLST, pdva.getStrainmlst());
			map.put(DrugViewAllExecl.STRAINPLG, pdva.getStrainplg());
			map.put(DrugViewAllExecl.OPERATOR, pdva.getStrainoperator());
			map.put(DrugViewAllExecl.STRAINPARTER, pdva.getStrainparter());
			map.put(DrugViewAllExecl.STRAINREMARKS, pdva.getStrainremarks());
			list.add(map);
		}
		return list;
	}
}
