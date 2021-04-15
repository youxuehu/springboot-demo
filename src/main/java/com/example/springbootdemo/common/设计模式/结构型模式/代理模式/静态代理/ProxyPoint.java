package com.example.springbootdemo.common.设计模式.结构型模式.代理模式.静态代理;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/15 9:59 下午
 * @desrription 代理类
 */
public class ProxyPoint implements SellTicket {

    private TranAction tranAction = new TranAction();

    @Override
    public void sell() {
        System.out.println("代售点收取一定的服务费");
        tranAction.sell();
    }
}
