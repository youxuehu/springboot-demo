package com.example.springbootdemo.common.设计模式.原型模式.demo1;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/13 9:56 下午
 * @desrription 原型模式：包含深克隆和浅克隆
 */
public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {

        Citation citation = new Citation();
        citation.setName("张三");
        Citation citation1 = citation.clone();
        citation1.setName("李四");
        citation.show();
        citation1.show();
    }
}
