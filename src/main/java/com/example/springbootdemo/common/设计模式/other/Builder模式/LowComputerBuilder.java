package com.example.springbootdemo.common.设计模式.other.Builder模式;

public class LowComputerBuilder implements Builder {
    private Computer computer;

    public LowComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void setCpu() {
        computer.setCpu("4C");
    }

    @Override
    public void setMemory() {
        computer.setMemory("8G");
    }

    @Override
    public void setSize() {
        computer.setSize("13K");
    }

    @Override
    public void setBrand() {
        computer.setBrand("Windows");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
