package com.myblog.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.myblog.entity.BLOGGER;
import com.myblog.service.BloggerService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private BloggerService bloggerService;

	/**
	 * 为当前登录的用户进行授权的操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 对当前登录的用户进行验证的操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		BLOGGER blogger = bloggerService.selectByUserName(userName);
		if (blogger != null) {
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("currentUser", blogger);
			AuthenticationInfo info = new SimpleAuthenticationInfo(blogger.getUsername(), blogger.getPassword(),
					this.getName());
			return info;
		} else {
			return null;
		}
	}

}
