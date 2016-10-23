package com.myblog.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myblog.entity.BLOG;
import com.myblog.lucene.BlogIndex;
import com.myblog.service.BlogService;
import com.myblog.service.CommentService;
import com.myblog.utils.StringUtil;

@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private CommentService commentService;

	private BlogIndex index = new BlogIndex();

	/**
	 * 请求博客详细信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/articles/{id}")
	public ModelAndView getBlogDetails(@PathVariable("id") Integer id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		BLOG blog = blogService.selectByPrimaryKey(id);
		blog.setClickhit(blog.getClickhit() + 1);
		String keyword = blog.getKeyword();
		if (StringUtil.isNotEmpty(keyword)) {
			String[] split = keyword.split(" ");
			mv.addObject("keywords", StringUtil.filterWhite(Arrays.asList(split)));
		} else {
			mv.addObject("keywords", null);
		}
		blogService.updateByPrimaryKeySelective(blog);
		mv.addObject("blog", blog);
		mv.addObject("title", "博客系统--" + blog.getTitle());
		mv.addObject("content", "blog/view.jsp");
		mv.addObject("pageCode2", this.getLastAndNextBlog(blogService.getLastBlog(id), blogService.getNextBlog(id),
				request.getServletContext().getContextPath()));
		mv.setViewName("frontpage/indexTemplet");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blog.getId());
		map.put("state", "1");
		mv.addObject("commentList", commentService.listComment(map));
		return mv;
	}

	private String getLastAndNextBlog(BLOG lastBlog, BLOG nextBlog, String projectContext) {
		StringBuffer sb = new StringBuffer();
		if (lastBlog == null || lastBlog.getId() == null) {
			sb.append("<p>上一篇：没有了</p>");
		} else {
			sb.append("<p>上一篇：<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>"
					+ lastBlog.getTitle() + "</a></p>");
		}

		if (nextBlog == null || nextBlog.getId() == null) {
			sb.append("<p>下一篇：没有了</p>");
		} else {
			sb.append("<p>下一篇：<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>"
					+ nextBlog.getTitle() + "</a></p>");

		}
		return sb.toString();
	}

	@RequestMapping("/q")
	private ModelAndView searchBlog(@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "page", required = false) String page, HttpServletRequest request) throws Exception {
		int pageSize = 10;
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		List<BLOG> sList = index.searchBlog(q);
		ModelAndView mv = new ModelAndView();
		Integer toIndex = sList.size() >= Integer.parseInt(page) * pageSize
				? Integer.parseInt(page) * pageSize : sList.size();
		mv.addObject("blogList",sList.subList((Integer.parseInt(page) - 1) * pageSize, toIndex));
		mv.addObject("q", q);
		mv.addObject("pageCode",this.genUpAndDownPageCode(Integer.parseInt(page), sList.size(), q, pageSize, request.getServletContext().getContextPath()));
		mv.addObject("resultTotal", sList.size());
		mv.addObject("title", "博客系统--搜索关键字'" + q + "'的结果页面");
		mv.addObject("content", "blog/searchResult.jsp");
		mv.setViewName("frontpage/indexTemplet");
		return mv;
	}

	/**
	 * 获取上一页，下一页代码
	 * 
	 * @param page
	 * @param totalNum
	 * @param q
	 * @param pageSize
	 * @param projectContext
	 * @return
	 */
	private String genUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize,
			String projectContext) {
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		StringBuffer pageCode = new StringBuffer();
		if (totalPage == 0) {
			return "";
		} else {
			pageCode.append("<nav>");
			pageCode.append("<ul class='pager'>");
			if (page > 1) {
				pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page - 1) + "&q=" + q
						+ "'>上一页</a></li>");
			} else {
				pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
			}
			if (page < totalPage) {
				pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page + 1) + "&q=" + q
						+ "'>下一页</a></li>");
			} else {
				pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
			}
			pageCode.append("</ul>");
			pageCode.append("</nav>");
		}
		return pageCode.toString();
	}
}
