package com.example.springbootdemo.common.设计模式.单例.demo8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11
 * @desrription 破坏单例： 序例化和反序例化会破坏单例
 *              解决方案： 在单例类中定义readResolve
 */
public class SingletonTest {

    public static void main(String[] args) throws Exception {
        writeObject2File();
        readObjectFromFile();
        readObjectFromFile();
    }

    /**
     * 读对象
     */
    public static void readObjectFromFile() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/youxuehu/Desktop/a.txt"));
        Singleton singleton = (Singleton) ois.readObject();
        System.out.println(singleton);
        ois.close();
    }

    /**
     * 写对象
     */
    public static void writeObject2File() throws Exception {
        Singleton instance = Singleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/youxuehu/Desktop/a.txt"));
        oos.writeObject(instance);
        oos.close();
    }
}
