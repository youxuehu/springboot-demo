package com.example.springbootdemo.common.设计模式.单例.demo10;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author youxuehu
 * @version v1.0
 * @date 2021/4/11 10:31 下午
 * @desrription 这是类的描述信息
 */
public class RuntimeTest {

    public static void main(String[] args) throws Exception {

        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ifconfig");
        InputStream inputStream = process.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

//        char[] chars = new char[1024 * 1024 * 10];
//        int length;
//        while ((length = inputStreamReader.read(chars)) != -1) {
//            System.out.println(new String(chars, 0, length));
//        }
        System.out.println("###############################");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
