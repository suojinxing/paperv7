package cn.tedu.config;


import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;

@Configuration
public class SpringShiroConfig {
	@Bean // session
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sManager = new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(30 * 60 * 1000); // 10 分钟
		return sManager;
	}

	@Bean // 记住我
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cManager = new CookieRememberMeManager();
//		SimpleCookie cookie = new SimpleCookie("rememberMe");
		SimpleCookie cookie = new SimpleCookie("somesi"); // 有啥用？？？就是一个cook的名字
		cookie.setMaxAge(10 * 60);
		cManager.setCookie(cookie);
		return cManager;
	}

	@Bean // 缓存配置
	public CacheManager shiroCacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean
	public SecurityManager securityManager(Realm realm, CacheManager cacheManager,
			CookieRememberMeManager rememberManager, DefaultWebSessionManager sessionManager) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(cacheManager);
		sManager.setRememberMeManager(rememberManager);
		sManager.setSessionManager(sessionManager);
		return sManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(@Autowired SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/index");
//		sfBean.setLoginUrl("/toDiffHtml");
		// 定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		// 静态资源允许匿名访问:"anon"
		map.put("/bootstrap/**", "anon");
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/img/**", "anon");
		map.put("/checkLogin", "anon");
		map.put("/doLogin", "anon");
		map.put("/file/pic/uploadHeadImg", "anon");
		map.put("/exit", "logout");
		map.put("/doLogout", "logout");
		// 除了匿名访问的资源,其它都要认证("authc")后访问
//		map.put("/**", "authc");
		map.put("/**", "user");// authc
		sfBean.setFilterChainDefinitionMap(map);
		return sfBean;
	}

	// 第一步:配置bean对象的生命周期管理。
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	// 第二步: 通过如下配置要为目标业务对象创建代理对象（SpringBoot中可省略）。
	@DependsOn("lifecycleBeanPostProcessor")
	@Bean
	public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	// 第三步:配置advisor对象,shiro框架底层会通过此对象的matchs方法返回值决定是否创建代理对象,进行权限控制。
	@Bean
	public AuthorizationAttributeSourceAdvisor newAuthorizationAttributeSourceAdvisor(
			@Autowired SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
