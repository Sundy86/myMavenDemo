package com.test.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest1 implements Callable<String> {
    private String acceptStr;

    public CallableTest1(String acceptStr) {
        this.acceptStr = acceptStr;
    }

    @Override
    public String call() throws Exception {
        // 任务阻塞 1 秒 ,且此时有异常产生，可以上抛
        Thread.sleep(1000);
        return this.acceptStr;
    }
    public static void main(String args[]){
        Callable<String> callable = new CallableTest1("my Callable test !");
        FutureTask<String> task = new FutureTask<String>(callable);
        Long startTime = System.currentTimeMillis();
        new Thread(task).start();
        try {
            // 调用get()阻塞主线程，反之，线程不会阻塞
            String result = task.get();
            System.out.println("the result is :"+ result);
            Long endTime = System.currentTimeMillis();
            System.out.println("the test cast time is "+(endTime-startTime)/1000+" second ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
