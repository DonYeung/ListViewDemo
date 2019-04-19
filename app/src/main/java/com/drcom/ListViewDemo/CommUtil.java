package com.drcom.ListViewDemo;

import android.content.Context;
import android.util.Log;

/**
 * Created by Don on 2019/3/21.
 * ${CLASSNAME}
 */
public class CommUtil {
    private static final String TAG = "CommUtil";
    private static CommUtil instance;
    private Context context;

    private CommUtil(Context context) {
        this.context = context;
    }

    public static CommUtil getInstance(Context mcontext) {
        if (instance == null) {
            instance = new CommUtil(mcontext);
        }
        return instance;
    }
    public void getdata(){
        Log.i(TAG, "getdata: ");
    }
}
