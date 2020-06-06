package com.example.springbootdemo.utils.mapwordcount;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapWordCount {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream(
                    new File("C:\\Users\\tiger\\IdeaProjects\\springboot-demo\\src\\main\\java\\com\\example\\" +
                            "springbootdemo\\utils\\mapwordcount\\wordcount.txt"));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            int len;
            byte[] buffer = new byte[1024];
            Map<String, Integer> map = new ConcurrentHashMap<>();
            while((len = bufferedInputStream.read(buffer)) != -1) {
//                System.out.println(new String(buffer, 0, len));
                String line = new String(buffer, 0, len);
                String[] cells = line.split(" ");
                for (String cell : cells) {
                    if (map.containsKey(cell)) {
                        map.put(cell, map.get(cell) + 1);
                    } else {
                        map.put(cell, 1);
                    }
                }
            }
//            System.out.println(map);
            printReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\tiger\\IdeaProjects\\springboot-demo\\src\\main\\java\\com\\example\\" +
                    "springbootdemo\\utils\\mapwordcount\\wordcount.txt"));
            String line;
            Map<String, Integer> map = new ConcurrentHashMap<>();
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(" ");
                for (String cell : cells) {
                    if (map.containsKey(cell)) {
                        map.put(cell, map.get(cell) + 1);
                    } else {
                        map.put(cell, 1);
                    }
                }
            }
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
