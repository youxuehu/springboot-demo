package com.example.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.common.cache.CacheService;
import com.example.springbootdemo.controller.userinfos.param.SessionInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.example.springbootdemo.utils.CommonConst.SESSION_KET;

@Controller
public class LoginController extends BaseController {
    @Autowired
    CacheService cacheService;
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/jobManager")
    public String jobManager() {
        System.out.println("JobManager");
        return "JobManager";
    }

    @RequestMapping("/doLogin")
    public String doLogin(Model model, HttpServletRequest request, HttpServletResponse response, String userName, String password) {
        if (StringUtils.isBlank(userName)) {
            model.addAttribute("errorMessage", "userName不能为空");
            return "login";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("errorMessage", "password不能为空");
            return "login";
        }
        if (!StringUtils.equals("admin", userName) || !StringUtils.equals("123456", password)) {
            model.addAttribute("errorMessage", "用户名或密码不正确");
            return "login";
        }
        SessionInfo sessionInfo = new SessionInfo("WB520289", userName);
        storeCookie(request, response, sessionInfo);
        return "redirect:/jobManager";
    }
    private void storeCookie(HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo) {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(SESSION_KET, uuid);
        Cookie cookie = new Cookie(SESSION_KET, uuid);
        cookie.setMaxAge(24 * 3600 * 1000);
        response.addCookie(cookie);
        cacheService.set(uuid, JSON.toJSONString(sessionInfo), 60 * 1000);
    }
}