package drug.commons.excelModel;

public class CharacterExcel {

	public final static String STRAINNO = "菌株编号";
	public final static String GENALIAS = "内部编号";
	public final static String GENNAME = "耐药基因";
	public final static String ISEQ = "插入元件";
	public final static String REPLICON = "复制子";
	public final static String GENTC = "接合子";
	public final static String OPERATOR = "操作人";
	public final static String REMARK = "备注";

	public static String[] getImportColumns() {
		return new String[] { STRAINNO, GENALIAS, GENNAME, ISEQ, REPLICON,
				GENTC, REMARK };
	}

	// 数组元素的顺序=execl文件中表头的顺序
	public static String[] getExportColumns() {
		return new String[] { STRAINNO, GENNAME, GENALIAS, ISEQ, REPLICON,
				GENTC, OPERATOR, REMARK };
	}
}
