package drug.commons.execlModel;

public class MicExecl {
	public static String STRAINNO = "菌株编号";
	public static String MICALIAS = "内部编号";
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
	public static String OPERATOR = "实验人员";
	public static String MICDETECTIONTYPE = "实验方式";
	public static String REMARK = "备注";

	public static String[] getNegativeImportColumns() {
		return new String[] { STRAINNO, MICALIAS, AMK, AMP, APR, CAZ, CL, CHL,
				CIP, CTX, CQM, DOX, FOX, FFC, FOS, GEN, IMP, OQX, NEO, SXT,
				STR, TET, MEM, OPERATOR, MICDETECTIONTYPE, REMARK };
	}

	public static String[] getPositiveImportColumns() {
		return new String[] { STRAINNO, MICALIAS, AMP, CIP, CLI, CQM, ERY, FFC,
				FOX, GEN, LZD, OXA, PEN, RIF, TET, TIA, VAL, VAN, OPERATOR,
				MICDETECTIONTYPE, REMARK };
	}

	public static String[] getNegativeExportColumns() {
		return new String[] { STRAINNO, MICALIAS, AMK, AMP, APR, CAZ, CL, CHL,
				CIP, CTX, CQM, DOX, FOX, FFC, FOS, GEN, IMP, OQX, NEO, SXT,
				STR, TET, MEM, REMARK };
	}

	public static String[] getPositiveExportColumns() {
		return new String[] { STRAINNO, MICALIAS, AMP, CIP, CLI, CQM, ERY, FFC,
				FOX, GEN, LZD, OXA, PEN, RIF, TET, TIA, VAL, VAN, REMARK };
	}

}
