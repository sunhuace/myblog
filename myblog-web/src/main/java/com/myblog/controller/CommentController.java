package com.myblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.BLOG;
import com.myblog.entity.COMMENT;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import com.myblog.utils.ResponseUtil;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private BlogService blogService;
	/**
	 * 保存评论
	 * @param comment
	 * @param imageCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/saveComment")
	public String saveComment(COMMENT comment, @RequestParam(value = "imageCode") String imageCode,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) 
					throws Exception {
		int addComment = 0;
		String sRand = (String) session.getAttribute("sRand");
		JSONObject result = new JSONObject();
		if(!sRand.equals(imageCode)) {
			 result.put("success", false);
			 result.put("errorInfo", "验证码错误！！");
			 ResponseUtil.write(response, result);
		} else {
			if(comment.getId() == null) {
				String userip = request.getRemoteAddr();//获取用户IP
				comment.setUserip(userip);
				addComment = commentService.addComment(comment);
				BLOG blog = blogService.selectByPrimaryKey(comment.getBlog().getId());
				//回复此时
				blog.setReplyhit(blog.getReplyhit() + 1);
				blogService.updateByPrimaryKeySelective(blog);
			} else {
				
			}
			if(addComment > 0) {
				 result.put("success", true);
				 result.put("errorInfo", "评论成功！");
				 ResponseUtil.write(response, result);
			} else {
				 result.put("success", false);
				 result.put("errorInfo", "评论失败！");
				 ResponseUtil.write(response, result);
			}
		}
		return null;
	}
}
