package com.myblog.entity;

import java.util.Date;

public class COMMENT {
    private Integer id;

    private String userip;

    private Integer blogid;

    private String content;

    private Date commentdate;

    private Integer state;
    
    private BLOG blog;

    public BLOG getBlog() {
		return blog;
	}

	public void setBlog(BLOG blog) {
		this.blog = blog;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip == null ? null : userip.trim();
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "COMMENT [id=" + id + ", userip=" + userip + ", blogid=" + blogid + ", content=" + content
				+ ", commentdate=" + commentdate + ", state=" + state + ", blog=" + blog + "]";
	}

    
}