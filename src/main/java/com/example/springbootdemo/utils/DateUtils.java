package com.example.springbootdemo.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static void main(String[] args) {
        Date endTime = getDate(28 * 24 * 3600 * 1000L);
        String formatTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS").
                format(LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime.getTime()),
                        ZoneId.systemDefault()));
        System.out.println(formatTime);
    }

    /**
     * 获取指定日期前的时间
     * 比如说 28天前
     * @param time
     * @return
     */
    private static Date getDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() - time);
        return calendar.getTime();
    }
}
