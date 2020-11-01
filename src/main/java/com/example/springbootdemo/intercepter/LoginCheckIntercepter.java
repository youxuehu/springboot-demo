package com.example.springbootdemo.intercepter;

import com.example.springbootdemo.common.cache.CacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.example.springbootdemo.utils.CommonConst.SESSION_KET;

public class LoginCheckIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    CacheService cacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("登录拦截器");
        if (checkLogin(request)) {
            redirect(request, response);
            return false;
        }
        return true;
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect("http://localhost:9090/login");
    }

    private boolean checkLogin(HttpServletRequest request) {
        String cacheKey = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return true;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (StringUtils.equals(SESSION_KET, name)) {
                String value = cookie.getValue();
                if (StringUtils.isBlank(value)) {
                    return true;
                }
                cacheKey = value;
            }
        }
        String cacheValue = cacheService.get(cacheKey, String.class);
        if (StringUtils.isBlank(cacheValue)) {
            return true;
        }
        return false;
    }

}
