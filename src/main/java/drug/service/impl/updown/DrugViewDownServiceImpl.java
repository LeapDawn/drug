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
import drug.commons.execlModel.DrugViewExecl;
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
			throws ExeclException {
		return null;
	}

	@Override
	public File exportDatas(String ids, File file) throws ExeclException {
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
			execlUtil.setHeadArray(DrugViewExecl.getPositiveExportColumns());
		} else if ("2".equals(micFlag)) {
			execlUtil.setHeadArray(DrugViewExecl.getNegativeExportColumns());
		} else {
			execlUtil.setHeadArray(DrugViewExecl.getExportColumns());
		}
		
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "耐药率信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExeclException("写入execl文件失败");
		}
		return file;
	}

	private List<Map<String, Object>> retransfer(List<DrugView> dvList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (DrugView dv : dvList) {
			Map<String, Object> map = new HashMap<String, Object>();
			PDrugView pdv = Transfer.changeToPageModel(dv);
			map.put(DrugViewExecl.SAMPLENO, pdv.getSampleno());
			map.put(DrugViewExecl.STRAINNO, pdv.getStrainno());
			map.put(DrugViewExecl.ALIAS, pdv.getStrainalias());
			map.put(DrugViewExecl.SAMPLEDATE, pdv.getSampledate());
			map.put(DrugViewExecl.SAMPLEPROVINCE, pdv.getSampleprovince());
			map.put(DrugViewExecl.FARMNAME, pdv.getFarmname());
			map.put(DrugViewExecl.SAMPLEFARMADDR, pdv.getSamplefarmaddr());
			map.put(DrugViewExecl.ANIMALNAME, pdv.getAnimalname());
			map.put(DrugViewExecl.SAMPLEANIMALAGE, pdv.getSampleanimalage());
			map.put(DrugViewExecl.SAMPLESOURCE, pdv.getSamplesource());
			map.put(DrugViewExecl.PARTNAME, pdv.getSamplecollectionpart());
			map.put(DrugViewExecl.SAMPLECOLLECTOR, pdv.getSamplecollector());
			map.put(DrugViewExecl.SAMPLEMEDICALHISTORY, pdv.getSamplemedicalhistory());
			map.put(DrugViewExecl.SAMPLEREMARKS, pdv.getSampleremarks());
			map.put(DrugViewExecl.STRAINCATEGORY, pdv.getStraincategory());
			map.put(DrugViewExecl.STRAINTYPE, pdv.getStraintype());
			map.put(DrugViewExecl.STRAINSTORAGEDATE, pdv.getStrainstoragedate());
			map.put(DrugViewExecl.SEROTYPE, pdv.getSerotype());
			map.put(DrugViewExecl.STRAINMLST, pdv.getStrainmlst());
			map.put(DrugViewExecl.STRAINPLG, pdv.getStrainplg());
			map.put(DrugViewExecl.STRAINOPERATOR, pdv.getStrainoperator());
			map.put(DrugViewExecl.STRAINPARTER, pdv.getStrainparter());
			map.put(DrugViewExecl.STRAINREMARKS, pdv.getStrainremarks());
			map.put(DrugViewExecl.AMP,String.valueOf(pdv.getAmp()));
			map.put(DrugViewExecl.OXA,String.valueOf(pdv.getOxa()));
			map.put(DrugViewExecl.PEN,String.valueOf(pdv.getPen()));
			map.put(DrugViewExecl.PIP,String.valueOf(pdv.getPip()));
			map.put(DrugViewExecl.TZP,String.valueOf(pdv.getTzp()));
			map.put(DrugViewExecl.CAZ,String.valueOf(pdv.getCaz()));
			map.put(DrugViewExecl.CFZ,String.valueOf(pdv.getCfz()));
			map.put(DrugViewExecl.CQM,String.valueOf(pdv.getCqm()));
			map.put(DrugViewExecl.CRO,String.valueOf(pdv.getCro()));
			map.put(DrugViewExecl.CTX,String.valueOf(pdv.getCtx()));
			map.put(DrugViewExecl.FEP,String.valueOf(pdv.getFep()));
			map.put(DrugViewExecl.FOX,String.valueOf(pdv.getFox()));
			map.put(DrugViewExecl.AMK,String.valueOf(pdv.getAmk()));
			map.put(DrugViewExecl.APR,String.valueOf(pdv.getApr()));
			map.put(DrugViewExecl.GEN,String.valueOf(pdv.getGen()));
			map.put(DrugViewExecl.NEO,String.valueOf(pdv.getNeo()));
			map.put(DrugViewExecl.NET,String.valueOf(pdv.getNet()));
			map.put(DrugViewExecl.STR,String.valueOf(pdv.getStr()));
			map.put(DrugViewExecl.TOB,String.valueOf(pdv.getTob()));
			map.put(DrugViewExecl.DOX,String.valueOf(pdv.getDox()));
			map.put(DrugViewExecl.MIN,String.valueOf(pdv.getMin()));
			map.put(DrugViewExecl.TET,String.valueOf(pdv.getTet()));
			map.put(DrugViewExecl.CHL,String.valueOf(pdv.getChl()));
			map.put(DrugViewExecl.FFC,String.valueOf(pdv.getFfc()));
			map.put(DrugViewExecl.AZM,String.valueOf(pdv.getAzm()));
			map.put(DrugViewExecl.ERY,String.valueOf(pdv.getEry()));
			map.put(DrugViewExecl.RIF,String.valueOf(pdv.getRif()));
			map.put(DrugViewExecl.TEC,String.valueOf(pdv.getTec()));
			map.put(DrugViewExecl.VAN,String.valueOf(pdv.getVan()));
			map.put(DrugViewExecl.CLI,String.valueOf(pdv.getCli()));
			map.put(DrugViewExecl.CL,String.valueOf(pdv.getCl()));
			map.put(DrugViewExecl.IMP,String.valueOf(pdv.getImp()));
			map.put(DrugViewExecl.TIA,String.valueOf(pdv.getTia()));
			map.put(DrugViewExecl.VAL,String.valueOf(pdv.getVal()));
			map.put(DrugViewExecl.FOS,String.valueOf(pdv.getFos()));
			map.put(DrugViewExecl.NIT,String.valueOf(pdv.getNit()));
			map.put(DrugViewExecl.SXT,String.valueOf(pdv.getSxt()));
			map.put(DrugViewExecl.TMP,String.valueOf(pdv.getTmp()));
			map.put(DrugViewExecl.CIP,String.valueOf(pdv.getCip()));
			map.put(DrugViewExecl.LEV,String.valueOf(pdv.getLev()));
			map.put(DrugViewExecl.NAL,String.valueOf(pdv.getNal()));
			map.put(DrugViewExecl.NOR,String.valueOf(pdv.getNor()));
			map.put(DrugViewExecl.OFX,String.valueOf(pdv.getOfx()));
			map.put(DrugViewExecl.LZD,String.valueOf(pdv.getLzd()));
			map.put(DrugViewExecl.OQX,String.valueOf(pdv.getOqx()));
			map.put(DrugViewExecl.MEM,String.valueOf(pdv.getMem()));
			map.put(DrugViewExecl.MICOPERATOR, pdv.getMicoperator());
			map.put(DrugViewExecl.MICDETECTIONTYPE, pdv.getMicdetectiontype());
			map.put(DrugViewExecl.MICREMARK, pdv.getMicremarks());
			map.put(DrugViewExecl.GENNAME, pdv.getGenname());
			map.put(DrugViewExecl.ISEQ, pdv.getIseq());
			map.put(DrugViewExecl.REPLICON, pdv.getReplicon());
			map.put(DrugViewExecl.GENTC, pdv.getGentc());
			map.put(DrugViewExecl.GENOPERATOR, pdv.getGenoperator());
			list.add(map);
		}
		return list;
	}

}
