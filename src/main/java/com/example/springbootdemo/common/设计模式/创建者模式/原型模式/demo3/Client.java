package com.example.springbootdemo.common.设计模式.创建者模式.原型模式.demo3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/13 9:56 下午
 * @desrription 原型模式：包含深克隆和浅克隆
 */
public class Client {

    /**
     * 深克隆：使用对象流
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {

        Citation citation = new Citation();
        Student student = new Student();
        student.setName("张三");
        citation.setStudent(student);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/youxuehu/Desktop/clone.txt"));
        oos.writeObject(citation);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/youxuehu/Desktop/clone.txt"));
        Citation citation1 = (Citation) ois.readObject();
        citation1.getStudent().setName("李四");
        citation.show();
        citation1.show();

        //张三获奖
        //李四获奖
    }
}
