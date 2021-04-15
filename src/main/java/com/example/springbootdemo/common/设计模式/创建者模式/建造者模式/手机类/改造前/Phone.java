package com.example.springbootdemo.common.设计模式.创建者模式.建造者模式.手机类.改造前;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:20 下午
 * @desrription 这是类的描述信息
 */
public class Phone {

    private String cpu;
    private String memory;
    private String screen;
    private String mainBoard;

    public Phone(String cpu, String memory, String screen, String mainBoard) {
        this.cpu = cpu;
        this.memory = memory;
        this.screen = screen;
        this.mainBoard = mainBoard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String mainBoard) {
        this.mainBoard = mainBoard;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", screen='" + screen + '\'' +
                ", mainBoard='" + mainBoard + '\'' +
                '}';
    }
}
