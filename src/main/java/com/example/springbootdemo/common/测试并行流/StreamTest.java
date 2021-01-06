package com.example.springbootdemo.common.测试并行流;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StreamTest {

    private static final Logger log = LoggerFactory.getLogger(StreamTest.class);

    public static void main(String[] args) throws InterruptedException {
        List<Apple> appleList = initAppleList();

        Date begin = new Date();
        for (Apple apple : appleList) {
            apple.setPrice(5.0 * apple.getWeight() / 1000);
            Thread.sleep(1000);
        }
        Date end = new Date();
        log.info("苹果数量：{}个, 耗时：{}s", appleList.size(), (end.getTime() - begin.getTime()) /1000);
        // 5248 [main] INFO  com.example.springbootdemo.common.测试并行流.StreamTest - 苹果数量：5个, 耗时：5s
    }

    private static List<Apple> initAppleList() {
        return Arrays.asList(new Apple(1.0,1),
                new Apple(2.0,2),
                new Apple(3.0,3),
                new Apple(4.0,4),
                new Apple(5.0,5));
    }
}
