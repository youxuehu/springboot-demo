package com.example.dubbospringbootclient.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @DubboReference
    private UserService userService;

    @RequestMapping("queryUser")
    public String queryUser(@RequestParam(value = "name") String name) {
        String response = userService.sayHi(name);
        System.out.println(response);
        return response;
    }
}
