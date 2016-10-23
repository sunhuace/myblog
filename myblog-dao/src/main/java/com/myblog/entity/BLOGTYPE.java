package com.myblog.entity;

public class BLOGTYPE {
	private Integer id;

	private String typename;

	private Integer orderno;
	
	//记录每一种的数量
	private Integer blogCount;

	public Integer getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename == null ? null : typename.trim();
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	@Override
	public String toString() {
		return "BLOGTYPE [id=" + id + ", typename=" + typename + ", orderno=" + orderno + ", blogCount=" + blogCount
				+ "]";
	}

}