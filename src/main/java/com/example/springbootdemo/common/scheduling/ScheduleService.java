package com.example.springbootdemo.common.scheduling;

import com.example.common.db.service.dbsavefront.DbSaveFrontService;
import com.example.common.utils.enums.SwitchEnum;
import com.example.common.utils.time.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

import static com.example.common.utils.constant.CommonConst.SCHEDULE_SWITCH;

@Component
public class ScheduleService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    DbSaveFrontService dbSaveFrontService;

    private boolean isOpen() {
        return SwitchEnum.OPEN == SwitchEnum.valueOf(dbSaveFrontService.queryValueByKeyString(SCHEDULE_SWITCH));
    }

    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        if (isOpen()) {
            LOGGER.info("fixedRate>>>" + new Date());
        }
    }
    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        if (isOpen()) {
            LOGGER.info("fixedDelay>>>" + new Date());
        }
    }
    @Scheduled(initialDelay = 2000,fixedDelay = 2000)
    public void initialDelay() {
        if (isOpen()) {
            LOGGER.info("initialDelay>>>" + new Date());
        }
    }
    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        if (isOpen()) {
            LOGGER.info(TimeUtils.currentTime());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dbSaveFrontService.insertOrUpdate(SCHEDULE_SWITCH, SwitchEnum.CLOSE.name());
    }
}
