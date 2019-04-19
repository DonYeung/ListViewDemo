package com.drcom.ListViewDemo.obj;

import android.util.Log;

public class Translation2 {
    private int status;
    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {

        Log.d("RxJava", "第2次翻译翻译内容 = " + content.out);

    }
    //定义 输出返回数据 的方法
    public String show2() {
        return( "第2次翻译翻译内容 = " + content.out);

    }
}
