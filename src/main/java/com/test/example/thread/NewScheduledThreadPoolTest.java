package com.test.example.thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class NewScheduledThreadPoolTest {
    //创建一个定长线程池，支持定时及周期性任务执行。 表示延迟3秒执行。
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        System.out.println("当前时间："+System.currentTimeMillis());
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                      System.out.println("delay 3 seconds  "+Thread.currentThread().getName()+":"+System.currentTimeMillis());
                }
            }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.shutdown();
    }
}