package com.example.primarysofaboot.service.impl;

import org.example.service.HelloSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloSyncServiceImpl implements HelloSyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloSyncServiceImpl.class);

    @Override
    public String sayHello(String name) {
        LOGGER.info("接收参数：" + name);
        return "hello, " + name;
    }
}
