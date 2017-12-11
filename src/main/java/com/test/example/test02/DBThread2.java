package com.test.example.test02;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DBThread2   {
    private int begin;
    private int end;


    public DBThread2(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }


    public static void main(String[] args) {
        DataToDB.deleteDB();

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable(){
            public void run() {
                for(int i = 0; i<100; i++){
                    String uid =  UUID.randomUUID().toString();
                    int reulst = DataToDB.insert(uid,"ss2"+i,"test"+i);
                    System.out.println("result "+i+"   --------   "+Thread.currentThread().getName()+" : "+System.currentTimeMillis());
                }
            }
        },3,TimeUnit.SECONDS);//表示每3秒执行一次
        scheduledThreadPool.shutdown();


//         System.out.println("当前时间："+System.currentTimeMillis());
//         scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                System.out.println("delay 1 seconds, and excute every 3 seconds  "+System.currentTimeMillis());
//                for(int i = 0; i<100; i++){
//                    String uid =  UUID.randomUUID().toString();
//                    int reulst = DataToDB.insert(uid,"ss2"+i+uid,"test"+i);
//                    System.out.println("result "+i+"   --------   "+Thread.currentThread().getName()+" : "+System.currentTimeMillis());
//                }
//            }
//        }, 0, 2, TimeUnit.SECONDS);

    }
}
