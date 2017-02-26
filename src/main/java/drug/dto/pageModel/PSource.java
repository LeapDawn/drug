package drug.dto.pageModel;

public class PSource {
	private Integer sourceno;

	private String remark;

	private String sourcename;

	private String ids;
	private String errorMsg;
	private int page;
	private int rows;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public Integer getSourceno() {
		return sourceno;
	}

	public void setSourceno(Integer sourceno) {
		this.sourceno = sourceno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename == null ? null : sourcename.trim();
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "PSource [sourceno=" + sourceno + ", remark=" + remark
				+ ", sourcename=" + sourcename + ", + errorMsg="
				+ getErrorMsg() + "]";
	}
}
