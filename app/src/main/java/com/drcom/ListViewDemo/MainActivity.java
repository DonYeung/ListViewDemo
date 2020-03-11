package com.drcom.ListViewDemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private IRemoteService remoteService;
    private  Chronometer cmPasstime;
    private long mNextTime = 100L;
    private long seconds = 0;//秒数(时间)
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmPasstime = findViewById(R.id.cm_passtime);
//        Intent intent = new Intent();
//        intent.setClass(MainActivity.this,RemoteService.class);
//        bindService(intent,connection, Service.BIND_AUTO_CREATE);

//        int[] nums = {0,5,2,6,9,3,7,1,8,4};
//        for(int i = 0 ; i < nums.length ; i ++ )
//        {
//            Log.i(TAG, "onCreate1: "+nums[i]);
//        }
//        Log.i(TAG,"_________________________");
//        bubbleSort(nums);
//        for(int i = 0 ; i < nums.length ; i ++ )
//        {
//            Log.i(TAG, "onCreate2: "+nums[i]);
//        }
        initchronmeter();
    }
    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            if (seconds<0){
                if (null != mRunnable) {
                    mHandler.removeCallbacks(mRunnable);
                    mRunnable = null;
                    return;
                }
            }
            cmPasstime.setText(formatseconds());
            mHandler.postDelayed(this, 1000);
        }
    }

    private MyRunnable mRunnable = null;

    private void initchronmeter(){
//        cmPasstime.setBase(SystemClock.elapsedRealtime());
//        cmPasstime.setFormat("计时：%s");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE,50);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long timeInMillisfor21 = calendar.getTimeInMillis();

        long curenttime = System.currentTimeMillis();

//        long time = 0L;
        mNextTime = Math.abs(timeInMillisfor21- curenttime)/1000;

        seconds =  Math.abs(timeInMillisfor21- curenttime)/1000;
        cmPasstime.setBase(SystemClock.elapsedRealtime());
//        cmPasstime.setText(formatseconds());

        if (mRunnable == null)
            mRunnable = new MyRunnable();
        mHandler.postDelayed(mRunnable, 0);

//        cmPasstime.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
////                String time = chronometer.getText().toString();
//                if (mNextTime <= 0) {
//                    cmPasstime.stop();
//                }
//
//                cmPasstime.setText(String.format("倒计时：%s", mFormat.format(new Date(mNextTime * 1000))));
//                mNextTime--;
//            }
//        });
//        cmPasstime.start();

    }

    public String formatseconds() {
        String hh = seconds / 3600 > 9 ? seconds / 3600 + "" : "0" + seconds
                / 3600;
        String mm = (seconds % 3600) / 60 > 9 ? (seconds % 3600) / 60 + ""
                : "0" + (seconds % 3600) / 60;
        String ss = (seconds % 3600) % 60 > 9 ? (seconds % 3600) % 60 + ""
                : "0" + (seconds % 3600) % 60;

        seconds--;

        return hh + ":" + mm + ":" + ss;
    }

    private ServiceConnection connection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService =  IRemoteService.Stub.asInterface(service);
            try {
                Log.i(TAG, "Client pid= " + Process.myPid());
                Log.i(TAG, "RemoteService pid= " + remoteService.getPid());
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "Service has unexpectedly disconnected");
            remoteService = null;
        }
    };

    /**
     * 冒泡排序法
     *
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     *  * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     *  * 针对所有的元素重复以上的步骤，除了最后一个。
     *  * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param nums 需要排序的整型数组
     *
     */
    public static void bubbleSort(int[] nums){
        int temp =0;
        int size =nums.length;
        for (int i = 0; i < size-1 ; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (nums[j]>nums[j+1]){  //交换两个数字的位置
                    temp =nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] =temp;
                }
            }
        }

        //倒着排 从大到小
//        for (int i = 0; i < size -1; i++) {
//            for (int j = 0; j < size-1-i; j++) {
//                if (nums[j]<nums[j+1]){
//                    temp = nums[j];
//                    nums[j] = nums[j+1];
//                    nums[j+1]=temp;
//                }
//
//            }
//        }
    }


    /**
     * 选择排序
     * 在未排序序列中找到最小元素，存放到排序序列的起始位置
     *  * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
     *  * 以此类推，直到所有元素均排序完毕。
     * @param nums
     */
    public static  void selectSort(int[] nums ) {
        int size = nums.length; //数组长度
        int temp = 0; //中间变量

        for (int i = 0; i < size; i++) {
            int k = i ;
            for (int j = size-1 ; j >i ; j--) {
                if (nums[j]<nums[k]){
                    k =j;
                }
            }
            temp=nums[i];
            nums[i] =nums[k];
            nums[k] =temp;
        }
    }


    /**
     * 插入排序
     *
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置中
     * 重复步骤2
     * @param nums  待排序数组
     */
    public static void insertSort (int[] nums){
        int  size =nums.length;
        int temp =0;
        int j =0;
        for (int i = 0; i < size; i++) {
            temp = nums[i];
            for (j = i; j>0&& temp <nums[j-1] ; j--) {

                nums[j] =nums[j-1];

            }
            nums[j]=temp;
        }
    }





}
