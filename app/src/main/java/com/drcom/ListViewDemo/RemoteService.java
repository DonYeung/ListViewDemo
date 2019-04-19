package com.drcom.ListViewDemo;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by Don on 2019/3/20.
 * ${CLASSNAME}
 */
public class RemoteService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    
    public RemoteService(){
        
    }
    // 实现AIDL接口
    private final IRemoteService.Stub binder = new IRemoteService.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString) {

        }

        @Override
        public int getPid() {
            return Process.myPid();
        }
    };


    
}
