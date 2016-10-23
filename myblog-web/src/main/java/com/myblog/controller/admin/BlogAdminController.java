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
import com.myblog.entity.BLOG;
import com.myblog.entity.PageBean;
import com.myblog.lucene.BlogIndex;
import com.myblog.service.BlogService;
import com.myblog.utils.ResponseUtil;
import com.myblog.utils.StringUtil;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
	@Autowired
	private BlogService blogService;
	
	private BlogIndex index = new BlogIndex();

	/**
	 * 添加或者修改博客信息
	 * 
	 * @param blog
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAndUpdate")
	public String saveAndUpdate(BLOG blog, HttpServletResponse response) throws Exception {
		int result = 0;
		if (blog.getId() == null) {
			result = blogService.insertSelective(blog);
			index.addIndex(blog);
		} else {
			result = blogService.updateByPrimaryKeySelective(blog);
			index.updateIndex(blog);
		}
		JSONObject object = new JSONObject();
		if (result > 0) {
			object.put("success", true);
		} else {
			object.put("success", false);
		}
		ResponseUtil.write(response, object);
		return null;
	}

	/**
	 * 分页查询博客的信息
	 * 
	 * @param page
	 * @param rows
	 * @param blog
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/listBlog")
	public String listBlog(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, BLOG blog, HttpServletResponse response) throws Exception {

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", StringUtil.formatLike(blog.getTitle()));
		map.put("page", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		List<BLOG> blogList = blogService.selectBlog(map);
		Long total = blogService.getTotalCount(map);
		
		JSONObject result = new JSONObject();
		result.put("rows", JSON.toJSON(blogList));
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 删除博客
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteBlog")
	public String deleteBlog(@RequestParam(value="ids", required=false)String ids, HttpServletResponse response) throws Exception {
		String[] idStr = ids.split(",");
		for (String str : idStr) {
			blogService.deleteByPrimaryKey(Integer.parseInt(str));
			index.deleteIndex(str);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 根据 主键ID 查找博客信息
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findBlogById")
	public String findBlogById(@RequestParam(value="id", required=true)String id, HttpServletResponse response) throws Exception {
		BLOG blog = blogService.selectByPrimaryKey(Integer.parseInt(id));
		ResponseUtil.write(response, JSON.toJSON(blog));
		return null;
	} 
}
