package com.drcom.ListViewDemo.binddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    MyBinder binder = new MyBinder();
    private String TAG = "Don";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return binder;
    }

    class MyBinder extends Binder{
        void createProgressBar(){
            Log.i(TAG, "createProgressBar: ");
        }
        void onProgressUpdate(){
            Log.i(TAG, "onProgressUpdate: ");
        }
    }
}
