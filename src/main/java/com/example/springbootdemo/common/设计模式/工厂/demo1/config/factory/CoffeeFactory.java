package com.example.springbootdemo.common.设计模式.工厂.demo1.config.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/12 10:38 下午
 * @desrription 这是类的描述信息
 */
public class CoffeeFactory {

    private static final HashMap<String, Coffee> COFFEE_MAP = new HashMap<>();

    // 记载配置文件
    static {
        Properties properties = new Properties();
        InputStream is = CoffeeFactory.class.getClassLoader().getResourceAsStream("coffee.properties");
        try {
            properties.load(is);
            for (Object key : properties.keySet()) {
                String className = properties.getProperty((String) key);
                Class clazz = Class.forName(className);
                Coffee coffee = (Coffee) clazz.newInstance();
                COFFEE_MAP.put((String) key, coffee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Coffee createCoffee(String name) {
        return COFFEE_MAP.get(name);
    }
}
