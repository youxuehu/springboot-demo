package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.动态代理.jdk动态代理;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/15 10:06 下午
 * @desrription 这是类的描述信息
 */
public class Client {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        SellTicket proxyObject = proxyFactory.proxy();
        proxyObject.sell();
        System.out.println(proxyObject.getClass());
        while (true) {}
    }
}
