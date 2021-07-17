package com.example.springbootdemo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author youxuehu
 * @version v1.0
 * @className SpringBeanUtil
 * @date 2021/7/15 10:17 下午
 * @desrription 这是类的描述信息
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
