package com.example.springbootdemo.common.builder;

import com.example.springbootdemo.common.builder.proxy.JobBuilderProxyFactory;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractJobBuilder implements JobBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        JobBuilderProxyFactory.register(this);
    }

}
