package drug.dto.pageModel;

public class PGenotypingDir {

	private Integer id;
	private String genotyping1;
	private String genotyping2;
	private String genotyping3;
	private String remark;
	private String ids;
	private int page;
	private int rows;
	private String errorMsg;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenotyping1() {
		return genotyping1;
	}

	public void setGenotyping1(String genotyping1) {
		this.genotyping1 = genotyping1;
	}

	public String getGenotyping2() {
		return genotyping2;
	}

	public void setGenotyping2(String genotyping2) {
		this.genotyping2 = genotyping2;
	}

	public String getGenotyping3() {
		return genotyping3;
	}

	public void setGenotyping3(String genotyping3) {
		this.genotyping3 = genotyping3;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String otherMsg) {
		this.errorMsg = otherMsg != null ? otherMsg : "";
	}
}
