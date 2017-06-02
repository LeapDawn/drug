package drug.dto.pageModel;

import java.util.List;

public class ImportResultModel {

	private int total; // 导入总记录数
	private int error; // 失败记录数
	private int success; // 成功记录数
	private List<?> data; // 导入失败数据

	public ImportResultModel() {

	}

	public ImportResultModel(List<?> data, int total) {
		this.total = total;
		this.data = data;
		this.error = data != null ? data.size() : 0;
		this.success = total - error;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ImportResultModel [total=" + total + ", error=" + error
				+ ", success=" + success + ", data=" + data + "]";
	}

}
