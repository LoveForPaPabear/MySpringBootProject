package com.example.myspringproject.config;

import com.example.myspringproject.cache.CustomCacheManager;
import com.example.myspringproject.model.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ppbear
 */
@Configuration
public class ShiroConfig {

    public ShiroConfig() {
        System.out.println("ShiroConfig  init ....");
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //权限配置
        filterChainDefinitionMap.put("/assets/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/css/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/font/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/images/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/js/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/products/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/Widget/**", DefaultFilter.anon.toString());

        filterChainDefinitionMap.put("/user/login", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/logout", "logout");
        // 先配置可以通过
        filterChainDefinitionMap.put("/**", DefaultFilter.authc.toString());
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //会话管理
        securityManager.setSessionManager(sessionManager());
        //自定义realm
        securityManager.setRealm(MyShiroRealm());
        //实现缓存管理
        securityManager.setCacheManager(cacheManager());

        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdCookie(simpleCookie());
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        return defaultWebSessionManager;
    }

    /**
     * 这里就是会话管理的操作类
     */
    @Bean
    public SessionDAO sessionDAO() {
//        return new RedisSessionDao();
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return enterpriseCacheSessionDAO;
    }

    /**
     * 加密方式配置
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        ////散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        ////散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 缓存管理器
     **/
    @Bean
    public CacheManager cacheManager() {
        return new CustomCacheManager();
    }

    @Bean
    public SimpleCookie simpleCookie() {
        return new SimpleCookie("REDISSESSION");
    }

    @Bean
    public MyShiroRealm MyShiroRealm() {
        return new MyShiroRealm();
    }


    /**
     * 开启@RequirePermission注解的配置，要结合DefaultAdvisorAutoProxyCreator一起使用，或者导入aop的依赖
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 定义Spring MVC的异常处理器
     */
    @Bean
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //数据库异常处理
        mappings.setProperty("DatabaseException", "databaseError");
        //处理shiro的认证未通过异常
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings);
//        r.setDefaultErrorView("error");
//        r.setExceptionAttribute("ex");
        return r;
    }

    /**
     * 管理生命周期 注意这个 static
     **/
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
