package com.example.springbootdemo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static com.example.springbootdemo.utils.CommonConst.SESSION_KET;

@Controller
public class LoginController extends BaseController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/jobManager")
    public String jobManager() {
        return "JobManager";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public ModelMap doLogin(HttpServletRequest request, HttpServletResponse response,  String userName, String password) {
        if (StringUtils.isBlank(userName)) {
            return error("errorMessage", "userName不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return error("errorMessage", "password不能为空");
        }
        if (!StringUtils.equals("admin", userName) || !StringUtils.equals("123456", password)) {
            return error("errorMessage", "用户名或密码不正确");
        }
        request.setAttribute(SESSION_KET, UUID.randomUUID().toString());
        Cookie cookie = new Cookie(SESSION_KET, UUID.randomUUID().toString());
        cookie.setPath("/");
        cookie.setDomain("127.0.0.1");
        cookie.setMaxAge(24 * 3600 * 1000);
        response.addCookie(cookie);
        return success("success", true);
    }
}
