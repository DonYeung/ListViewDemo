package com.drcom.ListViewDemo.danlidemo;

/**
 * 静态内部类单例模式
 * 第一次调用getInstance方法时加载SingletonPatternHolder
 * 并初始化singletonPattern，这样不仅能确保线程安全，
 * 也能保证SingletonPattern类的唯一性。
 */
public class AppUtils3 {

    private AppUtils3(){

    }
     static class AppUtils3Inner{
         private static final AppUtils3 instance = new AppUtils3();
    }

    public static AppUtils3 getInstance(){
         return AppUtils3Inner.instance;
    }
}
