package drug.dto.pageModel;

public class PStrainToNotes{

	private Integer id;

	private String strainname;

	private String straincategory;

	private String strainnotes;

	private String strainremark;

	private String gramstain;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStrainname() {
		return strainname;
	}

	public void setStrainname(String strainname) {
		this.strainname = strainname;
	}

	public String getStraincategory() {
		return straincategory;
	}

	public void setStraincategory(String straincategory) {
		this.straincategory = straincategory;
	}

	public String getStrainnotes() {
		return strainnotes;
	}

	public void setStrainnotes(String strainnotes) {
		this.strainnotes = strainnotes;
	}

	public String getStrainremark() {
		return strainremark;
	}

	public void setStrainremark(String strainremark) {
		this.strainremark = strainremark;
	}

	public String getGramstain() {
		return gramstain;
	}

	public void setGramstain(String gramstain) {
		this.gramstain = gramstain;
	}

	@Override
	public String toString() {
		return "PStrainToNotes [id=" + id + ", strainname=" + strainname
				+ ", straincategory=" + straincategory + ", strainnotes="
				+ strainnotes + ", strainremark=" + strainremark
				+ ", gramstain=" + gramstain + ", errorMsg=" + getErrorMsg()
				+ "]";
	}

}
