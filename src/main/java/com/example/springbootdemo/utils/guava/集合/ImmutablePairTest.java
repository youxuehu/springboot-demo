package com.example.springbootdemo.utils.guava.集合;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;

public class ImmutablePairTest {

    public static void main(String[] args) {
        /**
         * 不可变左右对
         */
        ImmutablePair<Integer, Integer> im = ImmutablePair.of(1, 10);
        Integer left = im.getLeft();
        Integer right = im.getRight();
        System.out.println(left);
        System.out.println(right);

        // java.lang.UnsupportedOperationException
        // Integer value = im.setValue(20);
        // System.out.println(value);


        // 可变左右对
        /**
         * 可变左右对
         */

        MutablePair<Integer, Integer> mutablePair = MutablePair.of(1, 10);
        mutablePair.setLeft(10);
        mutablePair.setRight(100);
        System.out.println(mutablePair.getLeft());
        System.out.println(mutablePair.getRight());


        /**
         * 可变的左中右对
         */
        MutableTriple<Integer, Integer, Integer> mutableTriple = MutableTriple.of(1, 2, 3);
        mutableTriple.setLeft(10);
        mutableTriple.setMiddle(20);
        mutableTriple.setRight(30);
        System.out.println(mutableTriple.getLeft());
        System.out.println(mutableTriple.getMiddle());
        System.out.println(mutableTriple.getRight());

    }
}
