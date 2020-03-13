package com.drcom.ListViewDemo.danlidemo;

/**
 * 饿汉模式（类加载较慢，但获取对象速度快，线程安全。）
 */
public class AppUtils {
    private static final AppUtils instance = new AppUtils();
    private AppUtils (){

    }
    public static AppUtils getInstance(){
        return instance;
    }
}
