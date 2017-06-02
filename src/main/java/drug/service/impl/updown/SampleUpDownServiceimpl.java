package drug.service.impl.updown;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import drug.commons.excelModel.SampleExcel;
import drug.commons.exception.DataViolationException;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.Province;
import drug.commons.util.Transfer;
import drug.dao.CollectionPartDAO;
import drug.dao.DetailAnimalDAO;
import drug.dao.FarmsDAO;
import drug.dao.SampleDAO;
import drug.dao.SourceDAO;
import drug.dto.pageModel.ImportResultModel;
import drug.dto.pageModel.PSample;
import drug.model.CollectionPart;
import drug.model.DetailAnimal;
import drug.model.Sample;
import drug.model.Source;
import drug.service.UpDownService;

@Service("sampleUpDown")
/**
 * 样品信息导入/导出
 */
public class SampleUpDownServiceimpl implements UpDownService{

	public static Logger log = Logger.getLogger("R");
	
	@Autowired
	private SampleDAO sampleDAO;
	@Autowired
	private SourceDAO sourceDAO;
	@Autowired
	private DetailAnimalDAO animalDAO;
	@Autowired
	private CollectionPartDAO partDAO;
	@Autowired
	private FarmsDAO farmsDAO;
	
	@Override
	public ImportResultModel importDatas(InputStream input, String user)
			throws ExcelException {
		String[] importColumns = SampleExcel.getImportColumns();
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setModelArray(importColumns);
		try {
			execlUtil.readExecl(input);
		}  catch (ExcelException e) {
			throw e;
		}
		List<Map<String, Object>> bodyList = execlUtil.getBodyList();
		List<PSample> pageList = this.transfer(bodyList, user);
		List<Sample> addList = new ArrayList<Sample>();
		List<PSample> errorList = CheckAttrs(addList, pageList);
		for (Sample sample : addList) {
			try {
				sample.setAdduser(user);
				this.executeInsert(sample);
				log.info("【样品信息导入成功】："+ sample+"【"+user+"】");
			} catch (Exception e) {
				e.printStackTrace();
				String errorMsg = "";
				if (e instanceof DuplicateKeyException) {
					errorMsg = "样品编号重复,请重新添加";
				} else if (e instanceof DataViolationException) {
					errorMsg = e.getMessage();
				} else if (e instanceof DataIntegrityViolationException) {
					errorMsg = "该样品信息记录存在不合法信息(某些字段长度过长)";
				} else if (e instanceof CannotCreateTransactionException 
						|| e instanceof DataAccessResourceFailureException) {
					errorMsg = "数据库服务异常,请重新添加";
				} else {
					errorMsg = "未知异常";
				}
				PSample psample = Transfer.changetoPageModel(sample);
				psample.setOtherMsg(errorMsg);
				errorList.add(psample);
				log.error("【导入样品信息异常】："+psample+ e +"【"+user+"】");
			}
		}
		ImportResultModel result = new ImportResultModel(errorList, bodyList.size());
		return result;
	}

	@Override
	public File exportDatas(String nos, File file)
			throws ExcelException {
		if (nos == null || nos.trim().equals("") || nos.trim().equals(",")){
			throw new DataViolationException("没有选择导出的样品信息");
		}
		String[] samplenos = nos.trim().split(",");
		List<Sample> sampleList = sampleDAO.selectByNos(samplenos);
		
		List<Map<String, Object>> bodyList = this.retransfer(sampleList);
		
		ExeclUtil execlUtil = new ExeclUtil();
		execlUtil.setHeadArray(SampleExcel.getExportColumns());
		execlUtil.setBodyList(bodyList);
		try {
			execlUtil.writeExecl(file, "样品信息");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入execl文件失败");
		}
		return file;
	}
	
	// 将execl文件中的信息转化为待导入的信息页面模型集合
	private List<PSample> transfer(List<Map<String, Object>> bodyList, String user) {
		List<PSample> sampleList = new ArrayList<PSample>();
		PSample psample = null;
		for (Map<String, Object> map : bodyList) {
			psample = new PSample();
			psample.setSampledateStr((String)map.get(SampleExcel.SAMPLEDATE));
			psample.setSampleprovince((String)map.get(SampleExcel.SAMPLEPROVINCE));
			psample.setFarmname((String)map.get(SampleExcel.FARMNAME));
			psample.setSamplefarmaddr((String)map.get(SampleExcel.SAMPLEFARMADDR));
			psample.setAnimalName((String)map.get(SampleExcel.ANIMALNAME));
			psample.setSampleanimalage((String)map.get(SampleExcel.SAMPLEANIMALAGE));
			psample.setSamplesource((String)map.get(SampleExcel.SAMPLESOURCE));
			psample.setPartName((String)map.get(SampleExcel.PARTNAME));
			psample.setSamplecollector((String)map.get(SampleExcel.SAMPLECOLLECTOR));
			psample.setSamplemedicalhistory((String)map.get(SampleExcel.SAMPLEMEDICALHISTORY));
			psample.setSampleremarks((String)map.get(SampleExcel.SAMPLEREMARKS));
			psample.setOtherMsg(user);
			sampleList.add(psample);
		}
		return sampleList;
	}

	// 将信息列表转化为execl文件中的信息
	private List<Map<String, Object>> retransfer(List<Sample> sampleList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Sample sample : sampleList) {
			Map<String, Object> map = new HashMap<String, Object>();
			PSample psample = Transfer.changetoPageModel(sample);
			map.put(SampleExcel.SAMPLENO, psample.getSampleno());
			map.put(SampleExcel.SAMPLEDATE, psample.getSampledateStr());
			map.put(SampleExcel.SAMPLEPROVINCE, psample.getSampleprovince());
			map.put(SampleExcel.FARMNAME, psample.getFarmname());
			map.put(SampleExcel.SAMPLEFARMADDR, psample.getSamplefarmaddr());
			map.put(SampleExcel.ANIMALNAME, psample.getAnimalName());
			map.put(SampleExcel.SAMPLEANIMALAGE, psample.getSampleanimalage());
			map.put(SampleExcel.SAMPLESOURCE, psample.getSamplesource());
			map.put(SampleExcel.PARTNAME, psample.getPartName());
			map.put(SampleExcel.SAMPLECOLLECTOR, psample.getSamplecollector());
			map.put(SampleExcel.SAMPLEMEDICALHISTORY, psample.getSamplemedicalhistory());
			map.put(SampleExcel.SAMPLEREMARKS, psample.getSampleremarks());
			list.add(map);
		}
		return list;
	}
	
	// 对pageList的记录进行检查,返回错误列表
	private List<PSample> CheckAttrs(List<Sample> addList, List<PSample> pageList){
		List<PSample> errorList = new ArrayList<PSample>();
		List<DetailAnimal> animalList = animalDAO.selectAll();
		List<Source> sourcelist = sourceDAO.selectAll();
		List<CollectionPart> partList = partDAO.selectAll();
		List<String> farmsList = farmsDAO.selectAll(null);
		for (PSample pSample : pageList) {
			StringBuffer sb = new StringBuffer();
			Boolean finalFlag = false;   // 该条记录是否错误
			Boolean flag = false;     // 每个验证细节是否错误

			// 验证year
			String date = pSample.getSampledateStr();
			if (date == null || date.length() != 10){
				finalFlag = true;
				pSample.setOtherMsg("添加失败,日期格式错误,实例:2016-01-01");
			}
			sb.append(date.substring(2, 4));
			
			// 验证省份
			String provinceNo = Province.getProvinceNo(pSample.getSampleprovince());
			if (provinceNo == null || provinceNo.trim().equals("")) {
				finalFlag = true;
				pSample.setOtherMsg("省份[" + pSample.getSampleprovince() + "]错误");
			}
			sb.append(provinceNo);
			
			// 检查来源
			flag = false;
			for (Source source : sourcelist) {
				if (source.getSourcename().equals(pSample.getSamplesource())) {
					flag = true;
					sb.append(source.getSourceno());
					break;
				}
			}
			if (!flag) {
				finalFlag = true;
				pSample.setOtherMsg("来源[" + pSample.getSamplesource() + "]不存在,请在[编号管理-来源编号]页面添加");
			}
			
			// 检查采样动物
			flag = false;
			if (!pSample.getAnimalName().trim().equals("")) {
				for (DetailAnimal animal : animalList) {
					if (animal.getAnimalname().equals(pSample.getAnimalName())) {
						pSample.setAnimalno(animal.getAnimalno());
						sb.append(pSample.getAnimalno());
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				finalFlag = true;
				pSample.setOtherMsg("采样动物[" + pSample.getAnimalName() + "]不存在,请在[编号管理-动物编号]页面添加");
			}
			
			// 检查部位
			flag = false;
			for (CollectionPart collectionPart : partList) {
				if (collectionPart.getPartname().equals(pSample.getPartName())) {
					pSample.setSamplecollectionpart(collectionPart.getPartno());
					flag = true;
					break;
				}
			}
			if (!flag) {
				finalFlag = true;
				pSample.setOtherMsg("部位[" + pSample.getPartName() + "]不存在,请在[编号管理-部位编号]页面添加");
			}
			
			// 检查采样地
			if (!farmsList.contains(pSample.getFarmname())) {
				finalFlag = true;
				pSample.setOtherMsg("采样地[" + pSample.getFarmname() + "]不存在,请在[数据管理-采样地信息]页面添加");
			}
			
			if(!finalFlag) {
				pSample.setSampleno(sb.toString());
				try {
					Sample entity = Transfer.changeToEntity(pSample);
					addList.add(entity);
				} catch (Exception e) {
					pSample.setOtherMsg(e.getMessage());
					errorList.add(pSample);
				}
			} else {
				errorList.add(pSample);
			}
		}
		return errorList;
	}
	
	// 执行插入样品操作
	private void executeInsert(Sample sample) {
		String samplenoPrefix = sample.getSampleno();
		String maxNo = sampleDAO.selectMaxNo(samplenoPrefix);
		if (maxNo == null || "".equals(maxNo)) {
			sample.setSampleno(samplenoPrefix + "0001");
		} else {
			sample.setSampleno(String.valueOf(Long.valueOf(maxNo) + 1));
		}
		sampleDAO.insert(sample);
	}
	
	public void setSourceDAO(SourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public void setAnimalDAO(DetailAnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}

	public void setPartDAO(CollectionPartDAO partDAO) {
		this.partDAO = partDAO;
	}

	public void setFarmsDAO(FarmsDAO farmsDAO) {
		this.farmsDAO = farmsDAO;
	}

	public void setSampleDAO(SampleDAO sampleDAO) {
		this.sampleDAO = sampleDAO;
	}
	
}
