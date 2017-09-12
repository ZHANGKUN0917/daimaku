package com.bwie.hp.zhangkun20170904;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类的用途：
 *
 * @author 张堃
 * @date 2017/9/4 08 47
 */

public class CustomCircle extends View {
    //颜色
    private int color;
    //宽度
    private int i;
    //速度
    private int speed;
    //画笔
    private Paint paint;
    private TypedArray array1;

    public CustomCircle(Context context) {
        super(context);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    public CustomCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, @Nullable AttributeSet attrs) {
        array1 = context.obtainStyledAttributes(attrs, R.styleable.CustomCircle);
        int color=array1.getColor(R.styleable.CustomCircle_viewcolor,Color.RED);
        int i = array1.getInt(R.styleable.CustomCircle_width, 20);
        int spped=array1.getInt(R.styleable.CustomCircle_spped,30);
        setBackground(new ColorDrawable(color));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint=new Paint();
        paint.setStrokeWidth(i);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300,400,200,paint);
        paint.setColor(color);
        array1.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
