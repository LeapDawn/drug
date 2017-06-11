package drug.dto.pageModel;

public class PGenView {
	private Integer id;

	private String strainno;

	private String genname;

	private String genalias;

	private String genremarks;

	private String sampleno;

	private String sampledate;

	private Integer animalno;

	private String animalname;

	private String sampleProvince;

	private String genotyping1;

	private String genotyping2;

	private String genotyping3;

	private String sampleSource;

	private String farmName;

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getSampleSource() {
		return sampleSource;
	}

	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStrainno() {
		return strainno;
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

	public String getSampledate() {
		return sampledate;
	}

	public void setSampledate(String sampledate) {
		this.sampledate = sampledate;
	}

	public Integer getAnimalno() {
		return animalno;
	}

	public void setAnimalno(Integer animalno) {
		this.animalno = animalno;
	}

	public String getAnimalname() {
		return animalname;
	}

	public void setAnimalname(String animalname) {
		this.animalname = animalname;
	}

	public String getSampleProvince() {
		return sampleProvince;
	}

	public void setSampleProvince(String sampleProvince) {
		this.sampleProvince = sampleProvince;
	}

	public String getGenotyping1() {
		return genotyping1;
	}

	public void setGenotyping1(String genotyping1) {
		this.genotyping1 = genotyping1;
	}

	public String getGenotyping2() {
		return genotyping2;
	}

	public void setGenotyping2(String genotyping2) {
		this.genotyping2 = genotyping2;
	}

	public String getGenotyping3() {
		return genotyping3;
	}

	public void setGenotyping3(String genotyping3) {
		this.genotyping3 = genotyping3;
	}

	@Override
	public String toString() {
		return "PGenView [id=" + id + ", strainno=" + strainno + ", genname="
				+ genname + ", genalias=" + genalias + ", genremarks="
				+ genremarks + ", sampleno=" + sampleno + ", sampledate="
				+ sampledate + ", animalno=" + animalno + ", animalname="
				+ animalname + ", sampleProvince=" + sampleProvince
				+ ", genotyping1=" + genotyping1 + ", genotyping2="
				+ genotyping2 + ", genotyping3=" + genotyping3 + "]";
	}
}
