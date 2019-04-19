package com.drcom.ListViewDemo;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by Don on 2019/3/22.
 * ${CLASSNAME}
 */
public class TestServices extends Service {
    MyBinder myBinder =new MyBinder();
    private static final String TAG = "TestServices";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return myBinder;
    }

    class MyBinder extends Binder{
        private static final String TAG = "MyBinder";

        void createProgessDialog(){
            Log.i(TAG, "createProgessDialog: ");
        }
        void updateProgess(){
            Log.i(TAG, "updateProgess: ");
        }

    }

}
