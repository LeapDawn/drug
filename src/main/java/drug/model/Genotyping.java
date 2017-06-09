package drug.model;

public class Genotyping {
    private Integer id;

    private String genname;

    private String genotyping;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenname() {
        return genname;
    }

    public void setGenname(String genname) {
        this.genname = genname == null ? null : genname.trim();
    }

    public String getGenotyping() {
        return genotyping;
    }

    public void setGenotyping(String genotyping) {
        this.genotyping = genotyping == null ? null : genotyping.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "Genotyping [id=" + id + ", genname=" + genname
				+ ", genotyping=" + genotyping + ", remark=" + remark + "]";
	}
    
    
}