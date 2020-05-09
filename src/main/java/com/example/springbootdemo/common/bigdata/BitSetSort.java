package com.example.springbootdemo.common.bigdata;

import java.util.BitSet;

public class BitSetSort {

    // BitSet元素排序
    public static void main(String[] args) {
        //4,7,2,5,3 元素不能是负数
        BitSet bs = new BitSet(8);
        int[] ia = {4, 7, 2, 5, 3};
        for (int i : ia) {
            bs.set(i, true);
        }
        System.out.println(bs);
        int size = bs.size();
        for (int j = 0; j < size; j++) {
            boolean b = bs.get(j);
            if (b) {
                System.out.print(j + " ");
            }
        }

    }
}
