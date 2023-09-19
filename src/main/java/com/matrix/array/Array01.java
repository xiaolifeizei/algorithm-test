package com.matrix.array;


/**
 * 遍历法翻转数字
 *
 * @author : cui_feng
 * @since : 2023-08-31 12:23
 */
public class Array01 {

    public static void main(String[] args) throws Exception {
        String [] arrStr = {"123","321","12300","-555666"};
        for (String str : arrStr) {
            System.out.println(turnNumber(str));
        }
    }

    public static String turnNumber(String str) {
        char [] strArr = str.toCharArray();
        int lastZero = -1;
        StringBuilder output = new StringBuilder();
        for (int i=strArr.length-1; i>=0; i--) {
            char c = strArr[i];
            if (i==0 && c=='-') {
                output = new StringBuilder("-").append(output);
                continue;
            }
            if (c=='0' && lastZero==-1) {
                lastZero = 0;
                continue;
            }
            if (c=='0' && lastZero==0) {
                continue;
            } else {
                lastZero = 1;
            }
            output.append(c);
        }
        return output.toString();
    }
}
