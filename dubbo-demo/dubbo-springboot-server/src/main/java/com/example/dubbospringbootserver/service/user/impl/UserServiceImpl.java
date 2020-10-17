package com.example.dubbospringbootserver.service.user.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.UserService;

@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public String sayHi(String name) {
        return "dubbo call sayHi, " + name;
    }
}
