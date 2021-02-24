package com.example.springbootdemo.utils.guava.集合;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;

public class SystemUtilsTest {

    public static void main(String[] args) {

        String hostName = SystemUtils.getHostName();
        File javaHome = SystemUtils.getJavaHome();
        File userDir = SystemUtils.getUserDir();
        File userHome = SystemUtils.getUserHome();

        System.out.println("hostName=" + hostName);
        System.out.println("javaHome=" + javaHome.getAbsolutePath());
        System.out.println("userDir=" + userDir.getAbsolutePath());
        System.out.println("userHome=" + userHome.getAbsolutePath());
    }
}
