package com.test.example.test01;

public class StringReverse {
    public static void main(String[] args) {
        String test = "I love china  ";
        System.out.println("BufferReverse "+ BufferReverse(test));
        System.out.println("StringReverse  " +  StringReverse(test));
        System.out.println("查找字符串 C的下标为: "+test.indexOf('c'));//7
        System.out.println("查找字符串 i最后一次出现的索引位置下标为: "+test.lastIndexOf('i'));//9
        System.out.println("获取指定索引位置的字符: "+test.charAt(3));//o
        System.out.println("获取子字符串: "+test.substring(3,6));//ove
        System.out.println("获取子字符串: "+test.substring(6));// china   从下标是6的字符开始截取

        String test1 = "hello world  ";
        System.out.println("去空格 "+ test1.trim());
    }

    public static String BufferReverse(String str){
        return new StringBuilder(str).reverse().toString();
    }


    public static String StringReverse(final String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            result+=c;
        }
        return result;
    }
}

