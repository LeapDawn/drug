package drug.commons.excelModel;

public class MicExcel {
	public final static String STRAINNO = "菌株编号";
	public final static String MICALIAS = "内部编号";
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
	public final static String OPERATOR = "实验人员";
	public final static String MICDETECTIONTYPE = "实验方式";
	public final static String REMARK = "备注";

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

	public static String[] getNeededUpdateColumns() {
		return new String[] { STRAINNO, MICALIAS};
	}
	
	public static String[] getNegativeUpdateColumns() {
		return new String[] { AMK, AMP, APR, CAZ, CL, CHL, CIP, CTX, CQM, DOX,
				FOX, FFC, FOS, GEN, IMP, OQX, NEO, SXT, STR, TET, MEM,
				OPERATOR, MICDETECTIONTYPE, REMARK };
	}

	public static String[] getPositiveUpdateColumns() {
		return new String[] { AMP, CIP, CLI, CQM, ERY, FFC, FOX, GEN, LZD, OXA,
				PEN, RIF, TET, TIA, VAL, VAN, OPERATOR, MICDETECTIONTYPE,
				REMARK };
	}
	
	public static String[] getDrugColumns() {
		return new String[] { AMK, AMP, APR, AZM, CFZ, FEP, CTX, FOX, CAZ, CRO,
				CQM, CHL, CIP, TIA, CLI, CL, DOX, VAL, ERY, FFC, FOS, GEN, IMP,
				LEV, LZD, MEM, MIN, NAL, NEO, NET, NIT, NOR, OFX, OXA, PEN,
				PIP, TZP, RIF, STR, TEC, TET, TOB, TMP, SXT, VAN, OQX};
	}
}
