package com.drcom.ListViewDemo.danlidemo;

/**
 * 懒汉模式，线程安全
 * synchronized保证一个时间内只能有一个线程得到执行，
 * 另一个线程必须等待当前线程执行完才能执行，使得线程安全。
 * 缺点每次调用getInstance方法都进行同步，造成了不必要的同步开销。
 */
public class AppUtils4 {
    private static AppUtils4 instance = null;
    public static AppUtils4 getInstance(){
        synchronized (AppUtils4.class){
            if (instance==null){
                instance= new AppUtils4();
            }
        }
        return instance;
    }
}
