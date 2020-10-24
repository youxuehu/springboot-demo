package com.example.springbootdemo.common.设计模式.策略.builder;

import com.example.springbootdemo.common.设计模式.策略.builder.proxy.JobBuilderProxyFactory;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractJobBuilder implements JobBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        JobBuilderProxyFactory.register(this);
    }

}
