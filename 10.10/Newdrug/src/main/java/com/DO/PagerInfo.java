package com.DO;

/**
 * 
 * @author Lin Chunfei
 *
 */
public class PagerInfo {
	private int draw;
	private String orderColumn;
	private String orderDir;
	private int start;
	private int length;
	private String[] searchColumns;
	private String searchValue;
	
	public PagerInfo(){
		
	}
	
	public PagerInfo(int draw, String orderColumn, String orderDir, int start, int length,
			String[] searchColumns, String searchValue) {
		super();
		this.draw = draw;
		this.orderColumn = orderColumn;
		this.orderDir = orderDir;
		this.start = start;
		this.length = length;
		this.searchColumns = searchColumns;
		this.searchValue = searchValue;
	}
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String[] getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns(String[] searchColumns) {
		this.searchColumns = searchColumns;
	}

	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString() {
		return "PagerInfo [draw=" + draw + ", orderColumn=" + orderColumn + ", orderDir=" + orderDir + ", start="
				+ start + ", length=" + length + ", searchColumns=" + searchColumns + ", searchValue=" + searchValue + "]";
	}	 
	
}
