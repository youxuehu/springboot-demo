package com.example.springbootdemo.common.thread.jiaoti;

public class FourMain {

/**
 * 思路： 我实现的思路就是遍历这个字符串， 先选定头位置为第一个字符，然后从最后向前遍历这个字符串， 头尾两个字符相同，
 * 则取中间字符串，进行递归。
 * 递归结束后得到结果， 继续将头向后推1位，然后再从字符串最后向前遍历， 如此循环，当尾等于头时，退出最外层循环，输出结果。
 *
 */


    /**
     * 找出字符串中对称的子字符串的个数
     *
     * @param str
     * @return
     */
    public static int findSymmetryStrNum(String str) {
        // 记录对称子串个数
        int count = 0;
        if (str != null && str.length() > 1) {
            int strLength = str.length();
            for (int strHead = 0; strHead < strLength; strHead++) {
                char headChar = str.charAt(strHead);
                int current = strLength - 1;
                // 当尾字符索引等于头字符索引时退出循环
                while (current > strHead) {
                    char currentChar = str.charAt(current);

// 如果头尾字符相等,则继续判断
                    if (headChar == currentChar) {
// 取出头尾中间的子字符串,对其进行分析
                        String newStr = str.substring(strHead + 1, current);
// 如果此子字符串的长度大于1,则进行递归
                        if (newStr.length() > 1)
                            count += findSymmetryStrNum(newStr);

// 如果此子字符串只有1个或0个字符,则表明原头尾字符和此单个字符组成对称字符串
                        else
                            count++;
// 将尾字符索引向前推1位
                        current--;
                    }
// 如果头尾字符不相等,则将尾字符索引向前推1位
                    else {
                        current--;
                    }
                }
            }
        }
        return count;
    }


    // 测试程序
    public static void main(String args[]) {
        int count = findSymmetryStrNum("1221");//
        System.out.println("symmetry string count is : " + count);
    }
}
