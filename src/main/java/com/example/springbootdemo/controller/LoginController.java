package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.bloomFilter.BloomFilterCache;
import com.example.springbootdemo.common.cache.CacheService;
import com.example.springbootdemo.common.db.dao.admin.model.Admin;
import com.example.springbootdemo.common.db.service.AdminService;
import com.example.springbootdemo.controller.params.LoginDTO;
import com.example.springbootdemo.controller.userinfos.param.SessionInfo;
import com.example.springbootdemo.utils.CookieUtil;
import com.example.springbootdemo.utils.enums.ErrorCodeEnum;
import com.example.springbootdemo.utils.I18nMessageUtil;
import com.example.springbootdemo.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.example.springbootdemo.utils.constant.CommonConst.SESSION_KET;
import static com.example.springbootdemo.utils.constant.I18nConstant.SESSION_LANG_KEY;

@Controller
@SessionAttributes(SESSION_LANG_KEY)
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    @Qualifier("redisCacheServiceImpl")
    CacheService cacheService;

    @Autowired
    AdminService adminService;

    @Autowired
    I18nMessageUtil i18nMessageUtil;

    @RequestMapping("/easyui")
    public String easyui(Model model, SessionInfo sessionInfo, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("easyui");
        model.addAttribute("sessionInfo", sessionInfo);
        return "easyui";
    }

    @RequestMapping("/layout")
    public String layout(Model model, SessionInfo sessionInfo, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("layout");
        model.addAttribute("sessionInfo", sessionInfo);
        return "layout";
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("admin");
        return "admin";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("登录");
        return "login";
    }

    @RequestMapping("/message")
    public String message(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("message");
        return "message";
    }

    @RequestMapping("/websocket")
    public String websocket(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("websocket");
        return "websocket";
    }

    @RequestMapping("/jobManager")
    public String jobManager() {
        LOGGER.info("JobManager");
        return "JobManager";
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ModelMap logout(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("登出");
        removeCookie(request, response);
        return success();
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public ModelMap doLogin(Model model, HttpServletRequest request, HttpServletResponse response, String userName,
                            String password, @ModelAttribute(SESSION_LANG_KEY) String lang) {
        return logonProcess(request, response, userName, password);
    }

    private ModelMap logonProcess(HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        if (StringUtils.isBlank(userName)) {
            LOGGER.error("userName不能为空");
//            model.addAttribute("errorMessage", "userName不能为空");
//            return "login";
            String message = i18nMessageUtil.getMessage(ErrorCodeEnum.USER_NAME_IS_NULL.getCode());
            return error("errorMessage", message);
        }
        if (StringUtils.isBlank(password)) {
            LOGGER.error("password不能为空");
//            model.addAttribute("errorMessage", "password不能为空");
//            return "login";
            String message = i18nMessageUtil.getMessage(ErrorCodeEnum.USER_PASSWORD_IS_NULL.getCode());
            return error("errorMessage", message);
        }
        if (!BloomFilterCache.bloomFilter.check(userName)) {
            LOGGER.error("该用户不存在（未通过布隆过滤器）");
            String message = i18nMessageUtil.getMessage(ErrorCodeEnum.USER_NOT_FOUND_WITH_BLOOM_FILTER.getCode());
            return error("errorMessage", String.format(message, userName));
        }
        Admin admin = adminService.queryById(userName);
        if (admin == null) {
            LOGGER.error("errorMessage", "用户" + userName + "不存在");
            String message = i18nMessageUtil.getMessage(ErrorCodeEnum.USER_NOT_FOUND.getCode());
            return error("errorMessage", String.format(message, userName));
        }
        String contractPassword = MD5Util.addSalt(password, admin.getSalt());
        String passWordDB = admin.getPassWord();
        if (!StringUtils.equals(passWordDB, contractPassword)) {
            LOGGER.error("用户名或密码不正确");
            String message = i18nMessageUtil.getMessage(ErrorCodeEnum.USER_OR_PASSWORD_ERROR.getCode());
            return error("errorMessage", message);
        }
        SessionInfo sessionInfo = new SessionInfo(userName, admin.getNickName());
        storeCookie(request, response, sessionInfo);
//        return "redirect:/jobManager";
        return success();
    }

    @RequestMapping("/doLogin2")
    @ResponseBody
    public ModelMap doLogin2(Model model,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody LoginDTO loginDTO,
                             @ModelAttribute(SESSION_LANG_KEY) String lang) {
        return logonProcess(request, response, loginDTO.getUserName(), loginDTO.getPassword());
    }

    private void storeCookie(HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo) {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(SESSION_KET, uuid);
        Cookie cookie = new Cookie(SESSION_KET, uuid);
        cookie.setMaxAge(24 * 3600 * 1000);
        response.addCookie(cookie);
        cacheService.set(uuid, JSON.toJSONString(sessionInfo), 1800 * 1000);
    }

    private void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtil.getCookieByName(request, SESSION_KET);
        cacheService.delete(cookie.getValue());
        CookieUtil.deleteCookieByName(request, response, SESSION_KET);
    }
}
