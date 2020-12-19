package com.example.springbootdemo.intercepter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemo.common.cache.CacheService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.example.springbootdemo.utils.CommonConst.SESSION_KET;

public class LoginCheckIntercepter extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCheckIntercepter.class);
    @Autowired  @Qualifier("redisCacheServiceImpl")
    CacheService cacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("登录拦截器");
        LOGGER.info(request.getRequestURL().toString());
        if (checkLogin(request)) {
            String accept = request.getHeader("accept");
            if (StringUtils.isNotBlank(accept) && StringUtils.contains(accept, "json")) {
                JSONObject errorJSONObject = new JSONObject();
                errorJSONObject.put("errorMessage", "登录超时，请重新登录！");
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().println(JSON.toJSONString(errorJSONObject));
                LOGGER.info(JSON.toJSONString(errorJSONObject));
                response.getWriter().flush();
                response.getWriter().close();
            } else {
                redirect(request, response);
            }
            return false;
        }
        return true;
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(request.getRequestURL().toString() + "/login");
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
