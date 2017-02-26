package drug.dto.pageModel;

public class PLinkURL {

	private String url;

	private String name;

	private String other;

	private int page;

	private int rows;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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

	@Override
	public String toString() {
		return "PLinkURL [url=" + url + ", name=" + name + ", other=" + other
				+ ", page=" + page + ", rows=" + rows + "]";
	}
}
