package drug.model;

public class Medice {
    private String medice;

    private String name;

    private String remark;

    public String getMedice() {
        return medice;
    }

    public void setMedice(String medice) {
        this.medice = medice == null ? null : medice.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}