package com.drcom.ListViewDemo.animationdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.drcom.ListViewDemo.R;

public class Animation3DView extends Animation {
    private int mCenterX;

    private int mCenterY;

    private Camera mCamera;

    private ImageView mImageView;

    private Bitmap mBitmap;

    public Animation3DView(ImageView imageView, Bitmap targetBitmap) {
        this.mImageView = imageView;
        this.mBitmap = targetBitmap;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        mCenterX = width / 2;
        mCenterY = height / 2;
        setDuration(300);
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    /**
     * @param interpolatedTime 代表了动画的时间进行比。不管动画实际的持续时间如何，当动画播放时，该参数总是自动从0变化到1。
     * @param t  代表了补间动画在不同时刻对图形或组件的变形程度。
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
//        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        mCamera.save(); //保存当前状态
        if (interpolatedTime>0.5f){
            mImageView.setImageBitmap(mBitmap);
        }
        mCamera.rotateY(180f * interpolatedTime); //旋转180度
        mCamera.getMatrix(matrix);
        matrix.preTranslate(-mCenterX,-mCenterY);
        matrix.postTranslate(mCenterX,mCenterY);
        mCamera.restore();; //载入之前保存的状态

    }

    public void setTargetBitmap(Bitmap targetBitmap) {
        mBitmap = targetBitmap;
    }

    public void setImageView(ImageView imageView) {
        mImageView = imageView;
    }
}
