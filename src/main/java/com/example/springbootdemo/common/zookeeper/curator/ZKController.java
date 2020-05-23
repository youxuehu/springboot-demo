package com.example.springbootdemo.common.zookeeper.curator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zk")
public class ZKController {

    @Autowired
    private ZookeeperLock zklock;

    @GetMapping("/lock")
    public Boolean getLock() throws Exception{

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    zklock.lock();
                    zklock.unlock();
                }
            }).start();
        } return true;
    }
}