package com.example.springbootdemo.common.设计模式.建造者模式.demo.手机类.改造后;

import com.alibaba.fastjson.JSON;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/14 10:22 下午
 * @desrription 建造者模式：改造后可以链式调用创建收集对象，不在需要按照顺序设置参数了
 */
public class Client {

    public static void main(String[] args) {
        // 使用链式调用
        Phone phone = new Phone.Builder()
                .cpu("Intel")
                .mainBoard("华硕")
                .screen("金斯顿")
                .memory("16G")
                .build();
        System.out.println(phone);
        System.out.println(JSON.toJSONString(phone, true));
    }
}
