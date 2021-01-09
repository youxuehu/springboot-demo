package com.example.springbootdemo.controller;

import com.example.springbootdemo.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Locale;

@Controller
@RequestMapping("/lang")
public class LanguageController {

    @Autowired
    SpringBeanUtil springBeanUtil;

    @RequestMapping
    @ResponseBody
    public String lang(String lang) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = springBeanUtil.getContext().getMessage("welcome", null, null, locale);
        return message;
    }
}
