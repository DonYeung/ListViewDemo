package com.drcom.ListViewDemo.animationdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drcom.ListViewDemo.R;

public class Animation3DActivity extends AppCompatActivity {
    private ImageView icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation3d_demo);
        icon  = findViewById(R.id.icon);

        //一、
         /*Bitmap targetBitmap
                  = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
         Animation3DView animation = new Animation3DView(icon,targetBitmap);
         animation.setDuration(5000);
//         animation.setRepeatCount(5);
        animation.setRepeatMode(Animation.RESTART);
         icon.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                icon.startAnimation(animation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });*/

         //二、
        CustomTweenAnimation customTweenAnimation = new CustomTweenAnimation();
        customTweenAnimation.setDuration(5000);
        // 设置动画结束后效果保留
        customTweenAnimation.setFillAfter(true);
        // 设置匀速变换
        customTweenAnimation.setInterpolator(new LinearInterpolator());
        icon.startAnimation(customTweenAnimation);




    }
}
