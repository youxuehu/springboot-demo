package com.example.springbootdemo.controller;

import lombok.Getter;
import lombok.Setter;
import org.apache.spark.sql.catalyst.expressions.String2StringExpression$class;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@RestController
public class HelloController {

    static Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String index() {
        log.info("Hello Docker!");
        return "Hello Docker!";
    }

    @RequestMapping("/call")
    public String call() {
        String url = "http://www.baidu.com";
        String rest = restTemplate.getForObject(url, String.class);
        log.info("请求参数：" + url);
        log.info("返回结果：" + rest);
        return "Hello Docker!";
    }

    @RequestMapping("/k8s")
    public String k8s(HttpServletRequest request) {
        log.info("Hello k8s!");
        return "Hello k8s! URL:" + request.getRequestURL().toString();
    }

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
        log.info("map", map);
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
