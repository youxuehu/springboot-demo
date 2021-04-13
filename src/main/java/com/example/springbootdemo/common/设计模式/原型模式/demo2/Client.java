package com.example.springbootdemo.common.设计模式.原型模式.demo2;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/13 9:56 下午
 * @desrription 原型模式：包含深克隆和浅克隆
 */
public class Client {

    /**
     * 浅克隆：Student原对象和新克隆Student的对象地址形同
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {

        Citation citation = new Citation();
        Student student = new Student();
        student.setName("张三");
        citation.setStudent(student);

        Citation citation1 = citation.clone();
        citation1.getStudent().setName("李四");
        citation.show();
        citation1.show();
        //李四获奖
        //李四获奖
    }
}
