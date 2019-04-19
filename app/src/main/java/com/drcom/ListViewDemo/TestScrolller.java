package com.drcom.ListViewDemo;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by ${USER_NAME} on 2019/2/22 15:17
 * ${CLASSNAME}
 */
public class TestScrolller extends Scroller {
    public TestScrolller(Context context) {
        super(context);
    }

    public TestScrolller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public TestScrolller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    /**
     * View.computeScroll()方法，此方法是空实现
     * 具体逻辑:先判断computeScrollOffset()，若为true（表示滚动未结束），
     * 则执行scrollTo()方法，它会再次调用postInvalidate()，如此反复执行，直到返回值为false。
     * @return
     */
    @Override
    public boolean computeScrollOffset() {
        return super.computeScrollOffset();

    }


    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, duration);

    }

}
