package com.example.springbootdemo.utils;

import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    public static Date getDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() - time);
        return calendar.getTime();
    }

    public static Date getEndDate(int time, TimeUnit timeUnit) {
        DateTime dateTime = DateTime.now();
        long millis;
        switch (timeUnit) {
            case DAYS:
                millis = dateTime.minusDays(time).toDateTime().getMillis();
                break;
            case HOURS:
                millis = dateTime.minusHours(time).toDateTime().getMillis();
                break;
            case MINUTES:
                millis = dateTime.minusMinutes(time).toDateTime().getMillis();
                break;
            case SECONDS:
                millis = dateTime.minusSeconds(time).toDateTime().getMillis();
                break;
            case MILLISECONDS:
                millis = dateTime.minusMillis(time).toDateTime().getMillis();
                break;
            default:
                throw new RuntimeException("no support time type");
        }
        return new Date(millis);
    }
}
