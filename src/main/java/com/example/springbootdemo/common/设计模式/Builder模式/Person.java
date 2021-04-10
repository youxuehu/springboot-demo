package com.example.springbootdemo.common.设计模式.Builder模式;

public class Person {

    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void builder() {
        builder.setBrand();
        builder.setCpu();;
        builder.setMemory();;
        builder.setSize();
    }

    public Computer getComputer() {
        return builder.getComputer();
    }

}
