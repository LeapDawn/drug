package drug.commons.excelModel;

public class DrugViewAllExcel {

	public static String SAMPLENO = "样品编号";
	public static String STRAINNO = "菌属编号";
	public static String ALIAS = "内部编号";
	public static String SAMPLEDATE = "采样时间";
	public static String SAMPLEPROVINCE = "采样省份";
	public static String FARMNAME = "采样地名称";
	public static String SAMPLEFARMADDR = "养殖场名称";
	public static String ANIMALNAME = "动物";
	public static String SAMPLEANIMALAGE = "日龄";
	public static String SAMPLESOURCE = "来源";
	public static String PARTNAME = "部位";
	public static String SAMPLECOLLECTOR = "采样人员";
	public static String SAMPLEMEDICALHISTORY = "动物用药史";
	public static String SAMPLEREMARKS = "样品备注";
	public static String STRAINCATEGORY = "菌属";
	public static String STRAINTYPE = "菌种";
	public static String STRAINSTORAGEDATE = "保存时间";
	public static String SEROTYPE = "血清型";
	public static String STRAINMLST = "MLST分型";
	public static String STRAINPLG = "PLG分型";
	public static String OPERATOR = "菌种鉴定人";
	public static String STRAINPARTER = "细菌分型人";
	public static String STRAINREMARKS = "菌株基本信息备注";

	public static String[] getExportColumns() {
		return new String[] { SAMPLENO, STRAINNO, ALIAS, SAMPLEDATE,
				SAMPLEPROVINCE, FARMNAME, SAMPLEFARMADDR, ANIMALNAME,
				SAMPLEANIMALAGE, SAMPLESOURCE, PARTNAME, SAMPLECOLLECTOR,
				SAMPLEMEDICALHISTORY, SAMPLEREMARKS, STRAINCATEGORY,
				STRAINTYPE, STRAINSTORAGEDATE, SEROTYPE, STRAINMLST, STRAINPLG,
				OPERATOR, STRAINPARTER, STRAINREMARKS };
	}
}
