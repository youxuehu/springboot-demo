package com.example.springbootdemo.common.设计模式.结构型模式.外观模式;


import org.apache.commons.lang3.StringUtils;

/**
 * @author youxuehu
 * @version v1.0
 * @className SmartAppliancesFacade
 * @date 2021/5/9 8:59 下午
 * @desrription 外观模式
 */
public class SmartAppliancesFacade {

    private Light light;

    private TV tv;

    private AirCondition airCondition;

    public SmartAppliancesFacade() {
        light = new Light();
        tv = new TV();
        airCondition = new AirCondition();
    }

    public void say(String message) {
        if (StringUtils.contains(message, "打开")) {
            on();
        } else if (StringUtils.contains(message, "关闭")) {
            off();
        } else {
            System.out.println("我没听懂你说的～");
        }
    }

    private void on() {
        light.on();
        tv.on();
        airCondition.on();
    }

    private void off() {
        light.off();
        tv.off();
        airCondition.off();
    }
}
