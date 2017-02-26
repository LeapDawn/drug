package drug.dto.listModel;

import java.util.Arrays;

public class LSample {

	private int page;

	private int rows;

	private String sampleno;

	private Integer[] animalno;

	private String[] samplesource;

	private String farmname; // 采样地名称

	private String samplefarmaddr; // 养殖场名称

	private String[] samplecollectionpart;

	private String[] sampleprovince;

	private String beginDate;

	private String endDate;

	private String sampleanimalage;
	
	private String order;
	
	private String sort;
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort != null && sort.trim().toLowerCase().equals("desc") ? sort
				.trim() : "";
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
	}

	public String[] getSamplesource() {
		return samplesource;
	}

	public void setSamplesource(String[] samplesource) {
		this.samplesource = samplesource;
	}

	public String getFarmname() {
		return farmname;
	}

	public void setFarmname(String farmname) {
		this.farmname = farmname;
	}

	public String getSamplefarmaddr() {
		return samplefarmaddr;
	}

	public void setSamplefarmaddr(String samplefarmaddr) {
		this.samplefarmaddr = samplefarmaddr;
	}

	public String[] getSamplecollectionpart() {
		return samplecollectionpart;
	}

	public void setSamplecollectionpart(String[] samplecollectionpart) {
		this.samplecollectionpart = samplecollectionpart;
	}

	public String[] getSampleprovince() {
		return sampleprovince;
	}

	public void setSampleprovince(String[] sampleprovince) {
		this.sampleprovince = sampleprovince;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beiginDate) {
		this.beginDate = beiginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer[] getAnimalno() {
		return animalno;
	}

	public void setAnimalno(Integer[] animalno) {
		this.animalno = animalno;
	}

	public String getSampleanimalage() {
		return sampleanimalage;
	}

	public void setSampleanimalage(String sampleanimalage) {
		this.sampleanimalage = sampleanimalage;
	}

	@Override
	public String toString() {
		return "LSample [page=" + page + ", rows=" + rows + ", sampleno="
				+ sampleno + ", animalno=" + Arrays.toString(animalno)
				+ ", samplesource=" + Arrays.toString(samplesource)
				+ ", farmname=" + farmname + ", samplefarmaddr="
				+ samplefarmaddr + ", samplecollectionpart="
				+ Arrays.toString(samplecollectionpart) + ", sampleprovince="
				+ Arrays.toString(sampleprovince) + ", beiginDate="
				+ beginDate + ", endDate=" + endDate + ", sampleanimalage="
				+ sampleanimalage + "]";
	}

}
