package com.test.example.test02;

import java.util.UUID;

public class DBThread implements Runnable {
    private int begin;
    private int end;

    public DBThread(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
         for(int i=begin;i<end;i++){
            String uid =  UUID.randomUUID().toString();
            int reulst = DataToDB.insert(uid,"ss2"+i,"test"+i);
            System.out.println("result "+reulst+"   --------"+i);
        }
    }
    public static void main(String[] args) {
        DataToDB.deleteDB();
        int round =100;
        int count=0;
        int begin=0;
        int end =0;
        for(int i=0;i<1000;i++){
            if(i%round==0){
                count++;
                begin=i;
                end=count*round-1;
                System.out.println("from "+begin+" to "+end);
                new Thread(new DBThread(begin,end)).start();

            }
        }
        System.out.println("线程退出!");
    }
}
