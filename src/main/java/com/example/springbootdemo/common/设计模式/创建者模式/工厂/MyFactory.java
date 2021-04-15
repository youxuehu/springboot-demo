package com.example.springbootdemo.common.设计模式.创建者模式.工厂;

/**
 * 工厂设计模式。主要用在创建对象的场景
 */
public class MyFactory {

    public static Object getInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        Object instance = clazz.newInstance();
        return instance;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        Object instance = getInstance("com.example.springbootdemo.common.设计模式.工厂.MyFactory");
        System.out.println(instance);
    }
}
