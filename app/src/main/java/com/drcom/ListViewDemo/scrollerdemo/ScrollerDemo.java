package com.drcom.ListViewDemo.scrollerdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScrollerDemo extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Scroller scroller = new Scroller(this);

    }


}
