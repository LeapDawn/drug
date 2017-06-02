package drug.dto.pageModel;

import java.util.List;

public class ResultPageModel<T> {

	private int total; // 总记录数
	private int pages; // 总页数
	private int rows; // 每页记录数
	private int currentPage; // 当前页码
	private String order;   // 排序
	private String sort;     // 升序/降序
	private List<T> data;
	

	public ResultPageModel() {

	}

	public ResultPageModel(int total, int rows, int currentPage, List<T> data) {
		super();
		this.total = total > 0 ? total : 0;
		this.rows = rows > 0 ? rows : 100;
		this.pages = this.total % this.rows == 0 && this.total != 0 ? (this.total / this.rows)
				: (this.total / this.rows) + 1;
		this.currentPage = currentPage <= this.pages ? currentPage : this.pages;
		this.currentPage = this.currentPage > 0 ? this.currentPage : 1;
		this.data = data;
	}

	public ResultPageModel(int total, int rows) {
		this.total = total > 0 ? total : 0;
		this.rows = rows > 0 ? rows : 100;
		pages = this.total % this.rows == 0 && this.total != 0 ? (this.total / this.rows)
				: (this.total / this.rows) + 1;
		this.currentPage = 1;
	}

	public ResultPageModel(int total, int rows, int currentPage) {
		this.total = total > 0 ? total : 0;
		this.rows = rows > 0 ? rows : 100;
		this.pages = this.total % this.rows == 0 && this.total != 0 ? (this.total / this.rows)
				: (this.total / this.rows) + 1;
		this.currentPage = currentPage <= this.pages ? currentPage : this.pages;
		this.currentPage = this.currentPage > 0 ? this.currentPage : 1;
	}

	public ResultPageModel(int total, int rows, int currentPage, String order,
			String sort) {
		this.total = total > 0 ? total : 0;
		this.rows = rows > 0 ? rows : 100;
		this.pages = this.total % this.rows == 0 && this.total != 0 ? (this.total / this.rows)
				: (this.total / this.rows) + 1;
		this.currentPage = currentPage <= this.pages ? currentPage : this.pages;
		this.currentPage = this.currentPage > 0 ? this.currentPage : 1;
		this.order = order;
		this.sort = sort;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
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
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "PageModel [total=" + total + ", pages=" + pages + ", rows="
				+ rows + ", currentPage=" + currentPage + ", data=" + data
				+ "]";
	}

}
