package com.example.springbootdemo.common.list;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMainTest {

    public static void main(String[] args) {

        Map<String, String> map = new ConcurrentHashMap<String, String>(){{
            put("name", "jack");
            put("age", "19");
            put("sex", "男");
        }};
        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put("email", "jack@sina.com");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put("telephome", "12711129121");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                map.put("university", "南开大学");
            }
        }).start();

    }
}
