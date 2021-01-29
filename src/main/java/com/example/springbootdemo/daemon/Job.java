package com.example.springbootdemo.daemon;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {

    private String appName;

    private String env;

    private String userName;

    private String jobId;

    private String content;

    private String workerIp;

    private JobStatus jobStatus;

    private Date gmtSubmitted;

    private Date gmtSchedule;

    private Date gmtFinished;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWorkerIp() {
        return workerIp;
    }

    public void setWorkerIp(String workerIp) {
        this.workerIp = workerIp;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getGmtSubmitted() {
        return gmtSubmitted;
    }

    public void setGmtSubmitted(Date gmtSubmitted) {
        this.gmtSubmitted = gmtSubmitted;
    }

    public Date getGmtSchedule() {
        return gmtSchedule;
    }

    public void setGmtSchedule(Date gmtSchedule) {
        this.gmtSchedule = gmtSchedule;
    }

    public Date getGmtFinished() {
        return gmtFinished;
    }

    public void setGmtFinished(Date gmtFinished) {
        this.gmtFinished = gmtFinished;
    }

    @Override
    public String toString() {
        return "Job{" +
                "appName='" + appName + '\'' +
                ", env='" + env + '\'' +
                ", userName='" + userName + '\'' +
                ", jobId='" + jobId + '\'' +
                ", content='" + content + '\'' +
                ", workerIp='" + workerIp + '\'' +
                ", jobStatus=" + jobStatus +
                ", gmtSubmitted=" + gmtSubmitted +
                ", gmtSchedule=" + gmtSchedule +
                ", gmtFinished=" + gmtFinished +
                '}';
    }
}
