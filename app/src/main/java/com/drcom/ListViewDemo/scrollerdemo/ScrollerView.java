package com.drcom.ListViewDemo.scrollerdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

import androidx.annotation.Nullable;

public class ScrollerView extends View {
    Scroller scroller ;
    public ScrollerView(Context context) {
        super(context);
         scroller = new Scroller(getContext());

    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
         scroller = new Scroller(getContext());

    }

    private void smoothScrollTo(int dstX, int dstY){
        int scrollX = getScrollX();//View的左边缘到其内容左边缘的距离
        int scrollY = getScrollY();//View的上边缘到其内容上边缘的距离
        int  deltaX =dstX - scrollX;//x方向滑动的位移量
        int  deltaY =dstY - scrollY;//y方向滑动的位移量

        scroller.startScroll(scrollX,scrollY,deltaX,deltaY);//开始滑动
        invalidate();//刷新界面

    }

    /**
     * //计算一段时间间隔内偏移的距离，并返回是否滚动结束的标记
     */
    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();//通过不断的重绘不断的调用computeScroll方法

        }
    }
}
