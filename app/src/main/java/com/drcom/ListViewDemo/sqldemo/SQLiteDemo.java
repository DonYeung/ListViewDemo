package com.drcom.ListViewDemo.sqldemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.drcom.ListViewDemo.R;

public class SQLiteDemo extends Activity implements View.OnClickListener {
    private Button create, add, delete, modify, search;
    private MyHelper myHelper;
    private SQLiteDatabase db;
    private ContentValues values;

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
        myHelper = new MyHelper(this, "student.db", null, 2);
        values = new ContentValues();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:
                db = myHelper.getReadableDatabase();
//                Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();

                break;
            case R.id.add:
                values.put("name", "lucy");
                values.put("age", 17);
                values.put("grade", "grade2");
                db.insert("student", null, values);
                values.clear();
                values.put("name", "susan");
                values.put("age", 19);
                values.put("grade", "grade13");
                db.insert("student", null, values);
                values.clear();
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
              /*  //添加数据到数据库
                long index = myHelper.getWritableDatabase().insert("student", null, values);

                //大于0表示添加成功
                if (index > 0) {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

                } else {
//                    return false;
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

                }*/
                break;

            case R.id.delete:
                db.delete("student", "age>?", new String[]{"17"});
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.modify:
                values.put("grade", "grade14");
                db.update("student", values, "name=?", new String[]{"lucy"});
                Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                values.clear();
                break;
            case R.id.selach:
                Cursor cursor = db.query("student", null, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String grade = cursor.getString(cursor.getColumnIndex("grade"));
                        Log.i("Don", "onClick name: " + name + "\n age:" + age + "\ngrade:" + grade);
                    }
                    cursor.close();
                }
                Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show();


                break;
        }
    }
}
