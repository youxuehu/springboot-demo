package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.动态代理.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/17 11:31 上午
 * @desrription 这是类的描述信息
 */
public class CglibProxyFactory implements MethodInterceptor {

    private TranAction tranAction = new TranAction();

    public TranAction proxyObject() {
        Enhancer enhancer = new Enhancer();
        //设置父类的字节码对象
        enhancer.setSuperclass(TranAction.class);
        enhancer.setCallback(this);
        TranAction proxyObject = (TranAction) enhancer.create();
        return proxyObject;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib收取代理费");
        Object invoke = method.invoke(tranAction, args);
        return invoke;
    }
}
