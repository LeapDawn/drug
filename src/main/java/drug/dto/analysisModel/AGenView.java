package drug.dto.analysisModel;

import drug.dto.listModel.LGenView;

public class AGenView extends LGenView {

	private String gen;

	private String statisticsType;

	private String timeWay;

	private String interval;

	public String getGen() {
		return gen == null?"":gen;
	}

	public void setGen(String gen) {
		this.gen = gen;
	}

	public String getStatisticsType() {
		return statisticsType == null?"":statisticsType;
	}

	public void setStatisticsType(String statisticsType) {
		this.statisticsType = statisticsType;
	}

	public String getTimeWay() {
		return timeWay == null?"":timeWay;
	}

	public void setTimeWay(String timeWay) {
		this.timeWay = timeWay;
	}

	public String getInterval() {
		return interval == null?"":interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
}
