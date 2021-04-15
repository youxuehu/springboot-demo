package com.example.springbootdemo.common.设计模式.创建者模式.原型模式.demo1;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/13 9:54 下午
 * @desrription 奖状
 */
public class Citation implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(name + "获奖");
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }
}
