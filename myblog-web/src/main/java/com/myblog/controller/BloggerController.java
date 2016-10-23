package com.myblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myblog.entity.BLOGGER;
import com.myblog.utils.CryptographyUtil;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
	/**
	 * 
	 * 博主管理的刚登陆过程
	 * @param blogger
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(BLOGGER blogger, HttpServletRequest request) {
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(),
				CryptographyUtil.md5(blogger.getPassword(), "123"));
		try {
			subject.login(token);
			return "redirect:/admin/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或者密码错误！");
			return "login";
		}
	}
    
    @RequestMapping("/aboutAuthor")
    public ModelAndView aboutAuthor() {
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("content","blogger/aboutAuthor.jsp");
		mv.addObject("title", "关于博主");
		
		mv.addObject("active_about", "active");
		mv.addObject("active_main", "");
		mv.addObject("active_bushit", "");
		
		mv.setViewName("frontpage/indexTemplet");
    	return mv;
    }
    
    @RequestMapping("/bishit")
    public ModelAndView bushit() {
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("content","bushit/bushit.jsp");
		mv.addObject("title", "扯淡话题");
		
		mv.addObject("active_about", "");
		mv.addObject("active_main", "");
		mv.addObject("active_bushit", "active");
		
		mv.setViewName("frontpage/indexTemplet");
    	return mv;
    }

}
