package cn.tedu.util;

import org.apache.shiro.SecurityUtils;

import cn.tedu.pojo.User;

public class ShiroUtil {
	public static User getUser() {
		return (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
	}

	public static String getName() {
		return getUser().getUsername();
	}
}
