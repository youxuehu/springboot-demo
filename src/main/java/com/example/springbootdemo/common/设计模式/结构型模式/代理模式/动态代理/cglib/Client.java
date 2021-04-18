package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.动态代理.cglib;


/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/17 11:37 上午
 * @desrription
 *              使用cglib实现动态代理，底层采用ASM字节码生成框架，使用字节码生成技术生成代理类，在JDK1.6
 *              之前比使用Java反射效率要高，唯一需要注意的是，cglib不能对生申明为final的类或者方法进行代理，
 *              因为cglib原理是动态生成被代理类的子类。
 *              在JDK1.6、JDK1.7、JDK1.8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高
 *              于cglib代理，只有当进行大量调用的时候，JDK1。7 JDK1。7比cglib代理效率低一点，但是在JDK1。8的
 *              时候，JDK代理效率高于cglib代理。所以如果有接口使用JDK动态代理，如果没有接口使用cglib代理。
 */
public class Client {
    public static void main(String[] args) {
        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        TranAction proxyObject = proxyFactory.proxyObject();
        System.out.println(proxyObject.getClass());
        proxyObject.sell();
        while (true) {}
    }
}
