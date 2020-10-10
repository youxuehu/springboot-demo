package com.example.sofarpcserver.service.impl;

import org.example.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("server receive " + name);
        return "hello, " + name;
    }
}
