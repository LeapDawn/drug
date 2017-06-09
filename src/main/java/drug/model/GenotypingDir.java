package drug.model;

public class GenotypingDir {
    private Integer id;

    private String genotyping1;

    private String genotyping2;

    private String genotyping3;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenotyping1() {
        return genotyping1;
    }

    public void setGenotyping1(String genotyping1) {
        this.genotyping1 = genotyping1 == null ? null : genotyping1.trim();
    }

    public String getGenotyping2() {
        return genotyping2;
    }

    public void setGenotyping2(String genotyping2) {
        this.genotyping2 = genotyping2 == null ? null : genotyping2.trim();
    }

    public String getGenotyping3() {
        return genotyping3;
    }

    public void setGenotyping3(String genotyping3) {
        this.genotyping3 = genotyping3 == null ? null : genotyping3.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "GenotypingDir [id=" + id + ", genotyping1=" + genotyping1
				+ ", genotyping2=" + genotyping2 + ", genotyping3="
				+ genotyping3 + ", remark=" + remark + "]";
	}
}