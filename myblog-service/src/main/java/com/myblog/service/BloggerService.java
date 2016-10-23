package com.myblog.service;

import com.myblog.entity.BLOGGER;

/**
 * Created by sun on 2016/9/13.
 */
public interface BloggerService {
	/**
	 * 根据博主ID查找博主
	 * @param id
	 * @return
	 */
    BLOGGER selectByPrimaryKey(Integer id);
    
    /**
     * 根据用户名查找博主
     * @param userName
     * @return
     */
    BLOGGER selectByUserName(String userName);
    
    /**
     * 查找唯一的博主
     * @param userName
     * @return
     */
    BLOGGER selectBlogger(); 
    
}
