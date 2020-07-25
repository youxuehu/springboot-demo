package com.example.springbootdemo.utils;

public class TestHashIndex {
    public static void main(String[] args) {
        /**
         * 雨露均沾，分散性
         */
        Integer key = 17;
        int h;
        int n = 28;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        int i;
        int index = i = (n - 1) & hash;
        System.out.println(index);
    }
}
