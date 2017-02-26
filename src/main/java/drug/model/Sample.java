package drug.model;

import java.util.Date;

public class Sample {
	private String sampleno;

	private Integer animalno;

	private String animalName;

	private String samplesource;

	private String farmname;

	private String samplefarmaddr;

	private String samplecollectionpart;

	private String partName;

	private String sampleprovince;

	private Date sampledate;

	private String samplemedicalhistory;

	private String samplecollector;

	private String sampleremarks;

	private String samplealias;

	private String sampleanimalage;

	private Date addtime;

	private String adduser;

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno == null ? null : sampleno.trim();
	}

	public Integer getAnimalno() {
		return animalno;
	}

	public void setAnimalno(Integer animalno) {
		this.animalno = animalno;
	}

	public String getSamplesource() {
		return samplesource;
	}

	public void setSamplesource(String samplesource) {
		this.samplesource = samplesource == null ? null : samplesource.trim();
	}

	public String getFarmname() {
		return farmname;
	}

	public void setFarmname(String farmname) {
		this.farmname = farmname == null ? null : farmname.trim();
	}

	public String getSamplefarmaddr() {
		return samplefarmaddr;
	}

	public void setSamplefarmaddr(String samplefarmaddr) {
		this.samplefarmaddr = samplefarmaddr == null ? null : samplefarmaddr
				.trim();
	}

	public String getSamplecollectionpart() {
		return samplecollectionpart;
	}

	public void setSamplecollectionpart(String samplecollectionpart) {
		this.samplecollectionpart = samplecollectionpart == null ? null
				: samplecollectionpart.trim();
	}

	public String getSampleprovince() {
		return sampleprovince;
	}

	public void setSampleprovince(String sampleprovince) {
		this.sampleprovince = sampleprovince == null ? null : sampleprovince
				.trim();
	}

	public Date getSampledate() {
		return sampledate;
	}

	public void setSampledate(Date sampledate) {
		this.sampledate = sampledate;
	}

	public String getSamplemedicalhistory() {
		return samplemedicalhistory;
	}

	public void setSamplemedicalhistory(String samplemedicalhistory) {
		this.samplemedicalhistory = samplemedicalhistory == null ? null
				: samplemedicalhistory.trim();
	}

	public String getSamplecollector() {
		return samplecollector;
	}

	public void setSamplecollector(String samplecollector) {
		this.samplecollector = samplecollector == null ? null : samplecollector
				.trim();
	}

	public String getSampleremarks() {
		return sampleremarks;
	}

	public void setSampleremarks(String sampleremarks) {
		this.sampleremarks = sampleremarks == null ? null : sampleremarks
				.trim();
	}

	public String getSamplealias() {
		return samplealias;
	}

	public void setSamplealias(String samplealias) {
		this.samplealias = samplealias == null ? null : samplealias.trim();
	}

	public String getSampleanimalage() {
		return sampleanimalage;
	}

	public void setSampleanimalage(String sampleanimalage) {
		this.sampleanimalage = sampleanimalage == null ? null : sampleanimalage
				.trim();
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser == null ? null : adduser.trim();
	}

	@Override
	public String toString() {
		return "Sample [sampleno=" + sampleno + ", animalno=" + animalno
				+ ", animalName=" + animalName + ", samplesource="
				+ samplesource + ", farmname=" + farmname + ", samplefarmaddr="
				+ samplefarmaddr + ", samplecollectionpart="
				+ samplecollectionpart + ", partName=" + partName
				+ ", sampleprovince=" + sampleprovince + ", sampledate="
				+ sampledate + ", samplemedicalhistory=" + samplemedicalhistory
				+ ", samplecollector=" + samplecollector + ", sampleremarks="
				+ sampleremarks + ", samplealias=" + samplealias
				+ ", sampleanimalage=" + sampleanimalage + ", addtime="
				+ addtime + ", adduser=" + adduser + "]";
	}
}