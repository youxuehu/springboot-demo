package com.example.springbootdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    public static void main(String[] args) {
        String data = "<<<<2020-11-20 20:46:10 123>>>>>";

        String regex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} \\d{3}";
        matchStr(regex, data);
    }

    private static void matchStr(String regex, String data) {

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
        }
    }
}
