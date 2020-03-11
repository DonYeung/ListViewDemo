package com.drcom.ListViewDemo.customviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.drcom.ListViewDemo.R;

public class CustomViewGroup extends LinearLayout {
    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 源码分析：LinearLayout复写的onLayout（）
     * 注：复写的逻辑 和 LinearLayout measure过程的 onMeasure()类似
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 参数说明
        // changed 当前View的大小和位置改变了
        // left 左部位置
        // top 顶部位置
        // right 右部位置
        // bottom 底部位置

        // 1. 遍历子View：循环所有子View
        // 注：本例中其实只有一个
        for (int i=0; i<getChildCount(); i++) {
            View child = getChildAt(i);

            // 取出当前子View宽 / 高
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            // 2. 计算当前子View的四个位置值
            // 2.1 位置的计算逻辑
            int mLeft = (r - width) / 2;
            int mTop = (b - height) / 2;
            int mRight =  mLeft + width;
            int mBottom = mTop + height;

            // 3. 根据上述4个位置的计算值，设置子View的4个顶点
            // 即确定了子View在父容器的位置
            child.layout(mLeft, mTop, mRight,mBottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
