package com.matrix.string;

/**
 * 去掉最外层括号，计数器解法
 * @author : cui_feng
 * @since : 2023-09-08 16:15
 */
public class String01 {

    public static void main(String[] args) {
        String str = "(()())(()(()()))";
        System.out.println(split(str));
    }

    public static String split(String str) {
        StringBuilder sb = new StringBuilder();
        char [] strArray = str.toCharArray();
        int count = 0;
        int start = 0;
        for(int i=0; i<strArray.length; i++) {
            if (strArray[i]=='(') {
                count++;
            }
            if (strArray[i]==')') {
                count--;
            }
            if (count==0) {
                sb.append(str, start+1, i);
                start = i+1;
            }
        }

        return sb.toString();
    }
}
