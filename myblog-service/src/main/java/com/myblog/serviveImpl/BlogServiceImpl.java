package com.myblog.serviveImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.dao.BLOGMapper;
import com.myblog.entity.BLOG;
import com.myblog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BLOGMapper bLOGMapper;
	
	public List<BLOG> selectByBlogTime() {
		return bLOGMapper.selectByBlogTime();
	}

	public List<BLOG> selectBlog(Map<String, Object> map) {
		return bLOGMapper.selectBlog(map);
	}

	public Long getTotalCount(Map<String, Object> map) {
		return bLOGMapper.getTotalCount(map);
	}

	public BLOG selectByPrimaryKey(Integer id) {
		return bLOGMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(BLOG record) {
		return bLOGMapper.updateByPrimaryKeySelective(record);
	}

	public BLOG getLastBlog(Integer id) {
		return bLOGMapper.getLastBlog(id);
	}

	public BLOG getNextBlog(Integer id) {
		return bLOGMapper.getNextBlog(id);
	}

	public int insertSelective(BLOG record) {
		return bLOGMapper.insertSelective(record);
	}

	public int deleteByPrimaryKey(Integer id) {
		return bLOGMapper.deleteByPrimaryKey(id);
	}

}
