package drug.dto.listModel;

import java.util.Arrays;

public class LStrainCoding {

	private String strainno;

	private String sampleno;

	private String[] straincategory;

	private String[] straintype;

	private String strainalias;

	private String operator; // 菌种鉴定人

	private String serotype; // 血清型

	private String strainmlst; // MLST分型

	private String strainplg; // PLG分型

	private String strainparter; // 细菌分型人

	private String beginDate;

	private String endDate;

	private int page;

	private int rows;

	private String order;

	private String sort;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.sort = sort != null && sort.toLowerCase().trim().equals("desc")?"desc":null;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort != null && sort.trim().toLowerCase().equals("desc") ? sort
				.trim() : "";
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

	public String getStrainno() {
		return strainno;
	}

	public void setStrainno(String strainno) {
		this.strainno = strainno;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
	}

	public String getStrainalias() {
		return strainalias;
	}

	public void setStrainalias(String strainalias) {
		this.strainalias = strainalias;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSerotype() {
		return serotype;
	}

	public void setSerotype(String serotype) {
		this.serotype = serotype;
	}

	public String getStrainmlst() {
		return strainmlst;
	}

	public void setStrainmlst(String strainmlst) {
		this.strainmlst = strainmlst;
	}

	public String getStrainplg() {
		return strainplg;
	}

	public void setStrainplg(String strainplg) {
		this.strainplg = strainplg;
	}

	public String getStrainparter() {
		return strainparter;
	}

	public void setStrainparter(String strainparter) {
		this.strainparter = strainparter;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String[] getStraincategory() {
		return straincategory;
	}

	public void setStraincategory(String[] straincategory) {
		this.straincategory = straincategory;
	}

	public String[] getStraintype() {
		return straintype;
	}

	public void setStraintype(String[] straintype) {
		this.straintype = straintype;
	}

	@Override
	public String toString() {
		return "LStrainCoding [strainno=" + strainno + ", sampleno=" + sampleno
				+ ", straincategory=" + Arrays.toString(straincategory)
				+ ", straintype=" + Arrays.toString(straintype)
				+ ", strainalias=" + strainalias + ", operator=" + operator
				+ ", serotype=" + serotype + ", strainmlst=" + strainmlst
				+ ", strainplg=" + strainplg + ", strainparter=" + strainparter
				+ ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}

}
