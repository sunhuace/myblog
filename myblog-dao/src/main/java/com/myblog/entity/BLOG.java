package com.myblog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BLOG {
	private Integer id;

	private String title;

	private String summary;

	private Date releasedate;

	private Integer clickhit;

	private Integer replyhit;

	private Integer typeid;

	private String keyword;

	private String content;
	
	private String contentNoTag;//lucence 分词使用
	

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}

	// 记录博客的类型
	private BLOGTYPE blogType;

	// 记录博客的数量
	private Integer blogCount;

	// 发布日期的年月
	private String releaseDateStr;
	
	//用于博客图片的缩略图
	private List<String> imageList = new ArrayList<String>();
	
	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	public BLOGTYPE getBlogType() {
		return blogType;
	}

	public void setBlogType(BLOGTYPE blogType) {
		this.blogType = blogType;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public Integer getClickhit() {
		return clickhit;
	}

	public void setClickhit(Integer clickhit) {
		this.clickhit = clickhit;
	}

	public Integer getReplyhit() {
		return replyhit;
	}

	public void setReplyhit(Integer replyhit) {
		this.replyhit = replyhit;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@Override
	public String toString() {
		return "BLOG [id=" + id + ", title=" + title + ", summary=" + summary + ", releasedate=" + releasedate
				+ ", clickhit=" + clickhit + ", replyhit=" + replyhit + ", typeid=" + typeid + ", keyword=" + keyword
				+ ", content=" + content + ", blogType=" + blogType + ", blogCount=" + blogCount + ", releaseDateStr="
				+ releaseDateStr + ", imageList=" + imageList + "]";
	}
}