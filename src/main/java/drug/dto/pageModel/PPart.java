package drug.dto.pageModel;

public class PPart{

	private String partno;

	private String partname;

	private String partremarks;
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

	public String getPartno() {
		return partno;
	}

	public void setPartno(String partno) {
		this.partno = partno;
	}

	public String getPartname() {
		return partname;
	}

	public void setPartname(String partname) {
		this.partname = partname;
	}

	public String getPartremarks() {
		return partremarks;
	}

	public void setPartremarks(String partremarks) {
		this.partremarks = partremarks;
	}

	@Override
	public String toString() {
		return "PPart [partno=" + partno + ", partname=" + partname
				+ ", partremarks=" + partremarks + ", errorMsg="
				+ getErrorMsg() + "]";
	}
}
