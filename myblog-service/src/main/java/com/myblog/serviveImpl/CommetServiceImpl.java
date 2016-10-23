package com.myblog.serviveImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myblog.dao.COMMENTMapper;
import com.myblog.entity.COMMENT;
import com.myblog.service.CommentService;

@Service("commentService")
public class CommetServiceImpl implements CommentService {

	@Autowired
	private COMMENTMapper commentMapper;
	
	public List<COMMENT> listComment(Map<String, Object> map) {
		return commentMapper.listComment(map);
	}

	public int addComment(COMMENT comment) {
		return commentMapper.addComment(comment);
	}

	public long getToatlComment() {
		return commentMapper.getToatlComment();
	}

	public int updateByPrimaryKeySelective(COMMENT record) {
		return commentMapper.updateByPrimaryKey(record);
	}

}
