package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author youxuehu
 * @version v1.0
 * @className ThreadPoolBean
 * @date 2021/7/17 7:26 下午
 * @desrription 这是类的描述信息
 */

@Configuration
public class ThreadPoolBean {

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        return new ScheduledThreadPoolExecutor(10);
    }
}
