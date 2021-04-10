package com.example.springbootdemo.common.设计模式.Builder模式.app;

public class MessageThrow {

    public static void main(String[] args) {

        String systemError = ErrorContext.instance().errorCode("NO_PERMISSION").errorMessage("对不起，你没有权限操作！").toString();
        System.out.println(systemError);
    }
}
