package drug.dto.pageModel;

import java.util.List;

public class PRole {

	private String roleno;

	private String rolename;

	private String remark;

	private List<String> functions;

	private int rows;

	private int page;

	public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<String> getFunctions() {
		return functions;
	}

	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PRole [roleno=" + roleno + ", rolename=" + rolename
				+ ", remark=" + remark + ", functions=" + functions + "]";
	}
}
