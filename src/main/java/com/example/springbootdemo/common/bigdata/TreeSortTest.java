package com.example.springbootdemo.common.bigdata;

import java.util.TreeSet;

public class TreeSortTest {

    // TreeSet 元素排序
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        int[] ia = {4, 7, 2, 5, 3, -1};
        for (int i : ia) {
            ts.add(i);
        }
        System.out.println(ts);
    }
}
