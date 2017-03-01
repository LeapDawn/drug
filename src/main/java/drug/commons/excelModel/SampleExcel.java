package drug.commons.excelModel;

public class SampleExcel {
	public static String SAMPLENO = "样品编号";
	public static String ANIMALNAME = "动物";
	public static String SAMPLESOURCE = "来源";
	public static String FARMNAME = "采样地名称";
	public static String SAMPLEFARMADDR = "养殖场名称";
	public static String PARTNAME = "部位";
	public static String SAMPLEPROVINCE = "采样省份";
	public static String SAMPLEDATE = "采样时间";
	public static String SAMPLEMEDICALHISTORY = "动物用药史";
	public static String SAMPLECOLLECTOR = "采样人员";
	public static String SAMPLEREMARKS = "备注";
	public static String SAMPLEANIMALAGE = "日龄";

	public static String[] getExportColumns() {
		return new String[] { SAMPLENO, SAMPLEDATE, SAMPLEPROVINCE, FARMNAME,
				SAMPLEFARMADDR, ANIMALNAME, SAMPLEANIMALAGE, SAMPLESOURCE,
				PARTNAME, SAMPLECOLLECTOR, SAMPLEMEDICALHISTORY, SAMPLEREMARKS };
	}

	public static String[] getImportColumns() {
		return new String[] { SAMPLEDATE, SAMPLEPROVINCE, FARMNAME,
				SAMPLEFARMADDR, ANIMALNAME, SAMPLEANIMALAGE, SAMPLESOURCE,
				PARTNAME, SAMPLECOLLECTOR, SAMPLEMEDICALHISTORY, SAMPLEREMARKS };
	}

}
