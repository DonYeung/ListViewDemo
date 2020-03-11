package com.drcom.ListViewDemo.interpotordemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.R;

public class InterpolatorDemo extends AppCompatActivity {
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator_demo);
        mButton = (Button) findViewById(R.id.Button);
        float curTranslationX = mButton.getTranslationX();
        //获取当前按钮的位置
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300, curTranslationX);
        // 创建动画对象 & 设置动画
// 表示的是:
// 动画作用对象是mButton
// 动画作用的对象的属性是X轴平移
// 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        objectAnimator.setDuration(5000);
        // 设置插值器

        objectAnimator.setInterpolator(new CustomInterpolator());
        objectAnimator.start();// 启动动画
        // 启动动画，源码分析就直接从start()开始


    }
}
