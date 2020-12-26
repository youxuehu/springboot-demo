package com.example.springbootdemo.holder;

import com.example.springbootdemo.controller.userinfos.param.SessionInfo;

public class ThreadLocalHolder {

    private static ThreadLocal<SessionInfo> threadLocal = new ThreadLocal<>();

    public static void set(SessionInfo sessionInfo) {
        threadLocal.set(sessionInfo);
    }

    public static SessionInfo get() {
        return threadLocal.get();
    }

    public static void clean() {
        threadLocal.remove();
    }
}
