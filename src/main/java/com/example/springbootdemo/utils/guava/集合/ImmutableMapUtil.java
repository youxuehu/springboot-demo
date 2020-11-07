package com.example.springbootdemo.utils.guava.集合;

import com.google.common.collect.ImmutableMap;

public class ImmutableMapUtil {
    /**
     * 定义不可变的map常量
     */
    static final ImmutableMap<String, String> map1 = new ImmutableMap.Builder<String, String>()
            .put("name", "jack")
            .put("age", "20")
            .build();
    static final ImmutableMap<String, String> map2 = new ImmutableMap.Builder<String, String>()
            .put("name", "jack")
            .put("age", "20")
            .build();
    static final ImmutableMap<String, String> map3 = new ImmutableMap.Builder<String, String>()
            .put("name", "jack")
            .put("age", "20")
            .build();
    public static void main(String[] args) {

        String name = map1.get("name");
        System.out.println(name);
    }
}
