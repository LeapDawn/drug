package drug.service;

import java.io.File;
import java.io.InputStream;

import drug.commons.exception.ExcelException;
import drug.dto.pageModel.ImportResultModel;

public interface UpDownService {
	
	/**
	 * 导入信息
	 * @param input
	 * @param user
	 * @return
	 * @throws ExcelException 
	 */
	public ImportResultModel importDatas(InputStream input, String user) throws ExcelException;
	
	/**
	 * 导出信息
	 * @param farmNames
	 * @param file
	 * @return
	 * @throws ExcelException 
	 */
	public File exportDatas(String ids, File file) throws ExcelException;
	
}
