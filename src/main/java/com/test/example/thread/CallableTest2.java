package com.test.example.thread;



import java.util.*;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class CallableTest2 implements Callable<Long> {
    private long from;
    private long to;

    public CallableTest2(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        long numSum=0;
        for (long j=from;j<=to;j++){
            numSum=numSum+j;
        }
        System.out.println(Thread.currentThread().getName()+"  numsum="+numSum);
        return numSum;
    }
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        //getFixedThreadResult();
        getCachedThreadResult();
    }

    public static void getFixedThreadResult()throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<Long>> list =  executorService.invokeAll(asList(new CallableTest2(1,10),new CallableTest2(1,100),new CallableTest2(1,1000)));
        executorService.shutdown();
        for (Future ff:list){
            System.out.println(ff.get());
        }
    }
    public static void getCachedThreadResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        List<Future<Long>> list = new ArrayList<Future<Long>>();
//        for(long i=0;i<5;i++){
//            list.add(executorService.submit(new CallableTest2(0,100)));
//        }
        List<Future<Long>> list =  executorService.invokeAll(asList(new CallableTest2(1,10),new CallableTest2(1,100),new CallableTest2(1,1000)));

        for (Future ff:list){
            if(ff.isDone()){
                System.out.println(ff.get()+"--");
            }else {
                System.out.println("CallableTest2任务未完成！"+Thread.currentThread().getName());
            }
        }

    }
}
