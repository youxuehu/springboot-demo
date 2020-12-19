package com.example.springbootdemo.common.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);
    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        LOGGER.info("fixedRate>>>"+new Date());
    }
    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        LOGGER.info("fixedDelay>>>"+new Date());
    }
    @Scheduled(initialDelay = 2000,fixedDelay = 2000)
    public void initialDelay() {
        LOGGER.info("initialDelay>>>"+new Date());
    }
    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        LOGGER.info(new Date().toLocaleString());
    }
}
