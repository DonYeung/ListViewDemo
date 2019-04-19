package com.drcom.ListViewDemo;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Created by Don on 2019/4/18.
 */
public class ViewPager2Demo extends AppCompatActivity {
    private static final String TAG = "ViewPager2Demo";
    private ViewPager2 mviewPager2;
    private  ViewPager2Adapter viewPager2Adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager2);


        mviewPager2 = findViewById(R.id.viewpager2);

        List<String> list = new ArrayList<>();
        list.add("页面一");
        list.add("页面二");
        list.add("页面三");
        list.add("页面四");

        mviewPager2.setAdapter(new ViewPager2Adapter(this, list, mviewPager2));

        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                Log.i(TAG, "onPageScrolled: 点击了X行"+position);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.i(TAG, "onPageSelected: pos"+position);
            }
        });
    }
}
