package com.myblog.service;

import java.util.List;
import java.util.Map;

import com.myblog.entity.BLOGTYPE;

public interface BlogTypeService {
	
	List<BLOGTYPE> listBlogType();

	/**
	 * 显示博客的类别 根据条件
	 * 
	 * @return
	 */
	List<BLOGTYPE> listType(Map<String, Object> map);

	/**
	 * 根据条件显示博客的总的记录数
	 * 
	 * @param map
	 * @return
	 */
	long getBlogTypeToal(Map<String, Object> map);
	
	/**
     * 插入博客类型
     * @param record
     * @return
     */
    int insertSelective(BLOGTYPE record);
    
    /**
     * 修改博客类型
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BLOGTYPE record);
    
    /**
     * 删除博客类型
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    
}
