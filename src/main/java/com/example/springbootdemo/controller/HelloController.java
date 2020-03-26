package com.example.springbootdemo.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class HelloController {

    @RequestMapping("/get")
    public String get() {
        return "Hello SpringBoot";
    }

    @RequestMapping("/hello")
    public String hello() {
        // 非空判断
        UserDemo userDemo = new UserDemo();
        userDemo.setName("吕水涵");
        Optional.ofNullable(userDemo).orElseThrow(()->new RuntimeException("用户不存在～"));
        Optional.ofNullable(userDemo).map(u -> u.getName()).orElseThrow(()->new RuntimeException("用户名不能为null~"));
        Optional.ofNullable(userDemo).map(u -> u.getMessage()).orElseThrow(()->new RuntimeException("消息不能为null~"));
        return "Hello SpringBoot";
    }


    @GetMapping("/hello2")
    public String hello(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;
        return "hello controller advice";
    }

}

@Setter
@Getter
class UserDemo {
    private String name;
    private String message;

}
