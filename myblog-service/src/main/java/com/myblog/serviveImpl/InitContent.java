package com.myblog.serviveImpl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.myblog.entity.BLOG;
import com.myblog.entity.BLOGGER;
import com.myblog.entity.BLOGTYPE;
import com.myblog.entity.FRIENDLINK;
import com.myblog.service.BlogService;
import com.myblog.service.BlogTypeService;
import com.myblog.service.BloggerService;
import com.myblog.service.FriendLinkService;

@Component
public class InitContent implements ServletContextListener, ApplicationContextAware {
	
	/**
	 * 必须声明为static 静态的
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 初始化ServletContext
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
		FriendLinkService friendLinkService = (FriendLinkService) applicationContext.getBean("friendLinkService");
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		BLOGGER blogger = bloggerService.selectBlogger();
		List<FRIENDLINK> allLink = friendLinkService.findAllLink();
		List<BLOGTYPE> blogType = blogTypeService.listBlogType();
		List<BLOG> blogTime = blogService.selectByBlogTime();
		blogger.setPassword("");
		// 此处将博主的信息放置在Application域中  实现数据的整个服务器的共享
		context.setAttribute("blogger", blogger);
		context.setAttribute("allLink", allLink);
		context.setAttribute("blogType", blogType);
		context.setAttribute("blogTime", blogTime);
	}

	/**
	 * 销毁ServletContext
	 */
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * 设置AppleationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
