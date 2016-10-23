package com.myblog.serviveImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.dao.BLOGGERMapper;
import com.myblog.entity.BLOGGER;
import com.myblog.service.BloggerService;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

	@Autowired
	private BLOGGERMapper bloggerMapper;

	public BLOGGER selectByPrimaryKey(Integer id) {
        return bloggerMapper.selectByPrimaryKey(id);
	}

	public BLOGGER selectByUserName(String userName) {
		return bloggerMapper.selectByUserName(userName);
	}

	public BLOGGER selectBlogger() {
		return bloggerMapper.selectBlogger();
	}
}
