package com.example.springbootdemo.common.设计模式.other.Builder模式;

public class Starter {

    public static void main(String[] args) {

        Person person = new Person();
        person.setBuilder(new LowComputerBuilder());
        person.builder();
        Computer win = person.getComputer();
        System.out.println(win);

        person.setBuilder(new HighComputerBuilder());
        person.builder();
        Computer mac = person.getComputer();
        System.out.println(mac);
    }
}
