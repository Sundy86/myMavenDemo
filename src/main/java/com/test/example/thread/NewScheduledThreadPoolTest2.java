package com.test.example.thread;

import com.test.example.test02.DataToDB;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class NewScheduledThreadPoolTest2 {
    public static void main(String[] args) {
        //表示延迟1秒后每3秒执行一次
        DataToDB.deleteDB();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("当前时间："+System.currentTimeMillis());
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds  "+System.currentTimeMillis());
                for(int i = 0; i<100; i++){
                    String uid =  UUID.randomUUID().toString();
                    int reulst = DataToDB.insert(uid,"ss2"+i+uid,"test"+i);
                  System.out.println("result "+i+"   --------   "+Thread.currentThread().getName()+" : "+System.currentTimeMillis());
                }
            }
        }, 0, 2, TimeUnit.SECONDS);

      }

}