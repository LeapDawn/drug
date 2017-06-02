package drug.commons.execlModel;

public class FarmExecl {

	public static String FARMNAME = "采样地名称";
	public static String PROVINCE = "省份";
	public static String ADDRESS = "详细地址";
	public static String LINKMAN = "联系人";
	public static String PHONE = "联系电话";
	public static String RAISEWAY = "饲养方式";
	public static String RAISESCOPE = "养殖规模";
	public static String RAISETYPE = "养殖模式";
	public static String BUILEDATE = "建厂时间";
	public static String REMARK = "备注";

	public static String[] getImportColumns() {
		return new String[] { FARMNAME, PROVINCE, ADDRESS, LINKMAN, PHONE,
				RAISEWAY, RAISESCOPE, RAISETYPE, BUILEDATE, REMARK };
	}

	public static String[] getExportColumns() {
		return new String[] { FARMNAME, PROVINCE, ADDRESS, LINKMAN, PHONE,
				RAISEWAY, RAISESCOPE, RAISETYPE, BUILEDATE, REMARK };
	}
}
