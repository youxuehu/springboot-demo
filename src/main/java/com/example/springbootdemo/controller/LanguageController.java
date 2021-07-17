package com.example.springbootdemo.controller;

import com.example.springbootdemo.utils.I18nMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.common.utils.constant.I18nConstant.SESSION_LANG_KEY;

@Controller
public class LanguageController {

    @Autowired
    I18nMessageUtil i18nMessageUtil;

    @RequestMapping("/lang")
    @ResponseBody
    public String lang(String lang) {
        String message = i18nMessageUtil.getMessage("welcome");
        return message;
    }

    @RequestMapping("/changeLang")
    @ResponseBody
    public String changeLang(HttpSession session, String lang) {
        session.setAttribute(SESSION_LANG_KEY, lang);
        return session.getAttribute(SESSION_LANG_KEY) == null ? "error" : (String) session.getAttribute(SESSION_LANG_KEY);
    }

}
