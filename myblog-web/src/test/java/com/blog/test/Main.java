package com.blog.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myblog.entity.BLOGTYPE;
import com.myblog.entity.COMMENT;
import com.myblog.service.BlogTypeService;
import com.myblog.service.BloggerService;
import com.myblog.service.CommentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class Main {
	
	@Autowired
	private BloggerService bloggerService;
	
	@Autowired
	private BlogTypeService blogTypeService;
	
	@Autowired
	private CommentService commentService;
	

	@Test
	public void test() {
		Map<String, Object> map = new HashMap<>();
		map.put("state", "0");
		List<COMMENT> cList = commentService.listComment(map);
		System.out.println(cList);
	}
	
	
}
