package com.test.example.test01;

public class MyThread extends Thread{
    private int ticket=10;
    public void run(){
        for(int i=1;i<=10;i++){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"--卖票：ticket--"+ticket--+"---"+i);
            }
        }

    }
    public static void main(String args[]){
        MyThread threadTest1 = new MyThread();
        MyThread threadTest2 = new MyThread();
        MyThread threadTest3 = new MyThread();
        threadTest1.start();//每个线程都各卖了10张，共卖了30张票
        threadTest2.start();//但实际只有10张票，每个线程都卖自己的票 没有达到资源共享
        threadTest3.start();
    }
}
