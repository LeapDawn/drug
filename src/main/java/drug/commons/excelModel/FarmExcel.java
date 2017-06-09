package drug.commons.excelModel;

public class FarmExcel {

	public final static String FARMNAME = "采样地名称";
	public final static String FRAMPROVINCE = "省份";
	public final static String FARMADDRESS = "详细地址";
	public final static String FARMLINKMAN = "联系人";
	public final static String FARMPHONE = "联系电话";
	public final static String FARMRAISEWAY = "饲养方式";
	public final static String FARMRAISESCOPE = "养殖规模";
	public final static String FARMRAISETYPE = "养殖模式";
	public final static String FARMBUILEDATE = "建厂时间";
	public final static String FARMREMARK = "备注";

	public static String[] getImportColumns() {
		return new String[] { FARMNAME, FRAMPROVINCE, FARMADDRESS, FARMLINKMAN, FARMPHONE,
				FARMRAISEWAY, FARMRAISESCOPE, FARMRAISETYPE, FARMBUILEDATE, FARMREMARK };
	}

	// 数组元素的顺序=execl文件中表头的顺序
	public static String[] getExportColumns() {
		return new String[] { FARMNAME, FRAMPROVINCE, FARMADDRESS, FARMLINKMAN, FARMPHONE,
				FARMRAISEWAY, FARMRAISESCOPE, FARMRAISETYPE, FARMBUILEDATE, FARMREMARK };
	}
}
