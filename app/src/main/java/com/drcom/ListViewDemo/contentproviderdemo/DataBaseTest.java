package com.drcom.ListViewDemo.contentproviderdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.R;
import com.drcom.ListViewDemo.sqldemo.MyHelper;

public class DataBaseTest extends AppCompatActivity implements View.OnClickListener {
    private Button create, add, delete, modify, search;
    private MyProviderHelper myHelper;
    private SQLiteDatabase db;
    private ContentValues values;
    private Uri uri;
    private Uri uri_return;
    private Uri uri_new;
    private ContentResolver resolver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);
        create = findViewById(R.id.create);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        modify = findViewById(R.id.modify);
        search = findViewById(R.id.selach);
        create.setOnClickListener(this);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        modify.setOnClickListener(this);
        search.setOnClickListener(this);
//        myHelper = new MyProviderHelper(this, "student.db", null, 2);
        values = new ContentValues();

        uri = Uri.parse("content://com.drcom.ListViewDemo.contentproviderdemo.MyProvider/student");
        resolver = getContentResolver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                Log.i("Don", "onClick: 增加成功");
                break;
            case R.id.add:
                values.put("name","tom");

                values.put("age",17);
                values.put("grade","grade1");
                uri_return = resolver.insert(uri,values);
                String id = uri_return.getPathSegments().get(1);
                uri_new = Uri.parse("content://com.drcom.ListViewDemo.contentproviderdemo.MyProvider/student/"+id);
                values.clear();
                values.put("name","jerry");

                values.put("age",2);
                values.put("grade","grade3");
                resolver.insert(uri_new,values);
                values.clear();
                break;

            case R.id.delete:
                resolver.delete(uri_new,null,null);
                break;
            case R.id.modify:
                values.put("grade","gra100");
                resolver.update(uri,values,null,null);

                break;
            case R.id.selach:
                Cursor cursor = resolver.query(uri,null,null,null,null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String grade = cursor.getString(cursor.getColumnIndex("grade"));
                        Log.i("Don", "onClick: name:"+name+"\nage:"+age+"\ngrade:"+grade);
                    }
                }
                cursor.close();
                break;
        }
    }
}
