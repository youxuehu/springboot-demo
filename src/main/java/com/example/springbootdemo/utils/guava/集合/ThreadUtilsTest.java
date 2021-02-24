package com.example.springbootdemo.utils.guava.集合;

import org.apache.commons.lang3.ThreadUtils;

import java.util.Collection;

public class ThreadUtilsTest {

    public static void main(String[] args) {

        Collection<Thread> allThreads = ThreadUtils.getAllThreads();
        allThreads.stream().forEach(thread -> {
            System.out.println(thread);
        });
    }
}
