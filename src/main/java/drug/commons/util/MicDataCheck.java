package drug.commons.util;

import java.lang.reflect.Field;

import drug.commons.exception.DataViolationException;
import drug.dto.pageModel.PStrainMic;
import drug.model.StrainMic;

/**
 * 检测mic浓度合法性
 */
public class MicDataCheck {
	public static void checkAllDate(StrainMic sMic) {
		Class<?> cla = sMic.getClass();
		Field[] fs = cla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true);
			Object val;
			try {
				val = f.get(sMic);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataViolationException("检测药物浓度数值失败,请重试");
			}
			if (f.getType().equals(Double.class) && val != null) {
				if (!checkData((Double) val)) {
					throw new DataViolationException(f.getName()
							+ "数值不符合浓度数值格式");
				}
			}
		}
	}

	public static void checkAllDate(PStrainMic pMic) {
		Class<?> cla = pMic.getClass();
		Field[] fs = cla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true);
			Object val;
			try {
				val = f.get(pMic);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataViolationException("检测药物浓度数值失败,请重试");
			}
			if (f.getType().equals(Double.class) && val != null) {
				if (!checkData((Double) val)) {
					throw new DataViolationException(f.getName()
							+ "数值不符合浓度数值格式");
				}
			}
		}
	}

	public static boolean checkData(Double f) {
		if (0.03 == f || 0.06 == f || 1 == f || 0.12 == f || 0.015 == f
				|| 0.008 == f || 0.032 == f || 0.128 == f || 0.004 == f
				|| 0.15 == f || 0.12 == f || 0.24 == f || 0.002 == f
				|| 0.125 == f || 0.25 == f || 0.5 == f)
			return true;
		if (f == 0)
			return true;
		if (f == 2)
			return true;
		boolean flag = false;
		if (f > 1) {
			do {
				double k = f % 2;
				f = f / 2;
				if (f == 2 && k == 0)
					flag = true;
			} while (f > 2);
		} else {
			flag = false;
		}
		return flag;
	}
}
