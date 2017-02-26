package drug.service;

import java.io.File;
import java.io.InputStream;

import drug.commons.exception.ExeclException;
import drug.dto.pageModel.ImportResultModel;

public interface UpDownService {
	
	/**
	 * 导入信息
	 * @param input
	 * @param user
	 * @return
	 * @throws ExeclException 
	 */
	public ImportResultModel importDatas(InputStream input, String user) throws ExeclException;
	
	/**
	 * 导出信息
	 * @param farmNames
	 * @param file
	 * @return
	 * @throws ExeclException 
	 */
	public File exportDatas(String ids, File file) throws ExeclException;
	
}
