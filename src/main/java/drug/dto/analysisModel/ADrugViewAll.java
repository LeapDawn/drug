package drug.dto.analysisModel;

import drug.dto.listModel.LDrugViewAll;

public class ADrugViewAll extends LDrugViewAll{

	private String strain;

	private String statisticsType;
	
	private String strainalias;

	private String timeWay;

	public String getStrainalias() {
		return strainalias;
	}

	public void setStrainalias(String strainalias) {
		this.strainalias = strainalias;
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
