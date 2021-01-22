package com.example.springbootdemo.manager.res;

import java.util.Date;

public class ResultLog {
    private Date gmtCreate;
    private String content;
    private String jobId;

    public ResultLog(Date gmtCreate, String content, String jobId) {
        this.gmtCreate = gmtCreate;
        this.content = content;
        this.jobId = jobId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "ResultLog{" +
                "gmtCreate=" + gmtCreate +
                ", content='" + content + '\'' +
                ", jobId='" + jobId + '\'' +
                '}';
    }
}
