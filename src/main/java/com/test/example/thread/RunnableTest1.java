package com.test.example.thread;

public class RunnableTest1 implements Runnable {
    private String acceptStr;

    public RunnableTest1(String acceptStr) {
        this.acceptStr = acceptStr;
    }
    @Override
    public void run() {
        try {
            // 线程阻塞 1 秒，此时有异常产生，只能在方法内部消化，无法上抛
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 最终处理结果无法返回
        System.out.println(this.acceptStr);
    }
    public static void main(String args[]){
        Runnable runnable = new RunnableTest1("my Callable test !");
        Long startTime = System.currentTimeMillis();
        new Thread(runnable).start();
        Long endTime = System.currentTimeMillis();
        System.out.println("the test cast time is "+(endTime-startTime)/1000+" second ");

    }

}
