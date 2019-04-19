package com.drcom.ListViewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private Paint mPaint;// 设置画笔变量
    private int defalutSize;

    // 自定义View有四个构造函数
    // 如果View是在Java代码里面new的，则调用第一个构造函数
    public CircleView(Context context) {
        super(context);
        // 在构造函数里初始化画笔的操作
        init();
    }
    // 该构造函数需要重写
    // 如果View是在.xml里声明的，则调用第二个构造函数
// 自定义属性是从AttributeSet参数传进来的
    public CircleView(Context context, @Nullable AttributeSet attrs) {

//        this(context, attrs,0);
         super(context,attrs);
        init();

    }

    // 不会自动调用
    // 一般是在第二个构造函数里主动调用
    // 如View有style属性时
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);

        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defalutSize = a.getDimensionPixelSize(R.styleable.CircleView_circle_color, Color.RED);

        //最后记得将TypedArray对象回收
        a.recycle();

        init();
    }

    // 画笔初始化
    private void init() {

        // 创建画笔
        mPaint = new Paint();
        // 设置画笔颜色为蓝色
        mPaint.setColor(Color.BLUE);
        // 设置画笔宽度为10px
        mPaint.setStrokeWidth(5f);
        //设置画笔模式为填充
        mPaint.setStyle(Paint.Style.FILL);

    }

    // 复写onDraw()进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**普通写法
        int width =getWidth();
        int height =getHeight();
        int r= Math.min(width,height)/2;

        //
        canvas.drawCircle(width/2,height/2,r,mPaint);
         **/

        /**
         *如果不手动设置支持padding属性，那么padding属性在自定义View中是不会生效的。

         //添加Padding属性，但不会生效
          android:padding="20dp"
         解决方案
         绘制时考虑传入的padding属性值（四个方向）。
         在自定义View类的复写onDraw（）进行设置
         **/
        // 获取传入的padding值
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();


        // 获取绘制内容的高度和宽度（考虑了四个方向的padding值）
        int width = getWidth() - paddingLeft - paddingRight ;
        int height = getHeight() - paddingTop - paddingBottom ;

        // 设置圆的半径 = 宽,高最小值的2分之1
        int r = Math.min(width, height)/2;

        // 画出圆(蓝色)
        // 圆心 = 控件的中央,半径 = 宽,高最小值的2分之1
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,r,mPaint);


    }

    /**
     * 在布局文件中，对自定义View的layout_width和layout_height不设置wrap_content，
     * 我们一般都是不需要进行处理的，但是如果要设置为wrap_content，我们需要在测量时，对宽高进行测量。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        int mWidth = 400;
        int mHeight = 400;

        // 当模式是AT_MOST（即wrap_content）时设置默认值
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        }
        // 宽 / 高任意一个模式为AT_MOST（即wrap_content）时，都设置默认值
        else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mHeight);
        }

    }
}
