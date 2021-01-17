package com.example.springbootdemo.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.springbootdemo.common.cache.CacheService;
import com.example.springbootdemo.common.db.dao.permission.mapper.PermissionMapper;
import com.example.springbootdemo.common.db.dao.permission.model.Permission;
import com.example.springbootdemo.common.db.dao.permission.model.PermissionExample;
import com.example.springbootdemo.controller.userinfos.param.SessionInfo;
import com.example.springbootdemo.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.example.springbootdemo.utils.constant.CommonConst.SESSION_KET;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminInterceptor.class);
    @Value("${admin.userNumber}")
    private String userNumber;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    @Qualifier("redisCacheServiceImpl")
    CacheService cacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String sessionKey = CookieUtil.getValueCookieByName(request, SESSION_KET);
        if (StringUtils.isBlank(sessionKey)) {
            return false;
        }
        String userInfo = cacheService.get(sessionKey, String.class);
        SessionInfo sessionInfo = JSON.parseObject(userInfo, new TypeReference<SessionInfo>() {
        });
        if (sessionInfo == null) {
            return false;
        }
        String userName = sessionInfo.getUserId();
        if (StringUtils.isBlank(userName)) {
            return false;
        }
        if (userName.equals(userNumber)) {
            return true;
        }
        LOGGER.info("Admin拦截器");
        String requestURI = request.getRequestURI();
        if (StringUtils.startsWith(requestURI, "/admin") && !isAdmin(userName)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    private boolean isAdmin(String userNumber) {
        if (StringUtils.isBlank(userNumber)) {
            return false;
        }
        PermissionExample condition = new PermissionExample();
        condition.createCriteria().andUsernumberEqualTo(userNumber);
        List<Permission> permissions = permissionMapper.selectByExample(condition);
        return CollectionUtils.isEmpty(permissions) ? false : true;
    }
}
