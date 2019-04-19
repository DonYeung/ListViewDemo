package com.drcom.ListViewDemo;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Don on 2019/3/21.
 * ${CLASSNAME}
 */
public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
//        CommUtil.getInstance(TestActivity.this).getdata();
//        myHandler.postDelayed(mRunable,1000*3);
//        startActivity(new Intent(this, VLayoutActivityDemo.class));
//        finish();


//        writeFile("eeeee");

//        readFile();

//        readconttenturi();

        Intent intent =new Intent(this,TestServices.class);
        bindService(intent,connection,BIND_AUTO_CREATE);

//        MyFragment myFragment =new MyFragment();
//        FragmentManager fragmentManager =getSupportFragmentManager();
//        FragmentTransaction transaction =fragmentManager.beginTransaction();
//        transaction.add(R.id.my_recycler_view,myFragment);//
//        transaction.addToBackStack(null);
//        transaction.commit();

    }

    private void writeFile(String text) {
        File file = new File(Environment.getExternalStorageDirectory().toString(), "/DrcomRecord/" + "qqq.txt");


        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            FileOutputStream outputStream = this.openFileOutput("dataqq", Context.MODE_PRIVATE);//写到/data/data/file文件中
//            FileOutputStream fileOutputStream =new FileOutputStream(file,false); //这种方法写到指定位置
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(text);
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            Log.i(TAG, "writeFile: " + "wirte成功 ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readFile() {
        File file = new File(Environment.getExternalStorageDirectory().toString(), "/DrcomRecord/" + "adb.text");

        StringBuffer content = new StringBuffer();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.i(TAG, "readFile: " + content);

    }


    private class MyHandler extends Handler {
        //        private final WeakReference<TestActivity> activity;
        private final TestActivity activity;

        private MyHandler(TestActivity activity) {
            this.activity = activity;
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activity != null) {
                // ...
            }


        }
    }

    private MyHandler myHandler = new MyHandler(TestActivity.this);

    private Runnable mRunable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(TestActivity.this, VLayoutActivityDemo.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        myHandler.removeCallbacks(mRunable);
    }

    public class MyThread extends Thread {
        public MyThread(Object o) {
        }

        public void run() {
            System.out.println("MyThread.run()");
        }
    }


    private void readconttenturi() {

        List<String> list = new ArrayList<>();
        Cursor cursor1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor1 != null) {
            while (cursor1.moveToNext()) {
                String name = cursor1.getString(cursor1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int id = cursor1.getInt(cursor1.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                if (cursor2!=null){
                    while (cursor2.moveToNext()){
                        String phone =cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        list.add(name+":"+phone);
                    }

                }
                cursor2.close();


            }
            cursor1.close();
        }
        for (String phonenumname:list) {
            Log.i(TAG, "readconttenturi: "+phonenumname);

        }
    }

    private TestServices.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (TestServices.MyBinder) service;
            myBinder.createProgessDialog();
            myBinder.updateProgess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    };




}
