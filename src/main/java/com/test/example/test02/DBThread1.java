package com.test.example.test02;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DBThread1  implements Runnable {
    private int begin;
    private int end;


    public DBThread1(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
         for(int i=begin;i<end;i++){
            String uid =  UUID.randomUUID().toString();
            int reulst = DataToDB.insert(uid,"ss2"+i,"test"+i);
            System.out.println("result "+reulst+"   --------   "+Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) {
        DataToDB.deleteDB();
      //  ExecutorService cachedThreadPool = Executors.newCachedThreadPool();//当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
       // ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5); //固定5个线程
       ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();//固定1个线程
        //ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        int round =10;
        int count=0;
        int begin=0;
        int end =0;
        for(int i=0;i<100;i++){
            if(i%round==0){
                count++;
                begin=i;
                end=count*round-1;
                System.out.println("from "+begin+" to "+end);
                singleThreadExecutor.execute(new DBThread1(end, begin));
             }
        }
        singleThreadExecutor.shutdown();
    }
}
