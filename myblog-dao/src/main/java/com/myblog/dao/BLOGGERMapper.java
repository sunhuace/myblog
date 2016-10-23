package com.myblog.dao;

import com.myblog.entity.BLOGGER;

public interface BLOGGERMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BLOGGER record);

    int insertSelective(BLOGGER record);

    BLOGGER selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BLOGGER record);

    int updateByPrimaryKeyWithBLOBs(BLOGGER record);

    int updateByPrimaryKey(BLOGGER record);

	BLOGGER selectByUserName(String userName);

	BLOGGER selectBlogger();
}