package drug.commons.excelModel;

public class DrugViewExcel {
	public static String STRAINNO = "菌株编号";
	public static String ALIAS = "内部编号";
	public static String SAMPLENO = "样品编号";
	public static String SAMPLEDATE = "采样时间";
	public static String SAMPLEPROVINCE = "省份";
	public static String FARMNAME = "采样地名称";
	public static String SAMPLEFARMADDR = "养殖场名称";
	public static String ANIMALNAME = "动物";
	public static String SAMPLEANIMALAGE = "日龄";
	public static String SAMPLESOURCE = "来源";
	public static String PARTNAME = "部位";
	public static String SAMPLECOLLECTOR = "采样人员";
	public static String SAMPLEMEDICALHISTORY = "动物用药史";
	public static String SAMPLEREMARKS = "样品备注";
	public static String STRAINCATEGORY = "菌属";
	public static String STRAINTYPE = "菌种";
	public static String STRAINSTORAGEDATE = "保存时间";
	public static String SEROTYPE = "血清型";
	public static String STRAINMLST = "MLST分型";
	public static String STRAINPLG = "PLG分型";
	public static String STRAINOPERATOR = "菌种鉴定人";
	public static String STRAINPARTER = "细菌分型人";
	public static String STRAINREMARKS = "菌株基本信息备注";
	public static String AMK = "AMK";
	public static String AMP = "AMP";
	public static String APR = "APR";
	public static String AZM = "AZM";
	public static String CFZ = "CFZ";
	public static String FEP = "FEP";
	public static String CTX = "CTX";
	public static String FOX = "FOX";
	public static String CAZ = "CAZ";
	public static String CRO = "CRO";
	public static String CQM = "CQM";
	public static String CHL = "CHL";
	public static String CIP = "CIP";
	public static String TIA = "TIA";
	public static String CLI = "CLI";
	public static String CL = "CL";
	public static String DOX = "DOX";
	public static String VAL = "VAL";
	public static String ERY = "ERY";
	public static String FFC = "FFC";
	public static String FOS = "FOS";
	public static String GEN = "GEN";
	public static String IMP = "IMP";
	public static String LEV = "LEV";
	public static String LZD = "LZD";
	public static String MEM = "MEM";
	public static String MIN = "MIN";
	public static String NAL = "NAL";
	public static String NEO = "NEO";
	public static String NET = "NET";
	public static String NIT = "NIT";
	public static String NOR = "NOR";
	public static String OFX = "OFX";
	public static String OXA = "OXA";
	public static String PEN = "PEN";
	public static String PIP = "PIP";
	public static String TZP = "TZP";
	public static String RIF = "RIF";
	public static String STR = "STR";
	public static String TEC = "TEC";
	public static String TET = "TET";
	public static String TOB = "TOB";
	public static String TMP = "TMP";
	public static String SXT = "SXT";
	public static String VAN = "VAN";
	public static String OQX = "OQX";
	public static String MICOPERATOR = "实验人员";
	public static String MICDETECTIONTYPE = "实验方式";
	public static String MICREMARK = "MIC备注";
	public static String GENNAME = "耐药基因";
	public static String ISEQ = "插入元件";
	public static String REPLICON = "复制子";
	public static String GENTC = "接合子";
	public static String GENOPERATOR = "操作人";

	public static String[] getExportColumns() {
		return new String[] { STRAINNO, ALIAS, SAMPLENO, SAMPLEDATE,
				SAMPLEPROVINCE, FARMNAME, SAMPLEFARMADDR, ANIMALNAME,
				SAMPLEANIMALAGE, SAMPLESOURCE, PARTNAME, SAMPLECOLLECTOR,
				SAMPLEMEDICALHISTORY, SAMPLEREMARKS, STRAINCATEGORY,
				STRAINTYPE, STRAINSTORAGEDATE, SEROTYPE, STRAINMLST, STRAINPLG,
				STRAINOPERATOR, STRAINPARTER, STRAINREMARKS, AMP, OXA, PEN,
				PIP, TZP, CAZ, CFZ, CQM, CRO, CTX, FEP, FOX, AMK, APR, GEN,
				NEO, NET, STR, TOB, DOX, MIN, TET, CHL, FFC, AZM, ERY, RIF,
				TEC, VAN, CLI, CL, IMP, TIA, VAL, FOS, NIT, SXT, TMP, CIP, LEV,
				NAL, NOR, OFX, LZD, OQX, MEM, MICOPERATOR, MICDETECTIONTYPE,
				MICREMARK, GENNAME, ISEQ, REPLICON, GENTC, GENOPERATOR };
	}

	public static String[] getPositiveExportColumns() {
		return new String[] { STRAINNO, ALIAS, SAMPLENO, SAMPLEDATE,
				SAMPLEPROVINCE, FARMNAME, SAMPLEFARMADDR, ANIMALNAME,
				SAMPLEANIMALAGE, SAMPLESOURCE, PARTNAME, SAMPLECOLLECTOR,
				SAMPLEMEDICALHISTORY, SAMPLEREMARKS, STRAINCATEGORY,
				STRAINTYPE, STRAINSTORAGEDATE, SEROTYPE, STRAINMLST, STRAINPLG,
				STRAINOPERATOR, STRAINPARTER, STRAINREMARKS, AMP, CIP, CLI,
				CQM, ERY, FFC, FOX, GEN, LZD, OXA, PEN, RIF, TET, TIA, VAL,
				VAN, MICOPERATOR, MICDETECTIONTYPE, MICREMARK, GENNAME, ISEQ,
				REPLICON, GENTC, GENOPERATOR };
	}

	public static String[] getNegativeExportColumns() {
		return new String[] { STRAINNO, ALIAS, SAMPLENO, SAMPLEDATE,
				SAMPLEPROVINCE, FARMNAME, SAMPLEFARMADDR, ANIMALNAME,
				SAMPLEANIMALAGE, SAMPLESOURCE, PARTNAME, SAMPLECOLLECTOR,
				SAMPLEMEDICALHISTORY, SAMPLEREMARKS, STRAINCATEGORY,
				STRAINTYPE, STRAINSTORAGEDATE, SEROTYPE, STRAINMLST, STRAINPLG,
				STRAINOPERATOR, STRAINPARTER, STRAINREMARKS, AMK, AMP, APR,
				CAZ, CL, CHL, CIP, CTX, CQM, DOX, FOX, FFC, FOS, GEN, IMP, OQX,
				NEO, SXT, STR, TET, MEM, MICOPERATOR, MICDETECTIONTYPE,
				MICREMARK, GENNAME, ISEQ, REPLICON, GENTC, GENOPERATOR };
	}
}
