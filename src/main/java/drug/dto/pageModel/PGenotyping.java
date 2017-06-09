package drug.dto.pageModel;

public class PGenotyping {
	private Integer id;

	private String genname;

	private String genotyping;

	private String remark;

	private String errorMsg;

	private String ids;

	private int page;

	private int rows;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenname() {
		return genname;
	}

	public void setGenname(String genname) {
		this.genname = genname;
	}

	public String getGenotyping() {
		return genotyping;
	}

	public void setGenotyping(String genotyping) {
		this.genotyping = genotyping;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
}
