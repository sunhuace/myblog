package com.myblog.serviveImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.dao.BLOGTYPEMapper;
import com.myblog.entity.BLOGTYPE;
import com.myblog.service.BlogTypeService;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

	@Autowired
	private BLOGTYPEMapper blogtypeMapper;

	public List<BLOGTYPE> listBlogType() {
		return blogtypeMapper.listBlogType();
	}

	public List<BLOGTYPE> listType(Map<String, Object> map) {
		return blogtypeMapper.listType(map);
	}

	public long getBlogTypeToal(Map<String, Object> map) {
		return blogtypeMapper.getBlogTypeToal(map);
	}

	public int insertSelective(BLOGTYPE record) {
		return blogtypeMapper.insertSelective(record);
	}

	public int updateByPrimaryKeySelective(BLOGTYPE record) {
		return blogtypeMapper.updateByPrimaryKeySelective(record);
	}

	public int deleteByPrimaryKey(Integer id) {
		return blogtypeMapper.deleteByPrimaryKey(id);
	}
	
}
