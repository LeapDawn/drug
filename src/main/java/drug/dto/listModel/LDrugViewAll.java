package drug.dto.listModel;

public class LDrugViewAll {

	private String sampleno;

	private String strainalias;

	private String beginDate;

	private String endDate;

	private String[] province;

	private String farmname;

	private String farmaddr;

	private String[] category;

	private String[] animalName;

	private String[] partName;

	private String[] source;

	private String animalAge;

	private int rows;

	private int page;

	private String order;

	private String sort;

	public String getStrainalias() {
		return strainalias;
	}

	public void setStrainalias(String strainalias) {
		this.strainalias = strainalias;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
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

	public String[] getProvince() {
		return province;
	}

	public void setProvince(String[] province) {
		this.province = province;
	}

	public String getFarmname() {
		return farmname;
	}

	public void setFarmname(String farmname) {
		this.farmname = farmname;
	}

	public String getFarmaddr() {
		return farmaddr;
	}

	public void setFarmaddr(String farmaddr) {
		this.farmaddr = farmaddr;
	}

	public String[] getCategory() {
		return category;
	}

	public void setCategory(String[] category) {
		this.category = category;
	}

	public String[] getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String[] animalName) {
		this.animalName = animalName;
	}

	public String[] getPartName() {
		return partName;
	}

	public void setPartName(String[] partName) {
		this.partName = partName;
	}

	public String[] getSource() {
		return source;
	}

	public void setSource(String[] source) {
		this.source = source;
	}

	public String getAnimalAge() {
		return animalAge;
	}

	public void setAnimalAge(String animalAge) {
		this.animalAge = animalAge;
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
		this.sort = sort;
	}
}
