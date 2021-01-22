package com.example.springbootdemo.handler;

import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractJobHandler implements JobHandler, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        JobHandlerFactory.register(this);
    }
}
