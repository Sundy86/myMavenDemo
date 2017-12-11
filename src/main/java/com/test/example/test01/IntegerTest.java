package com.test.example.test01;

public class IntegerTest {

    public static void main(String[] args) {
        int a1 = 128;
        Integer a2 = 128;
        Integer a3 = new Integer(128); // 这样写优化后其实就是a2的写法
        // Integer会自动拆箱为int，所以为true
        System.out.println("a1 == a2: " + (a1 == a2));  // true
        System.out.println("a2 == a3: " + (a2 == a3));  // false
        System.out.println("a2 == a3: " + (a2.equals(a3)));  // true 对象比较
    }

}
