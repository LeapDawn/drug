package drug.commons.excelModel;

public class DrugViewExcel {
	public final static String STRAINNO = "菌株编号";
	public final static String ALIAS = "内部编号";
	public final static String SAMPLENO = "样品编号";
	public final static String SAMPLEDATE = "采样时间";
	public final static String SAMPLEPROVINCE = "省份";
	public final static String FARMNAME = "采样地名称";
	public final static String SAMPLEFARMADDR = "养殖场名称";
	public final static String ANIMALNAME = "动物";
	public final static String SAMPLEANIMALAGE = "日龄";
	public final static String SAMPLESOURCE = "来源";
	public final static String PARTNAME = "部位";
	public final static String SAMPLECOLLECTOR = "采样人员";
	public final static String SAMPLEMEDICALHISTORY = "动物用药史";
	public final static String SAMPLEREMARKS = "样品备注";
	public final static String STRAINCATEGORY = "菌属";
	public final static String STRAINTYPE = "菌种";
	public final static String STRAINSTORAGEDATE = "保存时间";
	public final static String SEROTYPE = "血清型";
	public final static String STRAINMLST = "MLST分型";
	public final static String STRAINPLG = "PLG分型";
	public final static String STRAINOPERATOR = "菌种鉴定人";
	public final static String STRAINPARTER = "细菌分型人";
	public final static String STRAINREMARKS = "菌株基本信息备注";
	public final static String AMK = "AMK";
	public final static String AMP = "AMP";
	public final static String APR = "APR";
	public final static String AZM = "AZM";
	public final static String CFZ = "CFZ";
	public final static String FEP = "FEP";
	public final static String CTX = "CTX";
	public final static String FOX = "FOX";
	public final static String CAZ = "CAZ";
	public final static String CRO = "CRO";
	public final static String CQM = "CQM";
	public final static String CHL = "CHL";
	public final static String CIP = "CIP";
	public final static String TIA = "TIA";
	public final static String CLI = "CLI";
	public final static String CL = "CL";
	public final static String DOX = "DOX";
	public final static String VAL = "VAL";
	public final static String ERY = "ERY";
	public final static String FFC = "FFC";
	public final static String FOS = "FOS";
	public final static String GEN = "GEN";
	public final static String IMP = "IMP";
	public final static String LEV = "LEV";
	public final static String LZD = "LZD";
	public final static String MEM = "MEM";
	public final static String MIN = "MIN";
	public final static String NAL = "NAL";
	public final static String NEO = "NEO";
	public final static String NET = "NET";
	public final static String NIT = "NIT";
	public final static String NOR = "NOR";
	public final static String OFX = "OFX";
	public final static String OXA = "OXA";
	public final static String PEN = "PEN";
	public final static String PIP = "PIP";
	public final static String TZP = "TZP";
	public final static String RIF = "RIF";
	public final static String STR = "STR";
	public final static String TEC = "TEC";
	public final static String TET = "TET";
	public final static String TOB = "TOB";
	public final static String TMP = "TMP";
	public final static String SXT = "SXT";
	public final static String VAN = "VAN";
	public final static String OQX = "OQX";
	public final static String MICOPERATOR = "实验人员";
	public final static String MICDETECTIONTYPE = "实验方式";
	public final static String MICREMARK = "MIC备注";
	public final static String GENNAME = "耐药基因";
	public final static String ISEQ = "插入元件";
	public final static String REPLICON = "复制子";
	public final static String GENTC = "接合子";
	public final static String GENOPERATOR = "操作人";

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
