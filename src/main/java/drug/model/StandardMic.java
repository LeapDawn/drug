package drug.model;

public class StandardMic extends StandardMicKey {
    private Float medlimit;

    private String others1;

    private String others2;

    private String remark;

    public Float getMedlimit() {
        return medlimit;
    }

    public void setMedlimit(Float medlimit) {
        this.medlimit = medlimit;
    }

    public String getOthers1() {
        return others1;
    }

    public void setOthers1(String others1) {
        this.others1 = others1 == null ? null : others1.trim();
    }

    public String getOthers2() {
        return others2;
    }

    public void setOthers2(String others2) {
        this.others2 = others2 == null ? null : others2.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "StandardMic [medlimit=" + medlimit + ", others1=" + others1
				+ ", others2=" + others2 + ", remark=" + remark + "]";
	}
    
    
}