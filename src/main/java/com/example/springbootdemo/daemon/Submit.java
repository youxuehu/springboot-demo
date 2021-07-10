package com.example.springbootdemo.daemon;

import java.io.Serializable;

public class Submit implements Serializable {

    private Job job;

    private boolean isStop;

    public Submit(Job job, boolean isStop) {
        this.job = job;
        this.isStop = isStop;
    }

    public Submit() {
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
