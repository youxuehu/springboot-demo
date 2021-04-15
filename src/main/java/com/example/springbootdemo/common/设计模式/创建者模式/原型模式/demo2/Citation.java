package com.example.springbootdemo.common.设计模式.创建者模式.原型模式.demo2;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/13 9:54 下午
 * @desrription 奖状
 */
public class Citation implements Cloneable {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void show() {
        System.out.println(student.getName() + "获奖");
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }
}
