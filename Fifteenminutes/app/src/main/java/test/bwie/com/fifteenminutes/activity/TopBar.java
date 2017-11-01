package test.bwie.com.fifteenminutes.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.bwie.com.fifteenminutes.R;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/31 16 36
 */

public class TopBar extends RelativeLayout {
    public ImageView leftimage,rightimage;
    public TextView tv;
    private String string;
    private LayoutParams lParams, rParams, mParams;
    //注册接口成员属性
    private TopBarClickListener topBarClickListener;
    private float dimension;
    private int color;
    private int id2;
    private int id3;

    public TopBar(Context context) {
        super(context);
    }
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        string = typedArray.getString(R.styleable.TopBar_title);
        dimension = typedArray.getDimension(R.styleable.TopBar_titleSize, 20);
        color = typedArray.getColor(R.styleable.TopBar_titleColor, Color.BLUE);
        id2 = typedArray.getResourceId(R.styleable.TopBar_leftimage, R.mipmap.ic_launcher);
        id3 = typedArray.getResourceId(R.styleable.TopBar_rightimage, R.mipmap.ic_launcher);
        typedArray.recycle();
        //实例元素组件
        leftimage=new ImageView(context);
        rightimage=new ImageView(context);
        tv=new TextView(context);
        //给元素组件赋值
        leftimage.setImageResource(id2);
        rightimage.setImageResource(id3);
        tv.setText(string);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(30);
        //给元素组件赋值
        lParams = new LayoutParams(80, 80);
        lParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(leftimage,lParams);
        mParams = new LayoutParams(150, 80);
        mParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(tv,mParams);
        rParams = new LayoutParams(80, 80);
        rParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightimage,rParams);
        //按钮点击监听，调用接口
        leftimage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarClickListener.leftClick();
            }
        });
        rightimage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                topBarClickListener.rightClick();
            }
        });
    }
    public interface TopBarClickListener {
        //左按钮点击
        void leftClick();
        //右按钮点击
        void rightClick();
    }
    //给接口定义set方法
    public void setTopBarClickListener(TopBarClickListener topBarClickListener) {
        this.topBarClickListener = topBarClickListener;
    }

}
