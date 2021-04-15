package com.example.springbootdemo.common.设计模式.other.迭代器模式;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Iterator;
import java.util.Map;

public class 迭代器模式 implements Iterator<迭代器模式> {


    private String name;
    private String age;
    private String email;
    private String children;

    public 迭代器模式(String json) {
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        name = map.get("name");
        age = map.get("age");
        email = map.get("email");
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getChildren() {
        return children;
    }

    public static void main(String[] args) {
        迭代器模式 a = new 迭代器模式("{\"name\":\"jack\", \"age\":\"20\", \"email\":\"jack@123.com\"}");
        System.out.println(a.getName());
        System.out.println(a.getAge());
        System.out.println(a.getEmail());
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public 迭代器模式 next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
