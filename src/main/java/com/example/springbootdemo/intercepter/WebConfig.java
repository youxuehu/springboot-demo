package com.example.springbootdemo.intercepter;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{

    @Autowired
    UserArgsResolver userArgsResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgsResolver);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

}
