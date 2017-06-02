package drug.dto.pageModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Province {
	final static String province[] = { "北京:10", "天津:30", "河北:05", "内蒙古:01", "山西:03", "上海:20", "安徽:23", "江苏:21", "浙江:31", "山东:25", "江西:33", "福建:35", "广东:51", "广西:53", "海南:57", "河南:45", "湖北:43", "湖南:41", "黑龙江:15", "吉林:13", "辽宁:11", "陕西:71", "甘肃:73", "宁夏:75", "青海:81", "新疆:83", "重庆:40", "四川:61", "云南:65", "贵州:55", "西藏:85", "香港:99", "澳门:98", "台湾:97" };

	public static String getProvinceNo(String provice) {
		if (provice == null || "".equals(provice)) {
			return null;
		}
		for (int i = 0; i < province.length; i++) {
			if (province[i].split(":")[0].equals(provice)) {
				return province[i].split(":")[1];
			}
		}
		return null;
	}

	public static List<Map<String, Object>> getAllProvince() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < province.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("label", province[i].split(":")[0]);
			map.put("value", province[i].split(":")[0]);
			list.add(map);
		}
		return list;
	}
}
