package com.example.springbootdemo.common.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleService {
    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate>>>"+new Date());
    }
    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay>>>"+new Date());
    }
    @Scheduled(initialDelay = 2000,fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay>>>"+new Date());
    }
}
