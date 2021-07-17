package com.example.springbootdemo.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemo.common.cache.CacheService;
import com.example.springbootdemo.controller.userinfos.param.SessionInfo;
import com.example.springbootdemo.holder.ThreadLocalHolder;
import com.example.springbootdemo.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.common.utils.constant.CommonConst.SESSION_KET;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCheckInterceptor.class);

    @Autowired
    @Qualifier("redis")
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
                return false;
            }
            return false;
        }
        return true;
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();
        if (StringUtils.equals(requestURI, "/")) {
            response.sendRedirect(requestURL + "login");
            return;
        }
        String redirectURL = StringUtils.replace(requestURL, requestURI, "");
        response.sendRedirect(redirectURL + "/login");
    }

    private boolean checkLogin(HttpServletRequest request) {
        // 检查cookie是否存在
        Cookie cookie = CookieUtil.getCookieByName(request, SESSION_KET);
        if (cookie == null) {
            return true;
        }
        // 检查缓存是否存在
        String cacheValue = cacheService.get(cookie.getValue(), String.class);
        if (StringUtils.isBlank(cacheValue)) {
            return true;
        }
        // 存线程里面
        ThreadLocalHolder.set(JSON.parseObject(cacheValue, SessionInfo.class));
        request.getSession().setAttribute(SESSION_KET, JSON.parseObject(cacheValue));
        return false;
    }
}
