package com.example.springbootdemo.common.db.helper;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("(execution(* com.example.springbootdemo.common.db.service..*.select*(..))) || (execution(* com.example.springbootdemo.common.db.service..*.query*(..))) || (execution(* com.example.springbootdemo.common.db.service..*.get*(..)))")
    public void readPointcut() {
    }

    @Pointcut("(execution(* com.example.springbootdemo.common.db.service..*.insert*(..))) || (execution(* com.example.springbootdemo.common.db.service..*.add*(..))) || (execution(* com.example.springbootdemo.common.db.service..*.update*(..)))  || (execution(* com.example.springbootdemo.common.db.service..*.edit*(..))) || (execution(* com.example.springbootdemo.common.db.service..*.delete*(..))) || (execution(* com.example.springbootdemo.common.db.service..*.remove*(..)))")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.setSLAVE();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.setMaster7();
    }
}
