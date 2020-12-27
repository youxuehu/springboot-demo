package com.example.springbootdemo.utils;

import org.apache.commons.lang.StringUtils;

public class UserNumberUtil {

    // 不足6位数前面补0
    public static String formatUserNumber(String userNumber) {
        if (StringUtils.isBlank(userNumber)) {
            return userNumber;
        }
        if (StringUtils.isNumeric(userNumber)) {
            int userNo = Integer.parseInt(userNumber);
            userNumber = String.format("%06d", userNo);
        }
        return userNumber;
    }

    public static void main(String[] args) {
        String userNumber = formatUserNumber("WB520289");
        System.out.println(userNumber);
        userNumber = formatUserNumber("123456");
        System.out.println(userNumber);
        userNumber = formatUserNumber("123");
        System.out.println(userNumber);
    }
}
