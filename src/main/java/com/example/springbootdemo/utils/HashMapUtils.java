package com.example.springbootdemo.utils;

import java.util.HashMap;

/**
 *
 */
public class HashMapUtils {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        /**
         * hashMap 没有给定大小，默认值16
         * 最大值是2的30次方
         */
        for (int i = 0; i < 1000000; i++) {
            /**
             * put方法
             * 1：根据key，计算hash值，从而计算数组的下标位置
             * 2：如果没有发生碰撞，直接存入table
             * 3：如果发生碰撞了，则以链表的形式存入table
             * 4：如果碰过次数过多导致链表过长，达到阈值的大小8 ， 则将链表转换成红黑树
             * 5：如果节点已经存在则直接替换该节点的value
             * 6：如果数组满了超过了阈值0.75 * size，则resize()
             * 7：如果链表长度由于删除节点，达到最小阈值6，则将红黑树转换成链表
             */
            hashMap.put(i, "tiger" + i);
        }

        hashMap.clear();
    }
}
