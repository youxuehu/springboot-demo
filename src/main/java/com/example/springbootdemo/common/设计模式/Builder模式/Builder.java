package com.example.springbootdemo.common.设计模式.Builder模式;

public interface Builder {
    void setCpu();
    void setMemory();
    void setSize();
    void setBrand();
    Computer getComputer();
}
