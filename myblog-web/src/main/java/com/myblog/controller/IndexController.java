package com.myblog.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myblog.entity.BLOG;
import com.myblog.entity.PageBean;
import com.myblog.service.BlogService;
import com.myblog.utils.PageUtil;
import com.myblog.utils.StringUtil;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private BlogService blogService;

	/**
	 * 请求主页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "releaseDateStr", required = false) String releaseDateStr) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 5);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		List<BLOG> bList = blogService.selectBlog(map);
		mv.addObject("bList", bList);
		
		mv.addObject("content","blog/list.jsp");
		mv.addObject("title", "首页");
		
		mv.addObject("active_about", "");
		mv.addObject("active_main", "active");
		mv.addObject("active_bushit", "");
		
		for (Iterator<BLOG> it = bList.iterator(); it.hasNext();) {
			BLOG blog = it.next();
			List<String> imageList = blog.getImageList();
			Document document = Jsoup.parse(blog.getContent());
			Elements elements = document.select("img[src$=.jpg]");
			for (int i = 0; i < elements.size(); i++) {
				Element element = elements.get(i);
				imageList.add(element.toString());
				if (i == 2) {
					break;
				}
			}
		}
		//在请求分页的时候加入相应的查询参数
		StringBuffer sb = new StringBuffer();
		if(StringUtil.isNotEmpty(typeId)) {
			sb.append("typeId=" + typeId + "&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)) {
			sb.append("releaseDateStr=" + releaseDateStr);
		}
		mv.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/index.html",
				blogService.getTotalCount(map), Integer.parseInt(page), 5, sb.toString()));
		// 使用服务器内部的跳转方式进行跳转
		mv.setViewName("frontpage/indexTemplet");
		return mv;
	}

}
