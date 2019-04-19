package com.drcom.ListViewDemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by ${USER_NAME} on 2019/2/22 15:55
 * ${CLASSNAME}
 */
public class TestView extends LinearLayout {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
