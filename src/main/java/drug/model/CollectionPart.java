package drug.model;

public class CollectionPart {
    private String partno;

    private String partname;

    private String others1;

    private String others2;

    private String partremarks;

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno == null ? null : partno.trim();
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname == null ? null : partname.trim();
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

    public String getPartremarks() {
        return partremarks;
    }

    public void setPartremarks(String partremarks) {
        this.partremarks = partremarks == null ? null : partremarks.trim();
    }

	@Override
	public String toString() {
		return "CollectionPart [partno=" + partno + ", partname=" + partname
				+ ", others1=" + others1 + ", others2=" + others2
				+ ", partremarks=" + partremarks + "]";
	}
    
}