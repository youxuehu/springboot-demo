package com.example.springbootdemo.common.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class MyJob2 extends QuartzJobBean {
    HelloService helloService;
    public HelloService getHelloService() {
        return helloService;
    }
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        helloService.sayHello();
    }
}
class HelloService {
    public void sayHello() {
        System.out.println("hello service >>>"+new Date());
    }
}