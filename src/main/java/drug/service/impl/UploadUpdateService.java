package drug.service.impl;

import java.io.InputStream;

import drug.commons.exception.ExcelException;
import drug.dto.pageModel.UploadResultModel;

// 通过上传execl文件的方式实现批量修改数据
public interface UploadUpdateService {
	
	/**
	 * 上传execl文件实现有选择性批量修改数据属性
	 * @param input
	 * @param user
	 * @param gram
	 * @return
	 * @throws ExcelException 
	 */
	public UploadResultModel uploadUpdate(InputStream input, String gram, String user) throws ExcelException;
	
}
