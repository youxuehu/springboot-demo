package com.example.springbootdemo.common.设计模式.other.Builder模式.app;

public class MessageThrow {

    public static void main(String[] args) {

        String systemError = ErrorContext.instance().errorCode("NO_PERMISSION").errorMessage("对不起，你没有权限操作！").toString();
        System.out.println(systemError);
        // ErrorContext可以看作是线程内部的单例模式
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    ErrorContext instance = ErrorContext.instance();
                    System.out.println(instance);
                }
            }.start();
        }
    }
}
