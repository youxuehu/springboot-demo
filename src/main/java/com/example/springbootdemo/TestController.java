package com.example.springbootdemo;

import com.example.springbootdemo.utils.http.HttpUtils;

public class TestController {

    public static void main(String[] args) throws Exception {
        while (true) {
            Thread.sleep(100);
            HttpUtils.doGet("http://master7/get");
        }

    }
}
