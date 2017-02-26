package drug.model;

public class Source {
    private Integer sourceno;

    private String remark;

    private String sourcename;

    private String other1;

    private String other2;

    public Integer getSourceno() {
        return sourceno;
    }

    public void setSourceno(Integer sourceno) {
        this.sourceno = sourceno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename == null ? null : sourcename.trim();
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1 == null ? null : other1.trim();
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2 == null ? null : other2.trim();
    }

	@Override
	public String toString() {
		return "Source [sourceno=" + sourceno + ", remark=" + remark
				+ ", sourcename=" + sourcename + ", other1=" + other1
				+ ", other2=" + other2 + "]";
	}
}