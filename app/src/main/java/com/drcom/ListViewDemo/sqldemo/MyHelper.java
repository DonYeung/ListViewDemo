package com.drcom.ListViewDemo.sqldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static String CREATE_STUDENT = "create table student("
            +"id integer primary key autoincrement," +
            "name text," +
            "age integer," +
            "grade text)";
    public static String CREATE_COURSE = "create table course("
            +"id integer primary key autoincrement," +
            "name text," +
            "credit integer)";

    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
        Toast.makeText(mContext, "创建表成功", Toast.LENGTH_SHORT).show();;
        db.execSQL(CREATE_COURSE);
        Toast.makeText(mContext, "创建course表成功", Toast.LENGTH_SHORT).show();;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_COURSE);
                Toast.makeText(mContext, "创建course表成功", Toast.LENGTH_SHORT).show();;
            default:
        }
    }
}
