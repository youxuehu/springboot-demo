package com.example.springbootdemo.utils.time;
import java.time.LocalDateTime;

public class TimeUtils {

    public static String currentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 年月日时分秒纳秒
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        int nano = localDateTime.getNano();
        return "" + year +"-" + monthValue + "-" + dayOfMonth + " " + hour + ":" + minute + ":" + second + ":" + nano;
    }

    public static String currentTimeTrnn() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 年月日时分秒纳秒
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int dayOfMonth = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        int nano = localDateTime.getNano();
        return "" + year + monthValue + dayOfMonth + hour + minute + second + nano;
    }
}
