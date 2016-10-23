package com.myblog.dao;

import java.util.List;
import java.util.Map;

import com.myblog.entity.BLOGTYPE;

public interface BLOGTYPEMapper {
   
    int insert(BLOGTYPE record);

   

    BLOGTYPE selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(BLOGTYPE record);
    
    /**
     * 显示博客的类别
     * @return
     */
    List<BLOGTYPE> listBlogType();
    
    /**
     * 显示博客的类别 根据条件
     * @return
     */
    List<BLOGTYPE> listType(Map<String, Object> map);
    
    /**
     * 根据条件显示博客的总的记录数
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