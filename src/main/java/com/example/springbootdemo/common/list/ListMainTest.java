package com.example.springbootdemo.common.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMainTest {

    public static void main(String[] args) {

        // 在进行List元素修改时不要使用 java.util.Arrays ， 不支持

        // CopyOnWriteArrayList 解决多线程下修改元素, 注意： 1： 不能使用iterator.remove; 2: 使用Iterator进行List.remove会抛异常
        // CopyOnWriteArrayList在remove方法中使用ReentrantLock，实现线程安全lock unlock
        List<String> strings = new CopyOnWriteArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        // 调用List.remove()时，删除最后一个元素时，会抛 ConcurrentModificationException
//        for (String val : strings) {
//            if ("b".equals(val)) {
//                strings.remove(val);
//            }
//        }

        // 在单线程环境下，可以使用iterator.remove， 避免异常
//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            if ("c".equals(next)) {
//                iterator.remove();
//            }
//        }

        // 在多线程环境下， 推荐使用CopyOnWriteArrayList
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (String val : strings) {
                    if ("c".equals(val)) {
                        strings.remove(val);
                    }
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(strings);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                strings.stream().forEach(val -> {
                    System.out.println(val);
                });
            }
        }).start();



    }
}
