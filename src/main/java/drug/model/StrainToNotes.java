package drug.model;

public class StrainToNotes {
    private Integer id;

    private String strainname;

    private String straincategory;

    private String strainnotes;

    private String strainremark;

    private String others2;

    private String others1;

    private String gramstain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrainname() {
        return strainname;
    }

    public void setStrainname(String strainname) {
        this.strainname = strainname == null ? null : strainname.trim();
    }

    public String getStraincategory() {
        return straincategory;
    }

    public void setStraincategory(String straincategory) {
        this.straincategory = straincategory == null ? null : straincategory.trim();
    }

    public String getStrainnotes() {
        return strainnotes;
    }

    public void setStrainnotes(String strainnotes) {
        this.strainnotes = strainnotes == null ? null : strainnotes.trim();
    }

    public String getStrainremark() {
        return strainremark;
    }

    public void setStrainremark(String strainremark) {
        this.strainremark = strainremark == null ? null : strainremark.trim();
    }

    public String getOthers2() {
        return others2;
    }

    public void setOthers2(String others2) {
        this.others2 = others2 == null ? null : others2.trim();
    }

    public String getOthers1() {
        return others1;
    }

    public void setOthers1(String others1) {
        this.others1 = others1 == null ? null : others1.trim();
    }

    public String getGramstain() {
        return gramstain;
    }

    public void setGramstain(String gramstain) {
        this.gramstain = gramstain == null ? null : gramstain.trim();
    }
}