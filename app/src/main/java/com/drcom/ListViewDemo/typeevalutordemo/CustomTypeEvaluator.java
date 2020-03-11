package com.drcom.ListViewDemo.typeevalutordemo;

import android.animation.TypeEvaluator;

// 实现TypeEvaluator接口

/**
 * 实现TypeEvaluator接口的目的是自定义如何 从初始点坐标 过渡 到结束点坐标；
 * 从左上角到右下角的坐标过渡逻辑。
 */
public class CustomTypeEvaluator implements TypeEvaluator {

    // 复写evaluate（）
    // 在evaluate（）里写入对象动画过渡的逻辑
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        // 参数说明
        // fraction：表示动画完成度（根据它来计算当前动画的值）
        // startValue、endValue：动画的初始值和结束值

//        ... // 写入对象动画过渡的逻辑

        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;

        float x = startPoint.getX()+ fraction*(endPoint.getX()-startPoint.getX());
        float y = startPoint.getY()+ fraction*(endPoint.getY()-startPoint.getY());

        Point point = new Point(x,y);



        return point;
        // 返回对象动画过渡的逻辑计算后的值

    }


    static class Point {

        // 设置两个变量用于记录坐标的位置
        private float x;
        private float y;

        // 构造方法用于设置坐标
        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        // get方法用于获取坐标
        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }
}
