package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExeclException;
import drug.commons.execlModel.FarmExecl;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Province;
import drug.commons.util.Transfer;
import drug.dao.FarmsDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PFarms;
import drug.model.Farms;
import drug.service.UpDownService;

@Service("farmsUpDown")
public class FarmsUpDownServiceImpl implements UpDownService {

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private FarmsDAO farmDAO;
	public void setFarmDAO(FarmsDAO farmDAO) {
		this.farmDAO = farmDAO;
	}
	
	@Override
	public ImportResultModel importDatas(InputStream input, String user) throws ExeclException {
		String[] importColumns = FarmExecl.getImportColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExeclException e) {
			throw e;
		}
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<Farms> add_list = this.transfer(bodyList, user);
		List<PFarms> errorList = new ArrayList<PFarms>();
		this.CheckAttrs(add_list, errorList);
		for (Farms farms : add_list) {
			String errorMsg = "";
			try {
				farms.setAdduser(user);
				farmDAO.insert(farms);
				log.info("【采样地信息导入成功】："+farms.toString()+"【"+user+"】");
			} catch(Exception e) {
				if (e instanceof DuplicateKeyException) {
					errorMsg = "该采样地信息已经存在";
				} else if (e instanceof DataIntegrityViolationException) {
					errorMsg = "采样地信息存在不合法信息,请重新添加(某些字段长度过长)";
				} else {
					errorMsg = "未知异常";
				}
				PFarms pfarms = Transfer.changeToPageModel(farms);
				pfarms.setErrorMsg(errorMsg);
				log.error("【采样地信息导入异常】：" + pfarms + e +"【"+user+"】");
				errorList.add(pfarms);
			}
		}
		ImportResultModel result = new ImportResultModel(errorList, add_list.size());
		return result;
	}

	@Override
	public File exportDatas(String farmNames, File file) throws ExeclException {
		if (farmNames == null || farmNames.trim().equals("") || farmNames.trim().equals(",")){
			throw new DataViolationException("没有选择导出的采样地信息");
		}
		String[] names = farmNames.trim().split(",");
		List<Farms> list = farmDAO.selectByNames(names);
		
		List<Map<String, Object>> bodyList = this.retransfer(list);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(FarmExecl.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "采样地信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExeclException("写入execl文件失败");
		}
		return file;
	}
	
	// 将execl文件中的信息转化为待导入的采样地信息列表
	private List<Farms> transfer(List<Map<String, Object>> bodyList, String user) {
		List<Farms> farmsList = new ArrayList<Farms>();
		Farms farms = null;
		int i = 0;
		for (Map<String, Object> map : bodyList) {
			farms = new Farms();
			farms.setFarmname((String)map.get(FarmExecl.FARMNAME));
			farms.setFarmprovince((String)map.get(FarmExecl.FRAMPROVINCE));
			farms.setFarmaddress((String)map.get(FarmExecl.FARMADDRESS));
			farms.setFarmlinkman((String)map.get(FarmExecl.FARMLINKMAN));
			farms.setFarmphone((String)map.get(FarmExecl.FARMPHONE));
			farms.setFarmraiseway((String)map.get(FarmExecl.FARMRAISEWAY));
			farms.setFarmraisescope((String)map.get(FarmExecl.FARMRAISESCOPE));
			farms.setFarmraisetype((String)map.get(FarmExecl.FARMRAISETYPE));
			farms.setFarmbuilddate((String)map.get(FarmExecl.FARMBUILEDATE));
			farms.setFarmremarks((String)map.get(FarmExecl.FARMREMARK));
			farms.setAdduser(user);
			farms.setAddtime(new Timestamp(System.currentTimeMillis() + i));
			i++;
			farmsList.add(farms);
		}
		return farmsList;
	}
	
	// 将采样地信息列表转化为execl文件中的信息
	private List<Map<String, Object>> retransfer(List<Farms> farmsList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Farms farms : farmsList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(FarmExecl.FARMNAME, farms.getFarmname());
			map.put(FarmExecl.FRAMPROVINCE, farms.getFarmprovince());
			map.put(FarmExecl.FARMADDRESS, farms.getFarmaddress());
			map.put(FarmExecl.FARMLINKMAN, farms.getFarmlinkman());
			map.put(FarmExecl.FARMPHONE, farms.getFarmphone());
			map.put(FarmExecl.FARMRAISEWAY, farms.getFarmraiseway());
			map.put(FarmExecl.FARMRAISESCOPE, farms.getFarmraisescope());
			map.put(FarmExecl.FARMRAISETYPE, farms.getFarmraisetype());
			map.put(FarmExecl.FARMBUILEDATE, farms.getFarmbuilddate());
			map.put(FarmExecl.FARMREMARK, farms.getFarmremarks());
			list.add(map);
		}
		return list;
	}
	
	private void CheckAttrs(List<Farms> addList, List<PFarms> errorList){
		for (Farms farms : addList) {
			String errorMsg = null;
			if (farms.getFarmname().trim().equals("")) {
				errorMsg = "采样地名称不能为空";
			} else if(farms.getFarmprovince().trim().equals("")) {
				errorMsg = "省份不能为空";
			} else if(Province.getProvinceNo(farms.getFarmprovince()) == null){
				errorMsg = "省份错误";
			}
			if (errorMsg != null) {
				PFarms pfarms = new PFarms();
				Transfer.changeToPageModel(farms);
				pfarms.setErrorMsg(errorMsg);
				errorList.add(pfarms);
				addList.remove(farms);
			}
		}
	}
}
