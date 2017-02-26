package drug.dto.pageModel;

public class PStrainCharacter {
	private Integer id;

	private String strainno;

	private String genname;

	private String iseq; // 插入元件

	private String gentc; // 结合子

	private String replicon; // 复制子

	private String genalias;

	private String operator;

	private String genremarks;

	private String otherMsg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStrainno() {
		return strainno;
	}

	public void setStrainno(String strainno) {
		this.strainno = strainno;
	}

	public String getGenname() {
		return genname;
	}

	public void setGenname(String genname) {
		this.genname = genname;
	}

	public String getIseq() {
		return iseq;
	}

	public void setIseq(String iseq) {
		this.iseq = iseq;
	}

	public String getGentc() {
		return gentc;
	}

	public void setGentc(String gentc) {
		this.gentc = gentc;
	}

	public String getReplicon() {
		return replicon;
	}

	public void setReplicon(String replicon) {
		this.replicon = replicon;
	}

	public String getGenalias() {
		return genalias;
	}

	public void setGenalias(String genalias) {
		this.genalias = genalias;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getGenremarks() {
		return genremarks;
	}

	public void setGenremarks(String genremarks) {
		this.genremarks = genremarks;
	}

	public String getOtherMsg() {
		return otherMsg;
	}

	public void setOtherMsg(String otherMsg) {
		this.otherMsg = otherMsg;
	}

	@Override
	public String toString() {
		return "PStrainCharacter [id=" + id + ", strainno=" + strainno
				+ ", genname=" + genname + ", iseq=" + iseq + ", gentc="
				+ gentc + ", replicon=" + replicon + ", genalias=" + genalias
				+ ", operator=" + operator + ", genremarks=" + genremarks
				+ ", otherMsg=" + otherMsg + "]";
	}

}
