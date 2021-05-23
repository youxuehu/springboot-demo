package com.example.springbootdemo.handler;

public enum TaskTypeEnum {
    SHELL("SHELL"), HTTP("HTTP"), PYTHON("PYTHON"),
    SINGLETON("SINGLETON");


    String type;

    TaskTypeEnum(String type) {
        this.type = type;
    }
}
