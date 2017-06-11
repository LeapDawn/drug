package drug;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import drug.commons.excelModel.MicExcel;
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
		ExeclUtil util = new ExeclUtil();
		util.setModelArray(new String[]{"样品编号","动物","部位","动物用药史"});
		String[] files = new String[]{"e://广东江门菌株信息.xls","e://广东肇庆菌株信息.xls","e://广州惠州惠东菌株信息.xls",
				"e://上海浦东菌株信息.xls","e://韶关温氏菌株信息.xls","e://上海奉贤菌株信息.xls"};
		try {
			File file = new File("e://sql");
			FileWriter writer = new FileWriter(file, true);
			for (String string : files) {
				util.readExecl(new FileInputStream(string));
				List<Map<String, Object>> bodyList = util.getBodyList();
				String str="";
				for (Map<String, Object> map : bodyList) {
					str = "update sample set animalNo="
							+ "(select animalNo from detailanimal WHERE animalName like '"+map.get("动物")+"'),"
							+ "sampleCollectionPart=(select partNo from collectionpart where partName like '"+map.get("部位")+"'),"
							+ "sampleMedicalHistory='" + map.get("动物用药史") + "' "
							+ " where sampleNo = '" + map.get("样品编号") + "';";
					System.out.println(str);
					writer.write(str + "\n");
				}
				writer.flush();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test4() {
		Class<?> clazz =  MicExcel.class;
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			String name = field.getName();
			System.out.println("if (map.get(MicExcel."+ name + ")!=null){");
			System.out.println("	resultmap.put(\"" + name + "\", map.get(MicExcel."+ name + "));");
			System.out.println("}");
		}
	}
	
	@Test
	public void test5() {
		ExeclUtil util = new ExeclUtil();
		try {
			util.setModelArray(MicExcel.getNeededUpdateColumns());
			util.readExecl(new FileInputStream("e://mic1.xls"));
			String[] headArray = util.getHeadArray();
			for (String string : headArray) {
				System.out.println(string);
			}
			List<Map<String, Object>> bodyList = util.getBodyList();
			System.out.println(headArray);
			for (Map<String, Object> map : bodyList) {
				System.out.println(map);
			}
			System.out.println(headArray.length);
			for (Map<String, Object> map : bodyList) {
				System.out.println(map.size());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ExcelException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test6() {
		ExeclUtil util = new ExeclUtil();
		util.setModelArray(new String[]{"菌株编号","AMK"});
		String[] files = new String[]{"e://Negative_mic.xls"};
		try {
			File file = new File("e://sql");
			FileWriter writer = new FileWriter(file, true);
			for (String string : files) {
				util.readExecl(new FileInputStream(string));
				List<Map<String, Object>> bodyList = util.getBodyList();
				String str="";
				for (Map<String, Object> map : bodyList) {
					str = "update strainMic set AMK=" + map.get("AMK") 
							+ " where strainNo = '" + map.get("菌株编号") + "';";
					System.out.println(str);
					writer.write(str + "\n");
				}
				writer.flush();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
