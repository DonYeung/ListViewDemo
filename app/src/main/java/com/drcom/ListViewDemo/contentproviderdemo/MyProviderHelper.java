package com.drcom.ListViewDemo.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * 跨程序访问时我们不能直接使用 Toast
 */
public class MyProviderHelper extends SQLiteOpenHelper {
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

    public MyProviderHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_COURSE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_COURSE);
            default:
        }
    }
}
