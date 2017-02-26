package drug.dto.pageModel;

public class PStandardMic {

	private String medicalname;
	private String standardname;
	private String strainname;
	private Float medlimit;
	private String remark;

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

	public Float getMedlimit() {
		return medlimit;
	}

	public void setMedlimit(Float medlimit) {
		this.medlimit = medlimit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMedicalname() {
		return medicalname;
	}

	public void setMedicalname(String medicalName) {
		this.medicalname = medicalName;
	}

	public String getStandardname() {
		return standardname;
	}

	public void setStandardname(String standardName) {
		this.standardname = standardName;
	}

	public String getStrainname() {
		return strainname;
	}

	public void setStrainname(String strainName) {
		this.strainname = strainName;
	}

	@Override
	public String toString() {
		return "PStandardMic [medicalName=" + medicalname + ", standardName="
				+ standardname + ", strainName=" + strainname + ", medlimit="
				+ medlimit + ", remark=" + remark + ", errorMsg="
				+ getErrorMsg() + "]";
	}
}
