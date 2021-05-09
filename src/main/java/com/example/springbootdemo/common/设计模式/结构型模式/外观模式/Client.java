package com.example.springbootdemo.common.设计模式.结构型模式.外观模式;

/**
 * @author youxuehu
 * @version v1.0
 * @className Client
 * @date 2021/5/9 9:04 下午
 * @desrription 客户端
 */
public class Client {

    public static void main(String[] args) {

        SmartAppliancesFacade facade = new SmartAppliancesFacade();

        facade.say("打开");
        System.out.println("======================");
        facade.say("关闭");
    }
}
