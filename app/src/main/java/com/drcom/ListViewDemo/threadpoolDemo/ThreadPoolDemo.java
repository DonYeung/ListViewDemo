package com.drcom.ListViewDemo.threadpoolDemo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
    }

    private void test(){
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
//                Log.i("Don  ", "run: 测试git");
            }
        });


       // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
        //因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
        //定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
        Executors.newFixedThreadPool(3).execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        //创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
        //表示延迟3秒执行。
        Executors.newScheduledThreadPool(5).schedule(new Runnable() {
            @Override
            public void run() {

            }
        },3, TimeUnit.SECONDS);

        //定期执行示例代码如下：
        //表示延迟1秒后每3秒执行一次。
        Executors.newScheduledThreadPool(5).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

            }
        },1,3,TimeUnit.SECONDS);


        //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
