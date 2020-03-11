package com.drcom.ListViewDemo.handlerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class HandlerDemo extends AppCompatActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////1. 通过匿名内部类实例化的Handler类对象---
        //注：此处并无指定Looper，故自动绑定当前线程(主线程)的Looper、MessageQueue
        //匿名内部类 会导致handler对外部类HandlerDemo 的持有，导致内存泄漏
        mHandler = new Handler(){
            // 通过复写handlerMessage()从而确定更新UI的操作
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        mHandler = new FHandler();


        //修复 解决方案1：静态内部类+弱引用
        mHandler = new FFHandler(this);

    }

    //新建Handler子类（内部类）(非静态内部类)  会导致handler对外部类HandlerDemo 的持有，导致内存泄漏
    class FHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }


    /**
     *     解决方案1：静态内部类+弱引用
     */

    //修复bug--内存泄露
    private static class FFHandler extends Handler{
        // 定义 弱引用实例
        private WeakReference<Activity> mReference;

        // 在构造方法中传入需持有的Activity实例
        public FFHandler(Activity activity){
            // 使用WeakReference弱引用持有Activity实例
            mReference = new WeakReference<Activity>(activity);
        }

        // 通过复写handlerMessage() 从而确定更新UI的操作
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }


    /**
     * 解决方案2：当外部类结束生命周期时，清空Handler内消息队列
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        // 外部类Activity生命周期结束时，同时清空消息队列 & 结束Handler生命周期

    }

    /**
     * 为了保证Handler中消息队列中的所有消息都能被执行，此处推荐使用解决方案1解决内存泄露问题，即 静态内部类 + 弱引用的方式
     */
}
