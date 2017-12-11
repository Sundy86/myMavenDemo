package com.test.example.test01;

public class ThreadTest extends Thread{
    private String name;
    public ThreadTest(String name){
        super();
        this.name=name;
    }
    @Override
    public void run() {
         for(int i=1;i<=10;i++){
            System.out.println(Thread.currentThread().getName()+"----------"+name+"---"+i);
        }
    }
    public static void main(String args[]){
        ThreadTest threadTest1 = new ThreadTest("thread A");
        ThreadTest threadTest2 = new ThreadTest("thread B");
        threadTest1.start();
        threadTest2.start();
     }
}
