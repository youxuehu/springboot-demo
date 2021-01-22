package com.example.springbootdemo.manager;

import com.example.springbootdemo.manager.impl.MsgSender;

public class ExecutionContext {

    private MsgSender msgSender;

    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public MsgSender getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(MsgSender msgSender) {
        this.msgSender = msgSender;
    }
}
