package com.example.springbootdemo.utils.guava.集合;

import org.apache.poi.util.TempFile;

import java.io.File;
import java.io.IOException;


/**
 * 临时目录
 */
public class TempFileTest {

    public static void main(String[] args) {

        try {
            File abc = TempFile.createTempDirectory("abc");
            String absolutePath = abc.getAbsolutePath();
            System.out.println(absolutePath);
            // /var/folders/fy/cbbn3gc137193c1bj7h9wg_c0000gn/T/poifiles/abc1435750607207049665
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            File tempFile = TempFile.createTempFile("1111223213213123213123321", "b");
            String absolutePath = tempFile.getAbsolutePath();
            System.out.println(absolutePath);
            // /var/folders/fy/cbbn3gc137193c1bj7h9wg_c0000gn/T/poifiles/11112232132131232131233215583444190970644951b
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
