package org.example.google.guave.event;

public class PublishEventBus {

    private CommomEventBus commomEventBus;

    public void publishEvent(MyEventTask myEventTask) {
        commomEventBus.publish(myEventTask);
    }

    public void setCommomEventBus(CommomEventBus commomEventBus) {
        this.commomEventBus = commomEventBus;
    }

    public void init() {
        MyEventTask myEventTask = new MyEventTask();
        myEventTask.setTaskId(1L);
        publishEvent(myEventTask);
    }
}
