package com.example.springbootdemo.utils;

import java.util.List;
import java.util.concurrent.Callable;
public class OperaterRetryHelper {

    public static <T> T retry(Callable<T> task, List<Class<? extends Exception>> exceptions, int retryTimes, int time) {
        Exception ex = null;
        int count = 0;
        while (true) {
            try {
                return task.call();
            } catch (Exception e) {
                count++;
                ex = e;
                if (exceptions == null && retryTimes > 0) {
                    if (count <= retryTimes) {
                        System.out.println("执行失败，重试第 " + count + " 次");
                        if (time > 0) {
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException interruptedException) {
                                //
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (ex != null) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public static void main(String[] args) {
        String val = retry(() -> {
//            int i = 1 / 0;
            return "aaa";
        }, null, 1, 5000);
        System.out.println(val);
    }
}
