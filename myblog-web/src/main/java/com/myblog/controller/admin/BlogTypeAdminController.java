package com.myblog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myblog.entity.BLOG;
import com.myblog.entity.BLOGTYPE;
import com.myblog.entity.PageBean;
import com.myblog.service.BlogService;
import com.myblog.service.BlogTypeService;
import com.myblog.utils.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class BlogTypeAdminController {
	
	@Autowired
	private BlogTypeService blogTypeService;
	
	@Autowired
	private BlogService blogService; 
	
	/**
	 * 博客列表显示
	 */
	@RequestMapping("/blogType/listBlogType") 
	public ModelAndView listBlogType(HttpServletResponse response, @RequestParam(value="page", required=false)String page,
			@RequestParam(value="rows", required=false)String rows) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		List<BLOGTYPE> listType = blogTypeService.listType(map);
		long total = blogTypeService.getBlogTypeToal(map);
		JSONObject object = new JSONObject();
		object.put("total", total);
		object.put("rows", JSON.toJSON(listType));
		ResponseUtil.write(response, object);
		return mv;
	}
	
	@RequestMapping("/blogType/saveOrupdate")
	public String saveOrUpdate(BLOGTYPE blogType, HttpServletResponse response) throws Exception {
		int result = 0;
		if(blogType.getId() == null) {
			result = blogTypeService.insertSelective(blogType);
		} else {
			result = blogTypeService.updateByPrimaryKeySelective(blogType);
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
	
	@RequestMapping("/blogType/deleteBlogType")
	public String deleteBlogType(@RequestParam(value="ids", required = true)String ids, HttpServletResponse response) throws Exception {
		String [] id_S = ids.split(",");
		for(String str : id_S) {
			Map<String, Object> map = new HashMap<>();
			map.put("typeId", Integer.parseInt(str));
			List<BLOG> bList = blogService.selectBlog(map);
			if(bList.size() == 0) {
				blogTypeService.deleteByPrimaryKey(Integer.parseInt(str));
				JSONObject object = new JSONObject();
				object.put("success", true);
				ResponseUtil.write(response, object);
			} else {
				JSONObject object = new JSONObject();
				object.put("success", false);
				ResponseUtil.write(response, object);
				break;
			}
		}
		return null;
	}
}
