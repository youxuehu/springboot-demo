package com.example.springbootdemo.common.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerThread {

    private static int counter = 0;

    public static void main(String[] args) {

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
