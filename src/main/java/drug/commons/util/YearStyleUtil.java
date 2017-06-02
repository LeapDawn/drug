package drug.commons.util;

/**
 * 时间字符串生成工具
 */
public class YearStyleUtil {
	// 生成时间String
	public static String getDate(String unknownDate, String number) {
		if (number == null || number.equals("")) {
			number = "4";
		}
		if (number.equals("1") || number.equals("5") || number.equals("2")) {
			return unknownDate;
		} else {
			String iString = unknownDate.substring(5);
			if (number.equals("3")) {
				if (iString.equals("0")) {
					return unknownDate.substring(0, unknownDate.length() - 1)
							+ "上半年";
				} else if (iString.equals("1")) {
					return unknownDate.substring(0, unknownDate.length() - 1)
							+ "下半年";
				} else {
					return "Error1";
				}
			} else if (number.equals("4")) {
				if (iString.equals("0")) {
					return unknownDate.substring(0, unknownDate.length() - 1)
							+ "第一季度";
				} else if (iString.equals("1")) {
					return unknownDate.substring(0, unknownDate.length() - 1)
							+ "第二季度";
				} else if (iString.equals("2")) {
					return unknownDate.substring(0, unknownDate.length() - 1)
							+ "第三季度";
				} else if (iString.equals("3")) {
					return unknownDate.substring(0, unknownDate.length() - 1)
							+ "第四季度";
				} else {
					return "Error2";
				}
			} else {
				return "Error3";
			}
		}
	}
}
