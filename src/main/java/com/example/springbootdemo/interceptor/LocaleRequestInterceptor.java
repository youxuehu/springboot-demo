package com.example.springbootdemo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleRequestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("进入LocaleRequestFilter过滤器");
        }
        String lang = request.getParameter("lang");
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if("zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("设置语言----- zh");
            }
        }else if("en".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("设置语言----- en");
            }
        }
        return true;
    }
}
