package com.drcom.ListViewDemo.valueanimatordemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.R;

public class ValueAnimatorDemo extends AppCompatActivity {
    private static final String TAG = "Don";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator_demo);
        Button mButton = (Button) findViewById(R.id.Button);

        // 步骤1：设置属性数值的初始值 & 结束值
        ValueAnimator animator =ValueAnimator.ofInt(mButton.getLayoutParams().width,500);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

// 步骤2：设置动画的播放各种属性
        animator.setDuration(5000);

// 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (Integer) animation.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: "+currentValue);
                mButton.getLayoutParams().width = currentValue;
                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化

// 步骤4：刷新视图，即重新绘制，从而实现动画效果

                mButton.requestLayout();


            }
        });

        animator.start();
        // 启动动画

    }
}
