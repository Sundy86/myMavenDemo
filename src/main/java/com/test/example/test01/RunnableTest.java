package com.test.example.test01;

public class RunnableTest  implements  Runnable{
    private String name;
    public RunnableTest(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            System.out.println(Thread.currentThread().getName()+"----------"+name+"---"+i);
        }
    }
    public static void main(String args[]){
        RunnableTest threadTest1 = new RunnableTest("thread A");
        RunnableTest threadTest2 = new RunnableTest("thread B");
        new Thread(threadTest1).run();
        new Thread(threadTest2).run();

    }
}
