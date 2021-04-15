package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.静态代理;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/15 9:58 下午
 * @desrription 目标类
 */
public class TranAction implements SellTicket {

    @Override
    public void sell() {
        System.out.println("火车站售票");
    }
}
