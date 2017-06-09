package drug.dto.listModel;

import java.util.Set;

public class LGenView {
	private String strainno;

	private String genname;

	private String genalias;

	private String genremarks;

	private String sampleno;

	private String beginDate;

	private String endDate;

	private String[] province;

	private String[] animalname;

	private Set<Integer> animalNos;

	private String[] genotyping1;

	private String[] genotyping2;

	private String[] genotyping3;

	private String[] sampleSource;

	private int rows;
	private int page;
	private String sort;
	private String order;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Set<Integer> getAnimalNos() {
		return animalNos;
	}

	public void setAnimalNos(Set<Integer> animalNos) {
		this.animalNos = animalNos;
	}

	public String getStrainno() {
		return strainno;
	}

	public String[] getSampleSource() {
		return sampleSource;
	}

	public void setSampleSource(String[] sampleSource) {
		this.sampleSource = sampleSource;
	}

	public void setStrainno(String strainno) {
		this.strainno = strainno;
	}

	public String getGenname() {
		return genname;
	}

	public void setGenname(String genname) {
		this.genname = genname;
	}

	public String getGenalias() {
		return genalias;
	}

	public void setGenalias(String genalias) {
		this.genalias = genalias;
	}

	public String getGenremarks() {
		return genremarks;
	}

	public void setGenremarks(String genremarks) {
		this.genremarks = genremarks;
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

	public String[] getAnimalname() {
		return animalname;
	}

	public void setAnimalname(String[] animalname) {
		this.animalname = animalname;
	}

	public String[] getProvince() {
		return province;
	}

	public void setProvince(String[] province) {
		this.province = province;
	}

	public String[] getGenotyping1() {
		return genotyping1;
	}

	public void setGenotyping1(String[] genotyping1) {
		this.genotyping1 = genotyping1;
	}

	public String[] getGenotyping2() {
		return genotyping2;
	}

	public void setGenotyping2(String[] genotyping2) {
		this.genotyping2 = genotyping2;
	}

	public String[] getGenotyping3() {
		return genotyping3;
	}

	public void setGenotyping3(String[] genotyping3) {
		this.genotyping3 = genotyping3;
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
}
