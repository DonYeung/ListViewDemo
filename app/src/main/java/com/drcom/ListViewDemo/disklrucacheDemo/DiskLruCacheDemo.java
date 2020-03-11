package com.drcom.ListViewDemo.disklrucacheDemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

import okhttp3.internal.DiskLruCache;


public class DiskLruCacheDemo {
    private Context mContext;

    public DiskLruCacheDemo(Context context) {
        this.mContext = context;
    }

    private void setCacheDir() {


        DiskLruCache mDiskLruCache = null;
//        try {
        File cacheDir = getDiskCacheDir(mContext, "bitmap");
        if (!cacheDir.exists()){
            cacheDir.mkdir();
        }
        //todo
//        mDiskLruCache = DiskLruCache.create(this,cacheDir,getAppVersion(mContext),1, 10 * 1024 * 1024);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.
                isExternalStorageEmulated()) {
            //当SD卡存在或者SD卡不可被移除，获取路径 /sdcard/Android/data/<application package>/cache
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
