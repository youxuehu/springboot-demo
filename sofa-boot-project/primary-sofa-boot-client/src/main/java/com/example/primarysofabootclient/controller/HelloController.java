package com.example.primarysofabootclient.controller;

import org.example.service.HelloSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloSyncService helloSyncService;

    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return helloSyncService.sayHello(name);
    }
}
