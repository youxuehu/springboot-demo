package com.example.springbootdemo.interceptor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static com.example.common.utils.constant.I18nConstant.SESSION_LANG_KEY;


public class LocaleRequestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocaleRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("进入LocaleRequestFilter过滤器");
        }
        String lang = request.getParameter("lang");
        HttpSession session = request.getSession();
        if (session != null && StringUtils.isNotBlank(lang)) {
            session.setAttribute(SESSION_LANG_KEY, lang);
        }
        if (session != null && session.getAttribute(SESSION_LANG_KEY) == null) {
            session.setAttribute(SESSION_LANG_KEY, "en");
        }
        lang = (String) session.getAttribute(SESSION_LANG_KEY);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if(StringUtils.isNotBlank(lang) && "zh".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("设置语言----- zh");
            }
        }else if(StringUtils.isNotBlank(lang) && "en".equals(lang)){
            localeResolver.setLocale(request, response, new Locale("en", "US"));
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("设置语言----- en");
            }
        }
        return true;
    }
}
