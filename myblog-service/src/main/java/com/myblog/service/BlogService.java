package com.myblog.service;

import java.util.List;
import java.util.Map;

import com.myblog.entity.BLOG;

public interface BlogService {
	List<BLOG> selectByBlogTime();

	/**
	 * 根据条件获得博客
	 * 
	 * @param map
	 * @return
	 */
	List<BLOG> selectBlog(Map<String, Object> map);

	/**
	 * 获取总的博客数
	 * 
	 * @param map
	 * @return
	 */
	Long getTotalCount(Map<String, Object> map);
	
	 /**
     * 根据博客ID获取博客
     * @param id
     * @return
     */
    BLOG selectByPrimaryKey(Integer id);

    /**
     * 更新博客
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BLOG record);
    
    /**
     * 获得上一篇的博客
     * @param id
     * @return
     */
    BLOG getLastBlog(Integer id);
    
    /**
     * 获得下一篇的博客
     * @param id
     * @return
     */
    BLOG getNextBlog(Integer id);
    
    
    /**
     * 添加博客
     * @param record
     * @return
     */
    int insertSelective(BLOG record);
    
    
    /**
     * 根据之间ID删除 博客
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    
}
