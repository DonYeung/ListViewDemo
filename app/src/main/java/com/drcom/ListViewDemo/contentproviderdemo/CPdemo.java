package com.drcom.ListViewDemo.contentproviderdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.R;

import java.util.ArrayList;
import java.util.List;

public class CPdemo extends AppCompatActivity {
    private ListView listview;
    private ArrayAdapter adapter ;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpdemo);

        listview = findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listview.setAdapter(adapter);

//        init();

    }

    private void init() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if (cursor!=null){
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"="+id,null,null);
                if (cursor1!=null){
                    while (cursor1.moveToNext()){
                        String phone = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        list.add("name:"+name +",phone:"+phone);
                    }
                }
                cursor1.close();
            }
        }
        cursor.close();
//        Uri uri = Uri.parse("");
//        ContentResolver contentResolver = this.getContentResolver();
//        ContentValues contentValues  = new ContentValues();
//        contentValues.put("name","don");
//        contentResolver.insert(uri,contentValues);
    }
}
