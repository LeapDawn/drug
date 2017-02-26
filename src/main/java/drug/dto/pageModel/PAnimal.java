package drug.dto.pageModel;

public class PAnimal{

	private Integer animalno;

	private String animalname;

	private String animalremarks;

	private String ids;
	private String errorMsg;
	private int page;
	private int rows;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getAnimalno() {
		return animalno;
	}

	public void setAnimalno(Integer animalno) {
		this.animalno = animalno;
	}

	public String getAnimalname() {
		return animalname;
	}

	public void setAnimalname(String animalname) {
		this.animalname = animalname;
	}

	public String getAnimalremarks() {
		return animalremarks;
	}

	public void setAnimalremarks(String animalremarks) {
		this.animalremarks = animalremarks;
	}

	@Override
	public String toString() {
		return "PAnimal [animalno=" + animalno + ", animalname=" + animalname
				+ ", animalremarks=" + animalremarks + ", ErrorMsg="
				+ getErrorMsg() + "]";
	}
}
