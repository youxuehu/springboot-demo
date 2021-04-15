package com.example.springbootdemo.common.设计模式.other.Builder模式;

public class HighComputerBuilder implements Builder {
    private Computer computer;

    public HighComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void setCpu() {
        computer.setCpu("16C");
    }

    @Override
    public void setMemory() {
        computer.setMemory("32G");
    }

    @Override
    public void setSize() {
        computer.setSize("27K");
    }

    @Override
    public void setBrand() {
        computer.setBrand("IMac");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
