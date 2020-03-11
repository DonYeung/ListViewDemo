package com.drcom.ListViewDemo.objectanimatordemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.R;

public class ObjectAnimatorDemo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectanimator_demo);

        MyView2 myView2 = findViewById(R.id.myview2);

        ObjectAnimator animator = ObjectAnimator.ofObject(myView2,"color",new ColorTypeEvaluator(),"#0000FF","#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果
        animator.setDuration(5000);
        animator.start();
    }


}
