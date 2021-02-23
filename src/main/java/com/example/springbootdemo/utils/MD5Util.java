package com.example.springbootdemo.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加盐加密
 */
public class MD5Util {
    private static final String SALT = "youxuehu";
    public static String md5(String input) {
        return DigestUtils.md5Hex(input);
    }

    public static String addSalt(String input) {
        String output = "" + SALT.substring(0, 2) + input + SALT.charAt(6) + SALT.charAt(4);
        return md5(output);
    }

    public static String addSalt(String input, String salt) {
        String output = "" + salt.substring(0, 2) + input + salt.charAt(6) + salt.charAt(4);
        return md5(output);
    }

    public static String fromInputToOutput(String input, String saltInDB) {
        String str = addSalt(input);
        return addSalt(str, saltInDB);
    }

    public static void main(String[] args) {
        String output = addSalt("123456");
        System.out.println(output);
    }
}
