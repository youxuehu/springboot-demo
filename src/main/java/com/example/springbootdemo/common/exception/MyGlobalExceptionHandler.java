package com.example.springbootdemo.common.exception;

import com.alibaba.fastjson.JSON;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView customException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("errorMessage", JSON.toJSONString(e, true));
        modelAndView.setViewName("myerror");
        return modelAndView;
    }

    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }
}
