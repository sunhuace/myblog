package com.myblog.service;

import java.util.List;
import java.util.Map;

import com.myblog.entity.COMMENT;

public interface CommentService {
	
	/**
	 * 评论列表
	 * @param map
	 * @return
	 */
	public List<COMMENT> listComment(Map<String, Object> map);
	
	 /**
     * 添加评论
     * @param comment
     * @return int
     */
    int addComment(COMMENT comment);
    
    /**
     * 得到评论的总的条数
     * @return
     */
    long getToatlComment();
    
    /**
     * 更新评论状态
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(COMMENT record);
}
