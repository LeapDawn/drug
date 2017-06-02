package drug.model;

import java.util.Date;

public class StrainCharacter {
	private Integer id;

	private String strainno;

	private String genname;

	private String iseq; // 插入元件

	private String gentc; // 结合子

	private String replicon; // 复制子

	private String genalias;

	private String operator;

	private String adduser;

	private Date addtime;

	private String genremarks;

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
		this.strainno = strainno == null ? null : strainno.trim();
	}

	public String getGenname() {
		return genname;
	}

	public void setGenname(String genname) {
		this.genname = genname == null ? null : genname.trim();
	}

	public String getIseq() {
		return iseq;
	}

	public void setIseq(String iseq) {
		this.iseq = iseq == null ? null : iseq.trim();
	}

	public String getGentc() {
		return gentc;
	}

	public void setGentc(String gentc) {
		this.gentc = gentc == null ? null : gentc.trim();
	}

	public String getReplicon() {
		return replicon;
	}

	public void setReplicon(String replicon) {
		this.replicon = replicon == null ? null : replicon.trim();
	}

	public String getGenalias() {
		return genalias;
	}

	public void setGenalias(String genalias) {
		this.genalias = genalias == null ? null : genalias.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public String getAdduser() {
		return adduser;
	}

	public void setAdduser(String adduser) {
		this.adduser = adduser == null ? null : adduser.trim();
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getGenremarks() {
		return genremarks;
	}

	public void setGenremarks(String genremarks) {
		this.genremarks = genremarks == null ? null : genremarks.trim();
	}

	@Override
	public String toString() {
		return "StrainCharacter [id=" + id + ", strainno=" + strainno
				+ ", genname=" + genname + ", iseq=" + iseq + ", gentc="
				+ gentc + ", replicon=" + replicon + ", genalias=" + genalias
				+ ", operator=" + operator + ", adduser=" + adduser
				+ ", addtime=" + addtime + ", genremarks=" + genremarks + "]";
	}

}