package drug.model;

public class Role {
	private String roleno;

	private String rolename;

	private String remark;

	public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno == null ? null : roleno.trim();
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename == null ? null : rolename.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	@Override
	public String toString() {
		return "Role [roleno=" + roleno + ", rolename=" + rolename
				+ ", remark=" + remark + "]";
	}

}