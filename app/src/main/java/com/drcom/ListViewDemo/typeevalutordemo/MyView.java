package com.drcom.ListViewDemo.typeevalutordemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public static final float RADIUS = 70f;
    private CustomTypeEvaluator.Point currentPoint ;
    private Paint mPaint;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint==null){
            currentPoint = new CustomTypeEvaluator.Point(RADIUS,RADIUS);

            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x,y,RADIUS, mPaint);


            CustomTypeEvaluator.Point startPoint = new CustomTypeEvaluator.Point(RADIUS,RADIUS);
            CustomTypeEvaluator.Point endPoint = new CustomTypeEvaluator.Point(700,1000);


            ValueAnimator animator = ValueAnimator.ofObject(new CustomTypeEvaluator(),startPoint,endPoint);
            // 参数说明
            // 参数1：TypeEvaluator 类型参数 - 使用自定义的PointEvaluator(实现了TypeEvaluator接口)
            // 参数2：初始动画的对象点
            // 参数3：结束动画的对象点

            animator.setDuration(5000);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint = (CustomTypeEvaluator.Point) animation.getAnimatedValue();

                    invalidate();
                }
            });

            animator.start();


        }else{
            // 如果坐标值不为0,则画圆
            // 所以坐标值每改变一次,就会调用onDraw()一次,就会画一次圆,从而实现动画效果

            // 在该点画一个圆:圆心 = (30,30),半径 = 30
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);
        }
    }
}
