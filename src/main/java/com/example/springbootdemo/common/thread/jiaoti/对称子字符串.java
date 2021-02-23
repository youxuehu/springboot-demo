package com.example.springbootdemo.common.thread.jiaoti;

public class 对称子字符串 {

    public static void main(String[] args) {
        System.out.println(sliver("12221"));
    }

    private static String sliver(String abc) {
        if (abc == null || abc.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < abc.length(); i++) {
            int len1 = expendAroundCenter(abc, i, i);
            int len2 = expendAroundCenter(abc, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) /2;
                end = i + len /2;
            }
        }
        return abc.substring(start, end + 1);
    }

    private static int expendAroundCenter(String abc, int left, int right) {
        while (left >= 0 && right < abc.length() && abc.charAt(left) == abc.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
