package drug.commons.excelModel;

public class DrugViewAllExcel {

	public final static String SAMPLENO = "样品编号";
	public final static String STRAINNO = "菌属编号";
	public final static String ALIAS = "内部编号";
	public final static String SAMPLEDATE = "采样时间";
	public final static String SAMPLEPROVINCE = "采样省份";
	public final static String FARMNAME = "采样地名称";
	public final static String SAMPLEFARMADDR = "养殖场名称";
	public final static String ANIMALNAME = "动物";
	public final static String SAMPLEANIMALAGE = "日龄";
	public final static String SAMPLESOURCE = "来源";
	public final static String PARTNAME = "部位";
	public final static String SAMPLECOLLECTOR = "采样人员";
	public final static String SAMPLEMEDICALHISTORY = "动物用药史";
	public final static String SAMPLEREMARKS = "样品备注";
	public final static String STRAINCATEGORY = "菌属";
	public final static String STRAINTYPE = "菌种";
	public final static String STRAINSTORAGEDATE = "保存时间";
	public final static String SEROTYPE = "血清型";
	public final static String STRAINMLST = "MLST分型";
	public final static String STRAINPLG = "PLG分型";
	public final static String OPERATOR = "菌种鉴定人";
	public final static String STRAINPARTER = "细菌分型人";
	public final static String STRAINREMARKS = "菌株基本信息备注";

	public static String[] getExportColumns() {
		return new String[] { SAMPLENO, STRAINNO, ALIAS, SAMPLEDATE,
				SAMPLEPROVINCE, FARMNAME, SAMPLEFARMADDR, ANIMALNAME,
				SAMPLEANIMALAGE, SAMPLESOURCE, PARTNAME, SAMPLECOLLECTOR,
				SAMPLEMEDICALHISTORY, SAMPLEREMARKS, STRAINCATEGORY,
				STRAINTYPE, STRAINSTORAGEDATE, SEROTYPE, STRAINMLST, STRAINPLG,
				OPERATOR, STRAINPARTER, STRAINREMARKS };
	}
}
