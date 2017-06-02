package drug;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import drug.commons.excelModel.StrainExcel;
import drug.commons.exception.ExcelException;
import drug.commons.util.ExeclUtil;
import drug.commons.util.StringUtil;

public class TestClass {

	@Test
	public void test() throws ClassNotFoundException {
		Class<?> clazz = Class.forName("drug.model.StrainMic");
		String prefix = "public static String ";
		String sufix = " = \"\";";
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if (!name.toLowerCase().equals("adduser")
					&& !name.toLowerCase().equals("addtime")) {
				// System.out.println(prefix + name.toUpperCase() + sufix);
				System.out.println(prefix + name.toUpperCase() + "= \""
						+ name.toUpperCase() + "\" ;");
			}
		}
	}

	@Test
	public void test1() {
		// String str =
		// "AMP,CIP,CLI,CQM,ERY,FFC,FOX,GEN,LZD,OXA,PEN,RIF,TET,TIA,VAL,VAN";
		String str = "AMK,AMP,APR,CAZ,CL,CHL,CIP,CTX,CQM,DOX,FOX,FFC,FOS,GEN,IMP,OQX,NEO,SXT,STR,TET,MEM";
		String[] strings = str.split(",");
		// mic.setStrainno(String.valueOf(map.get(StrainExecl.SAMPLENO)));
		for (String s : strings) {
			String s2 = s.substring(0, 1) + s.substring(1).toLowerCase();
			System.out.println("mic.set" + s2
					+ "(Double.valueOf(String.valueOf(map.get(MicExecl." + s
					+ "))));");
		}
	}

	@Test
	public void test2() {
		// String str =
		// "AMP,CIP,CLI,CQM,ERY,FFC,FOX,GEN,LZD,OXA,PEN,RIF,TET,TIA,VAL,VAN";
		// String str =
		// "AMK,AMP,APR,CAZ,CL,CHL,CIP,CTX,CQM,DOX,FOX,FFC,FOS,GEN,IMP,OQX,NEO,SXT,STR,TET,MEM";
		String str = " AMP, OXA, PEN,PIP, TZP, CAZ, CFZ, CQM, CRO, CTX, "
				+ "FEP, FOX, AMK, APR, GEN,NEO, NET, STR, TOB, DOX,"
				+ " MIN, TET, CHL, FFC, AZM, ERY, RIF,TEC, VAN, CLI, "
				+ "CL, IMP, TIA, VAL, FOS, NIT, SXT, TMP, CIP, LEV,"
				+ "NAL, NOR, OFX, LZD, OQX, MEM";
		String[] strings = str.split(",");
		for (String s : strings) {
			s = s.trim();
			String s2 = s.substring(0, 1) + s.substring(1).toLowerCase();
			// System.out.println("map.put(MicExecl."+ s+
			// ",String.valueOf(pmic.get"+ s2 + "()));");
			System.out.println("map.put(DrugViewExecl." + s
					+ ",String.valueOf(pdv.get" + s2 + "()));");
		}
	}
	
	@Test
	public void test3() {
		Integer i = null;
		int a = i;
		System.out.println(a);
	}
}
