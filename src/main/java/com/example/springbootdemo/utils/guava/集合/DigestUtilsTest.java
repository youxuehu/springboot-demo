package com.example.springbootdemo.utils.guava.集合;

import org.apache.poi.util.TempFile;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsTest {


    //第一种实现
    public static void main(String[] args) {

        String string = "程序员";
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = string.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //第二种实现
    public void main2(String[] args) {
        String string = "程序员";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(string.getBytes());
            byte[] b = md5.digest();

            StringBuffer sb = new StringBuffer();
            for (int n = 0; n < b.length; n++) {
                int i = b[n];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            System.out.println(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
