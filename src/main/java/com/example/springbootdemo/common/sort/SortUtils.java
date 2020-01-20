package com.example.springbootdemo.common.sort;

public class SortUtils {

    public static void popo(int[] arrs) {
        for (int i = 0 ; i < arrs.length; i++) {
            for (int j = 0; j < arrs.length - 1; j++) {
                if (arrs[j] < arrs[j + 1]) {
                    int temp = arrs[j + 1];
                    arrs[j + 1] = arrs[j];
                    arrs[j] = temp;
                }
            }
        }
    }

    public static void quick(int begin, int end, int[] arrs) {
        int i = begin;
        int j = end;
        if (i >= j) {
            return;
        }
        boolean flag = true;
        while(i != j) {
            if (arrs[i] < arrs[j]) {
                int temp = arrs[i];
                arrs[i] = arrs[j];
                arrs[j] = temp;
                flag = !flag;
            }
            if (flag) {
                j--;
            } else {
                i++;
            }
        }
        j++;
        i--;
        quick(begin, i, arrs);
        quick(j, end, arrs);
    }
    public static void main(String[] args) {
        int[] arrs = {222,1,3,100,9,0};
//        popo(arrs);
        quick(0, arrs.length -1, arrs);
        for (int arr: arrs) {
            System.out.println(arr);
        }
    }
}
