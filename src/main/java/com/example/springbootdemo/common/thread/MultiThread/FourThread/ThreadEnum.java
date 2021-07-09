package com.example.springbootdemo.common.thread.MultiThread.FourThread;

/**
 * @author youxuehu
 * @version v1.0
 * @className ThreadEnum
 * @date 2021/7/9 10:15 下午
 * @desrription 这是类的描述信息
 */
public enum ThreadEnum {
    THREAD1("thread1"),
    THREAD2("thread2"),
    THREAD3("thread3"),
    THREAD4("thread4");

    private String code;

    ThreadEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
