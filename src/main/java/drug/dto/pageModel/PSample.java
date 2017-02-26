package drug.dto.pageModel;

public class PSample {

	private String sampleno;

	private Integer animalno;

	private String animalName;

	private String samplesource;

	private String farmname;

	private String samplefarmaddr;

	private String samplecollectionpart;

	private String partName;

	private String sampleprovince;

	private String sampledateStr;

	private String samplemedicalhistory;

	private String samplecollector;

	private String sampleremarks;

	private String sampleanimalage;

	private String otherMsg;

	public String getOtherMsg() {
		return otherMsg;
	}

	public void setOtherMsg(String errorMsg) {
		this.otherMsg = errorMsg;
	}

	public String getSampleno() {
		return sampleno;
	}

	public void setSampleno(String sampleno) {
		this.sampleno = sampleno;
	}

	public Integer getAnimalno() {
		return animalno;
	}

	public void setAnimalno(Integer animalno) {
		this.animalno = animalno;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public String getSamplesource() {
		return samplesource;
	}

	public void setSamplesource(String samplesource) {
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

	public String getSamplecollectionpart() {
		return samplecollectionpart;
	}

	public void setSamplecollectionpart(String samplecollectionpart) {
		this.samplecollectionpart = samplecollectionpart;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getSampleprovince() {
		return sampleprovince;
	}

	public void setSampleprovince(String sampleprovince) {
		this.sampleprovince = sampleprovince;
	}

	public String getSampledateStr() {
		return sampledateStr;
	}

	public void setSampledateStr(String sampledate) {
		this.sampledateStr = sampledate;
	}

	public String getSamplemedicalhistory() {
		return samplemedicalhistory;
	}

	public void setSamplemedicalhistory(String samplemedicalhistory) {
		this.samplemedicalhistory = samplemedicalhistory;
	}

	public String getSamplecollector() {
		return samplecollector;
	}

	public void setSamplecollector(String samplecollector) {
		this.samplecollector = samplecollector;
	}

	public String getSampleremarks() {
		return sampleremarks;
	}

	public void setSampleremarks(String sampleremarks) {
		this.sampleremarks = sampleremarks;
	}

	public String getSampleanimalage() {
		return sampleanimalage;
	}

	public void setSampleanimalage(String sampleanimalage) {
		this.sampleanimalage = sampleanimalage;
	}

	@Override
	public String toString() {
		return "PSample [sampleno=" + sampleno + ", animalno=" + animalno
				+ ", animalName=" + animalName + ", samplesource="
				+ samplesource + ", farmname=" + farmname + ", samplefarmaddr="
				+ samplefarmaddr + ", samplecollectionpart="
				+ samplecollectionpart + ", partName=" + partName
				+ ", sampleprovince=" + sampleprovince + ", sampledate="
				+ sampledateStr + ", samplemedicalhistory=" + samplemedicalhistory
				+ ", samplecollector=" + samplecollector + ", sampleremarks="
				+ sampleremarks + ", sampleanimalage=" + sampleanimalage
				+ ", errorMsg=" + getOtherMsg() + "]";
	}
}
