package drug.dto.analysisModel;

import drug.dto.listModel.LDrugView;

public class ADrugView extends LDrugView{

	private String drug;

	private String strain;

	private String statisticsType;

	private String timeWay;

	private Float medLimit;

	public Float getMedLimit() {
		return medLimit;
	}

	public void setMedLimit(Float medLimit) {
		this.medLimit = medLimit;
	}

	public String getDrug() {
		return drug != null ? drug:"";
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getStrain() {
		return strain != null ? strain:"";
	}

	public void setStrain(String strain) {
		this.strain = strain;
	}

	public String getStatisticsType() {
		return statisticsType !=null ? statisticsType:"";
	}

	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	public String getTimeWay() {
		return timeWay != null ? timeWay:"";
	}

	public void setTimeWay(String timeWay) {
		this.timeWay = timeWay;
	}
}
