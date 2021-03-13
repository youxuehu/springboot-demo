package com.example.springbootdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则表达式 {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("[A-Za-z0-9.%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{3,4}");
        Matcher matcher = pattern.matcher("youxuehu@hotmail.com");
        boolean matches = matcher.matches();
        System.out.println(matches);
    }
}
