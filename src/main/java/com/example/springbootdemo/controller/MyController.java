package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/model")
public class MyController extends BaseController {

    @ResponseBody
    @RequestMapping()
    public ModelMap model(HttpServletRequest request, HttpServletResponse response) {
        return success("name", "jack", "age", 20, "message", "I am jack");
    }

}
