package drug.dto.pageModel;

public class PStrainCoding {

	private String strainno;

	private String sampleno;

	private String straincategory;

	private String straintype;

	private String strainalias;

	private String gramstain;

	private String strainstoragedateStr;

	private String operator; // 菌种鉴定人

	private String serotype; // 血清型

	private String strainmlst; // MLST分型

	private String strainplg; // PLG分型

	private String strainparter; // 细菌分型人

	private String strainremarks;

	private String farmName;

	private String samplefarmaddr;

	private String sampleanimalage;

	private String samplesource;

	private String partName;

	private String samplecollector;

	private String samplemedicalhistory;

	private String otherMsg;

	public String getGramstain() {
		return gramstain;
	}

	public void setGramstain(String gramstain) {
		this.gramstain = gramstain;
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

	public String getStraincategory() {
		return straincategory;
	}

	public void setStraincategory(String straincategory) {
		this.straincategory = straincategory;
	}

	public String getStraintype() {
		return straintype;
	}

	public void setStraintype(String straintype) {
		this.straintype = straintype;
	}

	public String getStrainalias() {
		return strainalias;
	}

	public void setStrainalias(String strainalias) {
		this.strainalias = strainalias;
	}

	public String getStrainstoragedateStr() {
		return strainstoragedateStr;
	}

	public void setStrainstoragedateStr(String strainstoragedateStr) {
		this.strainstoragedateStr = strainstoragedateStr;
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

	public String getStrainremarks() {
		return strainremarks;
	}

	public void setStrainremarks(String strainremarks) {
		this.strainremarks = strainremarks;
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getSamplefarmaddr() {
		return samplefarmaddr;
	}

	public void setSamplefarmaddr(String samplefarmaddr) {
		this.samplefarmaddr = samplefarmaddr;
	}

	public String getSampleanimalage() {
		return sampleanimalage;
	}

	public void setSampleanimalage(String sampleanimalage) {
		this.sampleanimalage = sampleanimalage;
	}

	public String getSamplecollector() {
		return samplecollector;
	}

	public void setSamplecollector(String samplecollector) {
		this.samplecollector = samplecollector;
	}

	public String getSamplemedicalhistory() {
		return samplemedicalhistory;
	}

	public void setSamplemedicalhistory(String samplemedicalhistory) {
		this.samplemedicalhistory = samplemedicalhistory;
	}

	public String getSamplesource() {
		return samplesource;
	}

	public void setSamplesource(String samplesource) {
		this.samplesource = samplesource;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getOtherMsg() {
		return otherMsg;
	}

	public void setOtherMsg(String otherMsg) {
		this.otherMsg = otherMsg;
	}

	@Override
	public String toString() {
		return "PStrainCoding [strainno=" + strainno + ", sampleno=" + sampleno
				+ ", straincategory=" + straincategory + ", straintype="
				+ straintype + ", strainalias=" + strainalias + ", gramstain="
				+ gramstain + ", strainstoragedateStr=" + strainstoragedateStr
				+ ", operator=" + operator + ", serotype=" + serotype
				+ ", strainmlst=" + strainmlst + ", strainplg=" + strainplg
				+ ", strainparter=" + strainparter + ", strainremarks="
				+ strainremarks + ", farmName=" + farmName
				+ ", samplefarmaddr=" + samplefarmaddr + ", sampleanimalage="
				+ sampleanimalage + ", samplesource=" + samplesource
				+ ", partName=" + partName + ", samplecollector="
				+ samplecollector + ", samplemedicalhistory="
				+ samplemedicalhistory + ", otherMsg=" + otherMsg + "]";
	}

}
