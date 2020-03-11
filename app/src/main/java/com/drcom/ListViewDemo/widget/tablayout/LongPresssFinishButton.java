package com.drcom.ListViewDemo.widget.tablayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.drcom.ListViewDemo.R;

public class LongPresssFinishButton extends RelativeLayout {
    private static final String TAG = "LongPresssFinishButton";
    private RelativeLayout buttonLayout;
    private int roundWidth; //圆环宽度
    private Paint backgroundCirclePaint;
    private Paint backgroundPaint;
    private Paint progressCirclePaint ;
    private Paint mTextPaint ;
    private ValueAnimator valueAnimator;
    private final int DURATION = 3000;
    private int progress = 100;
    private boolean  isPress ;
    private int mLastMotionX,mLastMotionY;
    private boolean isMove;
    private static final int TOUCH_SLOP = 20;
    private Bitmap mBitmap;

    public LongPresssFinishButton(Context context) {
        super(context);
        init();

    }

    public LongPresssFinishButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public LongPresssFinishButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.long_press_to_finish_button,this,true);
        buttonLayout = convertView.findViewById(R.id.button_layout);
        roundWidth = dip2px(5);

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.parseColor("#FA8072"));
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStrokeWidth(roundWidth);


        backgroundCirclePaint = new Paint();
        backgroundCirclePaint.setStyle(Paint.Style.STROKE);
        backgroundCirclePaint.setColor(Color.parseColor("#565351"));
        backgroundCirclePaint.setAntiAlias(true);
        backgroundCirclePaint.setStrokeWidth(roundWidth);

        progressCirclePaint = new Paint();
        progressCirclePaint.setStyle(Paint.Style.STROKE);
        progressCirclePaint.setColor(Color.parseColor("#ffffff"));
        progressCirclePaint.setAntiAlias(true);
        progressCirclePaint.setStrokeWidth(roundWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextSize(spToPx(13));
        mTextPaint.setColor(Color.parseColor("#ffffff"));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nav_menu);


        valueAnimator = ValueAnimator.ofInt(100,0);
        valueAnimator.setDuration(DURATION);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (int) animation.getAnimatedValue();
                postInvalidate();
                if (progress==0){
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isPress = false;
                            postInvalidate();
                            if (onFinishListener!=null){
                                onFinishListener.onFinish();
                            }
                        }
                    },50);
                }
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
                isPress = false;
//                postInvalidate();
            }
        });

    }
    private OnFinishListener onFinishListener;

    public interface OnFinishListener {
        void onFinish();
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        Log.i(TAG, "dispatchDraw:  progress = "+ progress);
//        int center = getWidth() /2 ;
//        int radius = center - roundWidth/2; //圆环半径
//        canvas.save();
//        RectF oval = new RectF(center- radius , center - radius , center+radius,center+radius);
//
//        canvas.drawCircle(center,center,radius,backgroundCirclePaint);
//
//        if (isPress){
////            canvas.drawArc(oval,0,360,false,progressCirclePaint);
//              canvas.drawArc(oval,-90,360*progress/100,false,progressCirclePaint);
//        }
//
//
////        canvas.restore();
//    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.i(TAG, "dispatchDraw:  progress = "+ progress);
        int center = getWidth() /2 ;
        int radius = center - roundWidth/2; //圆环半径
        int innerradius = center - roundWidth/2 - dip2px(8); //圆环半径
        canvas.save();
        RectF oval = new RectF(center- radius , center - radius , center+radius,center+radius);
        RectF inneroval = new RectF(center- innerradius , center - innerradius , center+innerradius,center+innerradius);

        canvas.drawCircle(center,center,radius,backgroundPaint);
        canvas.drawText("结束",center,center+mBitmap.getHeight()/2 +dip2px(4),mTextPaint);

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        canvas.drawBitmap(mBitmap,center-mBitmap.getWidth()/2,center-mBitmap.getHeight() +dip2px(2),mPaint);

        if (isPress){
            canvas.drawArc(inneroval, 0, 360, false, backgroundCirclePaint);

            canvas.drawArc(inneroval,-90,360*progress/100,false,progressCirclePaint);
        }
    }

    private void startAnim(){
        Log.i(TAG, "startAnim: ");
        if (valueAnimator!=null){
            valueAnimator.start();
        }
    }
    private void cancelAnimation() {
        isPress = false;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        progress = 100;
    }
    /**
     * dip转换px
     */
    private int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
    private int spToPx(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(x>=buttonLayout.getLeft() && x<= buttonLayout.getRight() &&
                y>= buttonLayout.getTop() && y<= buttonLayout.getBottom()){
                    mLastMotionX = x;
                    mLastMotionY = y;
                    isMove = false;
                    isPress = true;
                    startAnim();
                }
                break;
                case MotionEvent.ACTION_MOVE:
                    if (isMove){
                        break;
                    }
                    if (Math.abs(mLastMotionX -x) >TOUCH_SLOP || Math.abs(mLastMotionY - y) > TOUCH_SLOP){
                        isMove = true;
                        cancelAnimation();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    cancelAnimation();
                    break;
        }
        return true;
    }
}
