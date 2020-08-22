package com.example.springbootdemo.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
public class JobIdGenerator {
    static int counter = 0;
    public static synchronized String generateJobId() {
        ++counter;
        if (counter > 999) {
            counter = 1;
        }
        String jobCountStr = String.format("%3d" , counter).
                replace(' ', '0');
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date()) + jobCountStr;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(generateJobId());
            try {
                Thread.sleep(0L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
