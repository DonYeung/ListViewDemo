package com.drcom.ListViewDemo.animationdemo;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CustomTweenAnimation extends Animation {

    private float mCenterX ,mCenterY;
    private Camera mCamera = new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        mCamera.save();
        // 根据interpolatedTime时间来控制x、y、z轴上的偏移
        mCamera.translate(100f-100f *interpolatedTime,150f*interpolatedTime - 150,80f-80f*interpolatedTime);

        // 设置根据interpolatedTime时间在x轴上旋转不同角度
        mCamera.rotateX(360f*interpolatedTime);

        // 设置根据interpolatedTime时间在y轴上旋转不同角度
        mCamera.rotateY(360*interpolatedTime);

        // 获取Transformation参数的Matrix对象
        Matrix matrix = t.getMatrix();

        // 将Camera所做的变换应用到Transformation的Matrix上
        mCamera.getMatrix(matrix);

        matrix.preTranslate(-mCenterX,-mCenterY);
        matrix.postTranslate(mCenterX,mCenterY);
        mCamera.restore();

    }
}
