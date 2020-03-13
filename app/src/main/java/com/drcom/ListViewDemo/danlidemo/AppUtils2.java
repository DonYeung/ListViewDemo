package com.drcom.ListViewDemo.danlidemo;

/**
 * 推荐写法（双重校验DCL模式）
 * 这里使用了volatile关键字，因为多个线程并发时初始化成员变量和对象实例化顺序可能会被打乱，这样就出错了，
 * volatile可以禁止指令重排序。双重校验虽然在一定程度解决了资源的消耗和多余的同步，
 * 线程安全问题，但在某些情况还是会出现双重校验失效问题，即DCL失效。
 */
public class AppUtils2 {
    private volatile static AppUtils2 instance;

    private AppUtils2(){

    }
    public static AppUtils2 getInstance(){
        if (instance==null){  //双重校验DCL单例模式
            synchronized (AppUtils2.class){   //同步代码块
                if (instance==null){
                    instance = new AppUtils2(); //创建一个新的实例
                }
            }
        }
        return instance;//返回一个实例
    }
}
