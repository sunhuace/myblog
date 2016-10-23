package com.myblog.dao;

import java.util.List;

import com.myblog.entity.FRIENDLINK;

public interface FRIENDLINKMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FRIENDLINK record);

    int insertSelective(FRIENDLINK record);

    FRIENDLINK selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FRIENDLINK record);

    int updateByPrimaryKey(FRIENDLINK record);
    
    List<FRIENDLINK> findAllLink();
}