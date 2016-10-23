package com.myblog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.COMMENT;
import com.myblog.entity.PageBean;
import com.myblog.service.CommentService;
import com.myblog.utils.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class CommentAdminController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/comment/listComment")
	public String listComment(HttpServletResponse response, @RequestParam(value="page", required=false)String page, @RequestParam(value="rows", required=false)String rows) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(rows), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<>();
		map.put("state", "0");
		map.put("page", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		List<COMMENT> cList = commentService.listComment(map);
		long total = commentService.getToatlComment();
		JSONObject object = new JSONObject();
		object.put("total", total);
		object.put("rows", JSON.toJSON(cList));
		ResponseUtil.write(response, object);
		return null;
	}
	
	@RequestMapping("/comment/modifyComment")
	public String modifyComment(HttpServletResponse response,@RequestParam(value="id", required=false)String id) throws Exception {
		COMMENT comment = new COMMENT();
		comment.setState(2);
		comment.setId(Integer.parseInt(id));
		int result = commentService.updateByPrimaryKeySelective(comment);
		if(result > 0) {
			JSONObject object = new JSONObject();
			object.put("success", true);
			ResponseUtil.write(response, object);
		} else {
			JSONObject object = new JSONObject();
			object.put("success", false);
			ResponseUtil.write(response, object);
		}
		return null;
	}
}
