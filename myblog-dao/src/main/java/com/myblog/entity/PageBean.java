package com.myblog.entity;

public class PageBean {

	private int page; // 页面上的页数
	private int pageSize; // 每一页的条数
	private int start;  // 查询开始位置
	
	
	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.start = (page-1)*pageSize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}
	
	
}
