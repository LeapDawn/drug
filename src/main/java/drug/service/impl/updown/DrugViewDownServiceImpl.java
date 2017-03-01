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

import drug.commons.excelModel.DrugViewExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Transfer;
import drug.dao.DrugViewDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PDrugView;
import drug.model.DrugView;
import drug.service.UpDownService;

@Service("drugViewDown")
public class DrugViewDownServiceImpl implements UpDownService {

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private DrugViewDAO dvDAO;
	public void setDvaDAO(DrugViewDAO dvDAO) {
		this.dvDAO = dvDAO;
	}

	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		return null;
	}

	@Override
	public File exportDatas(String ids, File file) throws ExcelException {
		String micFlag = null;  // 阴/阳性标识
		if (ids != null && ids.length() >= 1) {
			micFlag = ids.substring(0, 1);
			ids = ids.substring(1);
		}
		if (ids == null || ids.trim().equals("") || ids.trim().equals(",")){
			throw new DataViolationException("没有选择导出的耐药率信息");
		}
		String[] nos = ids.trim().split(",");
		
		List<DrugView> list = null;
		// 1=阳性,2=阴性,others=全部
		if ("1".equals(micFlag)) {
			list = dvDAO.selectById(nos, "阳性");
		} else if ("2".equals(micFlag)) {
			list = dvDAO.selectById(nos, "阴性");
		} else {
			list = dvDAO.selectById(nos, null);
		}
		List<Map<String, Object>> bodyList = this.retransfer(list);
		
		ExeclUtil execlUtil = new ExeclUtil();
		if ("1".equals(micFlag)) {
			execlUtil.setHeadArray(DrugViewExcel.getPositiveExportColumns());
		} else if ("2".equals(micFlag)) {
			execlUtil.setHeadArray(DrugViewExcel.getNegativeExportColumns());
		} else {
			execlUtil.setHeadArray(DrugViewExcel.getExportColumns());
		}
		
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "耐药率信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}

	private List<Map<String, Object>> retransfer(List<DrugView> dvList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DrugView dv : dvList) {
			Map<String, Object> map = new HashMap<String, Object>();
			PDrugView pdv = Transfer.changeToPageModel(dv);
			map.put(DrugViewExcel.SAMPLENO, pdv.getSampleno());
			map.put(DrugViewExcel.STRAINNO, pdv.getStrainno());
			map.put(DrugViewExcel.ALIAS, pdv.getStrainalias());
			map.put(DrugViewExcel.SAMPLEDATE, pdv.getSampledate());
			map.put(DrugViewExcel.SAMPLEPROVINCE, pdv.getSampleprovince());
			map.put(DrugViewExcel.FARMNAME, pdv.getFarmname());
			map.put(DrugViewExcel.SAMPLEFARMADDR, pdv.getSamplefarmaddr());
			map.put(DrugViewExcel.ANIMALNAME, pdv.getAnimalname());
			map.put(DrugViewExcel.SAMPLEANIMALAGE, pdv.getSampleanimalage());
			map.put(DrugViewExcel.SAMPLESOURCE, pdv.getSamplesource());
			map.put(DrugViewExcel.PARTNAME, pdv.getSamplecollectionpart());
			map.put(DrugViewExcel.SAMPLECOLLECTOR, pdv.getSamplecollector());
			map.put(DrugViewExcel.SAMPLEMEDICALHISTORY, pdv.getSamplemedicalhistory());
			map.put(DrugViewExcel.SAMPLEREMARKS, pdv.getSampleremarks());
			map.put(DrugViewExcel.STRAINCATEGORY, pdv.getStraincategory());
			map.put(DrugViewExcel.STRAINTYPE, pdv.getStraintype());
			map.put(DrugViewExcel.STRAINSTORAGEDATE, pdv.getStrainstoragedate());
			map.put(DrugViewExcel.SEROTYPE, pdv.getSerotype());
			map.put(DrugViewExcel.STRAINMLST, pdv.getStrainmlst());
			map.put(DrugViewExcel.STRAINPLG, pdv.getStrainplg());
			map.put(DrugViewExcel.STRAINOPERATOR, pdv.getStrainoperator());
			map.put(DrugViewExcel.STRAINPARTER, pdv.getStrainparter());
			map.put(DrugViewExcel.STRAINREMARKS, pdv.getStrainremarks());
			map.put(DrugViewExcel.AMP,String.valueOf(pdv.getAmp()));
			map.put(DrugViewExcel.OXA,String.valueOf(pdv.getOxa()));
			map.put(DrugViewExcel.PEN,String.valueOf(pdv.getPen()));
			map.put(DrugViewExcel.PIP,String.valueOf(pdv.getPip()));
			map.put(DrugViewExcel.TZP,String.valueOf(pdv.getTzp()));
			map.put(DrugViewExcel.CAZ,String.valueOf(pdv.getCaz()));
			map.put(DrugViewExcel.CFZ,String.valueOf(pdv.getCfz()));
			map.put(DrugViewExcel.CQM,String.valueOf(pdv.getCqm()));
			map.put(DrugViewExcel.CRO,String.valueOf(pdv.getCro()));
			map.put(DrugViewExcel.CTX,String.valueOf(pdv.getCtx()));
			map.put(DrugViewExcel.FEP,String.valueOf(pdv.getFep()));
			map.put(DrugViewExcel.FOX,String.valueOf(pdv.getFox()));
			map.put(DrugViewExcel.AMK,String.valueOf(pdv.getAmk()));
			map.put(DrugViewExcel.APR,String.valueOf(pdv.getApr()));
			map.put(DrugViewExcel.GEN,String.valueOf(pdv.getGen()));
			map.put(DrugViewExcel.NEO,String.valueOf(pdv.getNeo()));
			map.put(DrugViewExcel.NET,String.valueOf(pdv.getNet()));
			map.put(DrugViewExcel.STR,String.valueOf(pdv.getStr()));
			map.put(DrugViewExcel.TOB,String.valueOf(pdv.getTob()));
			map.put(DrugViewExcel.DOX,String.valueOf(pdv.getDox()));
			map.put(DrugViewExcel.MIN,String.valueOf(pdv.getMin()));
			map.put(DrugViewExcel.TET,String.valueOf(pdv.getTet()));
			map.put(DrugViewExcel.CHL,String.valueOf(pdv.getChl()));
			map.put(DrugViewExcel.FFC,String.valueOf(pdv.getFfc()));
			map.put(DrugViewExcel.AZM,String.valueOf(pdv.getAzm()));
			map.put(DrugViewExcel.ERY,String.valueOf(pdv.getEry()));
			map.put(DrugViewExcel.RIF,String.valueOf(pdv.getRif()));
			map.put(DrugViewExcel.TEC,String.valueOf(pdv.getTec()));
			map.put(DrugViewExcel.VAN,String.valueOf(pdv.getVan()));
			map.put(DrugViewExcel.CLI,String.valueOf(pdv.getCli()));
			map.put(DrugViewExcel.CL,String.valueOf(pdv.getCl()));
			map.put(DrugViewExcel.IMP,String.valueOf(pdv.getImp()));
			map.put(DrugViewExcel.TIA,String.valueOf(pdv.getTia()));
			map.put(DrugViewExcel.VAL,String.valueOf(pdv.getVal()));
			map.put(DrugViewExcel.FOS,String.valueOf(pdv.getFos()));
			map.put(DrugViewExcel.NIT,String.valueOf(pdv.getNit()));
			map.put(DrugViewExcel.SXT,String.valueOf(pdv.getSxt()));
			map.put(DrugViewExcel.TMP,String.valueOf(pdv.getTmp()));
			map.put(DrugViewExcel.CIP,String.valueOf(pdv.getCip()));
			map.put(DrugViewExcel.LEV,String.valueOf(pdv.getLev()));
			map.put(DrugViewExcel.NAL,String.valueOf(pdv.getNal()));
			map.put(DrugViewExcel.NOR,String.valueOf(pdv.getNor()));
			map.put(DrugViewExcel.OFX,String.valueOf(pdv.getOfx()));
			map.put(DrugViewExcel.LZD,String.valueOf(pdv.getLzd()));
			map.put(DrugViewExcel.OQX,String.valueOf(pdv.getOqx()));
			map.put(DrugViewExcel.MEM,String.valueOf(pdv.getMem()));
			map.put(DrugViewExcel.MICOPERATOR, pdv.getMicoperator());
			map.put(DrugViewExcel.MICDETECTIONTYPE, pdv.getMicdetectiontype());
			map.put(DrugViewExcel.MICREMARK, pdv.getMicremarks());
			map.put(DrugViewExcel.GENNAME, pdv.getGenname());
			map.put(DrugViewExcel.ISEQ, pdv.getIseq());
			map.put(DrugViewExcel.REPLICON, pdv.getReplicon());
			map.put(DrugViewExcel.GENTC, pdv.getGentc());
			map.put(DrugViewExcel.GENOPERATOR, pdv.getGenoperator());
			list.add(map);
		}
		return list;
	}

}
