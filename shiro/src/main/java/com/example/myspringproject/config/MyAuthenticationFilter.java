package com.example.myspringproject.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean allowed = super.isAccessAllowed(request, response, mappedValue);
        if (!allowed) {
            // 判断请求是否是options请求
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;
            }
        }
        return allowed;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) { // 判断是否登录
            if (isLoginSubmission(request, response)) { // 判断是否为post访问
                return executeLogin(request, response);
            } else {
                // sessionID已经注册,但是并没有使用post方式提交
                return true;
            }
        } else {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            /*
             * 跨域访问有时会先发起一条不带token，不带cookie的访问。
             * 这就需要我们抓取这条访问，然后给他通过，否则只要是跨域的访问都会因为未登录或缺少权限而被拦截
             * （如果重写了isAccessAllowed，就无需下面的判断）
             */
//            if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
//                resp.setStatus(HttpStatus.OK.value());
//                return true;
//            }
            /*
             * 跨域的第二次请求就是普通情况的request了，在这对他进行拦截
             */
            String ajaxHeader = req.getHeader("dev");
            if (StringUtils.isNotBlank(ajaxHeader)) {
                // 前端Ajax请求，则不会重定向
                resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
                resp.setHeader("Access-Control-Allow-Credentials", "true");
                resp.setContentType("application/json; charset=utf-8");
                resp.setCharacterEncoding("UTF-8");
                resp.setStatus(HttpStatus.UNAUTHORIZED.value());//设置未登录状态码
                PrintWriter out = resp.getWriter();
//				Map<String, String> result = new HashMap<>();
//				result.put("MESSAGE", "未登录用户");
                String result = "{\"MESSAGE\":\"未登录用户\"}";
                out.println(result);
                out.flush();
                out.close();
            } else {
                // == 如果是普通访问重定向至shiro配置的登录页面 == //
                saveRequestAndRedirectToLogin(request, response);
            }
        }
        return false;
    }
}