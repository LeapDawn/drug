package drug.dto.listModel;

public class LStrainCharacter {
	private String strainno;

	private String genname;

	private String iseq; // 插入元件

	private String gentc; // 结合子

	private String replicon; // 复制子

	private String genalias;

	private String operator;

	private int page;

	private int rows;

	private String order;

	private String sort;

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort != null && sort.toLowerCase().trim().equals("desc")?"desc":null;
	}
}
