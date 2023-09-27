package com.matrix.hash;

import java.util.Arrays;

/**
 * 查找字符串中不重复的子串的最大长度
 * 哈希表+双指针
 *
 * @author : cui_feng
 * @since : 2023-09-27 14:03
 */
public class HashTable01 {

    public static void main(String[] args) {
        String [] strs = {"abcc","abc","bbc"};
        for (String str : strs) {
            System.out.println(resolve(str));
        }
        System.out.println();
        for (String str : strs) {
            System.out.println(resolve2(str));
        }
    }

    public static int resolve(String s) {
        int len;
        if (s==null || (len=s.length())==0) {
            return 0;
        }

        int res=0, left=0, right=0;
        // 哈希表
        char [] chs = new char[128];
        while (right < len) {
            // 右指针字符
            char rightChar = s.charAt(right);
            // 取哈希表中的字符
            char c = chs[(chs.length-1) & hash(rightChar)];
            // 未重复出现
            if (rightChar != c) {
                // 右指针自增
                right++;
                // 将不重复字符记录到哈希表中
                chs[(chs.length-1)&hash(rightChar)] = rightChar;
                // 每次计算不重复子串的长度，取最大值
                res = Math.max(res, right-left);
            } else {
                // 如果重复出现则左指针自增
                char leftChar = s.charAt(left++);
                // 删除左指针在哈希表中的字符
                chs[(chs.length-1)&hash(leftChar)] = 0;
            }
        }

        return res;
    }

    /**
     * 优化解法
     * @param s
     * @return
     */
    public static int resolve2(String s) {
        int len;
        if (s==null || (len=s.length())==0) {
            return 0;
        }

        int res=0, left=0, right=0;
        // 哈希表,记录字符最后一次出现的索引
        int [] arr = new int[128];
        // 填充默认值
        Arrays.fill(arr, -1);

        while (right < len) {
            int c = s.charAt(right);
            // 字符已经重复出现
            if (arr[c] != -1) {
                // 移动左指针，直接定位到上次出现的位置的下一个位置，只向右移动不向左移动
                left = Math.max(left, arr[c]+1);
            }
            // 无论是否重复，记录该字符最后一次出现的索引
            arr[c] = right;
            // 计算子串长度，取最大值
            res = Math.max(res,right+1-left);
            // 移动右指针
            right++;
        }

        return res;
    }

    public static int hash(char c) {
        return c;
    }
}
