package com.matrix.hash;

/**
 * 查找字符串中不重复的子串的最大长度
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
                chs[(chs.length-1)&hash(rightChar)] = 0;
            }
        }

        return res;
    }

    public static int hash(char c) {
        return c;
    }
}
