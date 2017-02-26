package drug.model;

import java.util.Date;

public class DrugViewAll {
    private String id;

    private String sampleno;

    private String strainno;

    private String strainalias;

    private Date sampledate;

    private String sampleprovince;

    private String farmname;

    private String samplefarmaddr;

    private Integer animalno;

    private String animalname;

    private String samplecollectionpartno;

    private String samplecollectionpart;

    private String sampleanimalage;

    private String samplesource;

    private String samplecollector;

    private String samplemedicalhistory;

    private String sampleremarks;

    private String straincategory;

    private String straintype;

    private Date strainstoragedate;

    private String serotype;

    private String strainmlst;

    private String strainplg;

    private String strainoperator;

    private String strainparter;

    private String strainremarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSampleno() {
        return sampleno;
    }

    public void setSampleno(String sampleno) {
        this.sampleno = sampleno == null ? null : sampleno.trim();
    }

    public String getStrainno() {
        return strainno;
    }

    public void setStrainno(String strainno) {
        this.strainno = strainno == null ? null : strainno.trim();
    }

    public String getStrainalias() {
        return strainalias;
    }

    public void setStrainalias(String strainalias) {
        this.strainalias = strainalias == null ? null : strainalias.trim();
    }

    public Date getSampledate() {
        return sampledate;
    }

    public void setSampledate(Date sampledate) {
        this.sampledate = sampledate;
    }

    public String getSampleprovince() {
        return sampleprovince;
    }

    public void setSampleprovince(String sampleprovince) {
        this.sampleprovince = sampleprovince == null ? null : sampleprovince.trim();
    }

    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname == null ? null : farmname.trim();
    }

    public String getSamplefarmaddr() {
        return samplefarmaddr;
    }

    public void setSamplefarmaddr(String samplefarmaddr) {
        this.samplefarmaddr = samplefarmaddr == null ? null : samplefarmaddr.trim();
    }

    public Integer getAnimalno() {
        return animalno;
    }

    public void setAnimalno(Integer animalno) {
        this.animalno = animalno;
    }

    public String getAnimalname() {
        return animalname;
    }

    public void setAnimalname(String animalname) {
        this.animalname = animalname == null ? null : animalname.trim();
    }

    public String getSamplecollectionpartno() {
        return samplecollectionpartno;
    }

    public void setSamplecollectionpartno(String samplecollectionpartno) {
        this.samplecollectionpartno = samplecollectionpartno == null ? null : samplecollectionpartno.trim();
    }

    public String getSamplecollectionpart() {
        return samplecollectionpart;
    }

    public void setSamplecollectionpart(String samplecollectionpart) {
        this.samplecollectionpart = samplecollectionpart == null ? null : samplecollectionpart.trim();
    }

    public String getSampleanimalage() {
        return sampleanimalage;
    }

    public void setSampleanimalage(String sampleanimalage) {
        this.sampleanimalage = sampleanimalage == null ? null : sampleanimalage.trim();
    }

    public String getSamplesource() {
        return samplesource;
    }

    public void setSamplesource(String samplesource) {
        this.samplesource = samplesource == null ? null : samplesource.trim();
    }

    public String getSamplecollector() {
        return samplecollector;
    }

    public void setSamplecollector(String samplecollector) {
        this.samplecollector = samplecollector == null ? null : samplecollector.trim();
    }

    public String getSamplemedicalhistory() {
        return samplemedicalhistory;
    }

    public void setSamplemedicalhistory(String samplemedicalhistory) {
        this.samplemedicalhistory = samplemedicalhistory == null ? null : samplemedicalhistory.trim();
    }

    public String getSampleremarks() {
        return sampleremarks;
    }

    public void setSampleremarks(String sampleremarks) {
        this.sampleremarks = sampleremarks == null ? null : sampleremarks.trim();
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

    public Date getStrainstoragedate() {
        return strainstoragedate;
    }

    public void setStrainstoragedate(Date strainstoragedate) {
        this.strainstoragedate = strainstoragedate;
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

    public String getStrainoperator() {
        return strainoperator;
    }

    public void setStrainoperator(String strainoperator) {
        this.strainoperator = strainoperator == null ? null : strainoperator.trim();
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
}