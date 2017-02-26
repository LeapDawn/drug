package drug.model;

import java.util.Date;

public class StrainCoding {
    private String strainno;

    private String sampleno;

    private String straincategory;

    private String straintype;

    private String strainalias;

    private Date strainstoragedate;

    private String operator;

    private String serotype;

    private String strainmlst;

    private String strainplg;

    private String strainparter;

    private String strainremarks;

    private String gramstain;

    private Date addtime;

    private String adduser;
    
    private String farmName;

	private String samplefarmaddr;

	private String sampleanimalage;

	private String samplesource;

	private String partName;

	private String samplecollector;

	private String samplemedicalhistory;
	
    public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getSamplefarmaddr() {
		return samplefarmaddr;
	}

	public void setSamplefarmaddr(String samplefarmaddr) {
		this.samplefarmaddr = samplefarmaddr;
	}

	public String getSampleanimalage() {
		return sampleanimalage;
	}

	public void setSampleanimalage(String sampleanimalage) {
		this.sampleanimalage = sampleanimalage;
	}

	public String getSamplesource() {
		return samplesource;
	}

	public void setSamplesource(String source) {
		this.samplesource = source;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String part) {
		this.partName = part;
	}

	public String getSamplecollector() {
		return samplecollector;
	}

	public void setSamplecollector(String samplecollector) {
		this.samplecollector = samplecollector;
	}

	public String getSamplemedicalhistory() {
		return samplemedicalhistory;
	}

	public void setSamplemedicalhistory(String samplemedicalhistory) {
		this.samplemedicalhistory = samplemedicalhistory;
	}

	public String getStrainno() {
        return strainno;
    }

    public void setStrainno(String strainno) {
        this.strainno = strainno == null ? null : strainno.trim();
    }

    public String getSampleno() {
        return sampleno;
    }

    public void setSampleno(String sampleno) {
        this.sampleno = sampleno == null ? null : sampleno.trim();
    }

    public String getStraincategory() {
        return straincategory;
    }

    public void setStraincategory(String straincategory) {
        this.straincategory = straincategory == null ? null : straincategory.trim();
    }

    public String getStraintype() {
        return straintype;
    }

    public void setStraintype(String straintype) {
        this.straintype = straintype == null ? null : straintype.trim();
    }

    public String getStrainalias() {
        return strainalias;
    }

    public void setStrainalias(String strainalias) {
        this.strainalias = strainalias == null ? null : strainalias.trim();
    }

    public Date getStrainstoragedate() {
        return strainstoragedate;
    }

    public void setStrainstoragedate(Date strainstoragedate) {
        this.strainstoragedate = strainstoragedate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getSerotype() {
        return serotype;
    }

    public void setSerotype(String serotype) {
        this.serotype = serotype == null ? null : serotype.trim();
    }

    public String getStrainmlst() {
        return strainmlst;
    }

    public void setStrainmlst(String strainmlst) {
        this.strainmlst = strainmlst == null ? null : strainmlst.trim();
    }

    public String getStrainplg() {
        return strainplg;
    }

    public void setStrainplg(String strainplg) {
        this.strainplg = strainplg == null ? null : strainplg.trim();
    }

    public String getStrainparter() {
        return strainparter;
    }

    public void setStrainparter(String strainparter) {
        this.strainparter = strainparter == null ? null : strainparter.trim();
    }

    public String getStrainremarks() {
        return strainremarks;
    }

    public void setStrainremarks(String strainremarks) {
        this.strainremarks = strainremarks == null ? null : strainremarks.trim();
    }

    public String getGramstain() {
        return gramstain;
    }

    public void setGramstain(String gramstain) {
        this.gramstain = gramstain == null ? null : gramstain.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }
}