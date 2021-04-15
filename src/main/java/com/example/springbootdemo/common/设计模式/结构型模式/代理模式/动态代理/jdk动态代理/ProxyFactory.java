package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.动态代理.jdk动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/15 10:02 下午
 * @desrription JDK动态代理工厂：用于生成代理对象
 */
public class ProxyFactory {

    private TranAction tranAction = new TranAction();

    public SellTicket proxy() {
        SellTicket sellTicket = (SellTicket) Proxy.newProxyInstance(
                tranAction.getClass().getClassLoader(),
                tranAction.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("火车票代售点收取一点手续费（JDK动态代理）");
                        Object invoke = method.invoke(tranAction, args);
                        return invoke;
                    }
                }
        );
        return sellTicket;
    }
}
