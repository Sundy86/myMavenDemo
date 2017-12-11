package com.test.example.test01;

public class MyRunnable implements Runnable {
    private int ticket=10;
    public void run(){
        for(int i=1;i<=10;i++){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"--卖票：ticket--"+ticket--+"---"+i);
            }
        }
    }
    public static void main(String args[]){
        MyRunnable myrunable = new MyRunnable();
        //程序中有三个线程，但是一共卖了10张票，也就是说使用Runnable实现多线程可以达到资源共享目的。
        new Thread(myrunable).start();
        new Thread(myrunable).start();
        new Thread(myrunable).start();
    }
}
