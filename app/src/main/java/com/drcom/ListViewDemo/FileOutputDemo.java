package com.drcom.ListViewDemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileOutputDemo extends Activity {
    private EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_output_demo);
        edit = findViewById(R.id.edit);

        StringBuilder content = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            FileInputStream fileInputStream  = openFileInput("name");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line ;
            while ((line=bufferedReader.readLine())!=null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String inpout = content.toString();
        if (inpout!=null && !TextUtils.isEmpty(inpout)){
            edit.setText(inpout);
            edit.setSelection(inpout.length());
            Toast.makeText(this, "重新加载成功", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String text = edit.getText().toString();
        BufferedWriter bufferedWriter = null;
        if (text != null && !TextUtils.isEmpty(text)) {


            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput("name", Context.MODE_PRIVATE);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                bufferedWriter.write(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (bufferedWriter!=null){
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}
