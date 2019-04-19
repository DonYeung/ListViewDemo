package com.drcom.ListViewDemo;

import android.app.Activity;
import android.os.Bundle;

public class testd extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyThread1 mt = new MyThread1();
        Thread mt11 = new Thread(mt,"窗口1");
        mt11.start();
    }

    //第一个线程类:实现卖票速度1s/张操作
    private class MyThread1 implements Runnable{

        private int ticket = 100;//一个窗口有100张票

        //在run方法里复写需要进行的操作:卖票速度1s/张
        @Override
        public void run(){
            while (ticket>0){
                ticket--;
                System.out.println(Thread.currentThread().getName() + "卖掉了1张票，剩余票数为:"+ticket);

                try {
                    Thread.sleep(1000);//卖票速度是1s一张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


}
