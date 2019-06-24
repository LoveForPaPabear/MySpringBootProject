package com.example.myspringproject.config;

import com.example.myspringproject.model.MyShiroRelam;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ppbear
 */
@Configuration
public class ShiroConfig {

    public ShiroConfig() {
        System.out.println("ShiroConfig  init ....");
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //权限配置
        //filterChainDefinitionMap.put("/stu/addStu","perms[student:aaaa]");
        // 配置不会被拦截的链接 顺序判断  相关静态资源
        filterChainDefinitionMap.put("/assets/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/css/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/font/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/images/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/js/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/products/**", DefaultFilter.anon.toString());
        filterChainDefinitionMap.put("/Widget/**", DefaultFilter.anon.toString());

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;

        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", DefaultFilter.anon.toString());// 先配置可以通过
//        filterChainDefinitionMap.put("/**", DefaultFilter.authc.toString());
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

//    @Bean
//    public SecurityManager securityManager(MyShiroRelam realm, CacheManager cacheManager, SessionManager sessionManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setSessionManager(sessionManager);
//        securityManager.setRealm(realm);
//        securityManager.setCacheManager(cacheManager);
//        return securityManager;
//    }

//    @Bean
//    public CacheManager cacheManager(CustomCacheManager cacheManager) {
//        CacheManager ehCacheManager = new CacheManager();
//        ehCacheManager.setCacheManager(cacheManager);
//        return ehCacheManager;
//    }


    @Bean
    public MyShiroRelam MyShiroRelam() {
        return new MyShiroRelam();
    }

    /*
     * 开启@RequirePermission注解的配置，要结合DefaultAdvisorAutoProxyCreator一起使用，或者导入aop的依赖
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*
     *安全管理器配置
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(MyShiroRelam());
        return securityManager;
    }

}
