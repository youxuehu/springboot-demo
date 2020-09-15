package org.example.google.guave.event;

import com.google.common.eventbus.Subscribe;

public class EventBusRegister {

    private CommomEventBus commomEventBus;

    public void init() {
        commomEventBus.register(this);
    }

    public void setCommomEventBus(CommomEventBus commomEventBus) {
        this.commomEventBus = commomEventBus;
    }

    @Subscribe
    public void processEvent(MyEventTask myEventTask) {
        System.out.println("<<<<<<<<<<<<<<< 订阅事件开始执行 >>>>>>>>>>>>>>>>>> "+myEventTask);
    }
}
