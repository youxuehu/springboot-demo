package com.example.springbootdemo.handler;

public enum TaskTypeEnum {

    SINGLETON("SINGLETON");

    String type;

    TaskTypeEnum(String type) {
        this.type = type;
    }
}
