package drug.commons.util;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static String checkContentType(MultipartFile file) {
		if (file == null) {
			return "上传的文件不能为空";
		}
		String type = file.getContentType();
		if (!"application/vnd.ms-excel".equals(type) && !"application/x-xls".equals(type)) {
			System.out.println("contentType:" + type);
			return "文件类型错误"; 
		}
		return null;
	}
	
}
