package com.example.springbootdemo.daemon;

import org.springframework.stereotype.Service;

@Service
public class LeaderLaunch implements Runnable {

    @Override
    public void run() {
        // 选主， 负责分配任务给从节点
    }
}
