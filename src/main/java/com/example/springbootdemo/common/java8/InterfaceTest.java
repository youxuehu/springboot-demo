package com.example.springbootdemo.common.java8;

/**
 * 接口可以定义方法实现
 * 好处： 当一个接口多个实现类是，可以不实现这个方法，使用接口中默认的实现
 * 类似于抽象类
 */
public interface InterfaceTest {

    void operator();

    default double test(int sum) {
        return Math.sqrt(sum);
    }
}

class A implements InterfaceTest {

    @Override
    public void operator() {
        double test = test(123);
        System.out.println(test);
    }

    public static void main(String[] args) {
        InterfaceTest a = new A();
        a.operator();
    }
}
