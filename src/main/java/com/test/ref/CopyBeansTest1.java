package com.test.ref;

public class CopyBeansTest1 {
    public static void main(String args[]){
        Person1 p1 = new Person1("张三", "男",28,"test1","test2","test3","test4");
        Person2 p2 =new Person2();
        p2.setP1(p1.getP1());
        p2.setP2(p1.getP2());
        p2.setP3(p1.getP3());
        p2.setP4(p1.getP4());
        System.out.println(p2);
    }
}
