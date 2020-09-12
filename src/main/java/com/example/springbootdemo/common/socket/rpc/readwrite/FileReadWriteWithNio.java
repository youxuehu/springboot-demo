package com.example.springbootdemo.common.socket.rpc.readwrite;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;


/**
 * nio 文件读写操作
 */
public class FileReadWriteWithNio {


    public static void main(String[] args) {



        String result = readFile("c:/Users/youxuehu/read.txt");
        System.out.println(result);
        List<String> nameList = Arrays.asList("zhansgan", "lisi", "wangwu", "zhaoliu");
        String content = JSON.toJSONString(nameList);
        while (true) {
            writeFile("c:/Users/youxuehu/read.txt", content + "\n");
        }

    }

    private static void writeFile(String path, String content) {

        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            FileChannel fileChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.wrap(content.getBytes());
            fileChannel.write(byteBuffer);
            fileChannel.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String readFile(String path) {
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read = fileChannel.read(byteBuffer);
            while (read != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    byteArrayOutputStream.write(b);
                }
                byteBuffer.clear();
                read = fileChannel.read(byteBuffer);

            }

            return byteArrayOutputStream.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
