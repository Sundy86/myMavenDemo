package com.test.example.test01;

public class DoubleIntCovert {
    public static void main(String[] args) {
        double firstAvg=81.29;
        double secondAvg;
        int rise=2;
        secondAvg=firstAvg+rise;
        System.out.println(secondAvg);

        int secondAvgnew=(int)firstAvg+rise;
        System.out.println((double)secondAvgnew);
    }
}
