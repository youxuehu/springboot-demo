package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.动态代理.cglib;


/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/17 11:37 上午
 * @desrription 这是类的描述信息
 */
public class Client {
    public static void main(String[] args) {
        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        TranAction proxyObject = proxyFactory.proxyObject();
        System.out.println(proxyObject.getClass());
        proxyObject.sell();
        while (true) {}
    }
}
