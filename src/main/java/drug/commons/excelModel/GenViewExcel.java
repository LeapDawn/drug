package drug.commons.excelModel;

public class GenViewExcel {

	public final static String STRAINNO = "菌株编号";
	public final static String ALIAS = "内部编号";
	public final static String SAMPLENO = "样品编号";
	public final static String GENNAME = "基因名称";
	public final static String GENOTYPING1 = "亚型1";
	public final static String GENOTYPING2 = "亚型2";
	public final static String GENOTYPING3 = "亚型3";
	public final static String SAMPLEDATE = "采样时间";
	public final static String SAMPLEPROVINCE = "省份";
	public final static String FARMNAME = "采样地名称";
	public final static String ANIMALNAME = "动物";
	public final static String SAMPLESOURCE = "来源";
	public final static String REMARKS = "备注";

	public static String[] getExportColumns() {
		return new String[] { STRAINNO, ALIAS, SAMPLENO, GENNAME, GENOTYPING1,
				GENOTYPING2, GENOTYPING3, SAMPLEDATE, SAMPLEPROVINCE, FARMNAME,
				ANIMALNAME, SAMPLESOURCE, REMARKS };
	}
}
