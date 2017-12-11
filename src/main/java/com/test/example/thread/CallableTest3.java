package com.test.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableTest3 {
    public static void getCachedThreadResult() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Callable<Integer> callable = null;
        List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
        for(int i=0;i<10;i++){
            callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int ran = new Random().nextInt(1000);
                    Thread.sleep(ran);
                    System.out.println(Thread.currentThread().getName()+" 歇息了 " + ran +"毫秒");
                    return ran;
                }
            };
            list.add(callable);
        }

        long s = System.currentTimeMillis();
        List<Future<Integer>> list1 = executorService.invokeAll(list);
        long ss =  System.currentTimeMillis() - s  ;
        System.out.println("运行任务消耗了 ：" + ss +"毫秒");
        for(int i=0;i<list1.size();i++){
           System.out.println(list1.get(i).get());
        }

        executorService.shutdown();
    }

    public static void main(String args[]) throws Exception {
        getCachedThreadResult();
    }
}
