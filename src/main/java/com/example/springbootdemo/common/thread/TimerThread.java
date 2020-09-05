package com.example.springbootdemo.common.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerThread {

    private static int counter = 0;

    public static void main(String[] args) {

        // jdk1.5之前的任务调度 Timer, 只支持单线程

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                add();
            }
        }, 1000, 1000);
    }

    public static void add() {
        System.out.println(++counter);
    }
}
