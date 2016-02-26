package com.example.administrator.alldemos.supports;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.administrator.alldemos.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * ---------------------------------------------------
 * Description: 自定义view测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.supports
 * Time: 2015/12/22 15:06
 * ---------------------------------------------------
 */
public class MyCustomView extends View {

    /**
     * 文本
     */
    private String mTitleText;
    /**
     * 文本的颜色
     */
    private int mTitleTextColor;
    /**
     * 文本的大小
     */
    private int mTitleTextSize;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;

    public MyCustomView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public MyCustomView(Context context)
    {
        this(context, null);
    }

    /**
     * 获得自定义的样式属性
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyCustomView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustomView, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++)
        {
            int attr = a.getIndex(i);
            switch (attr)
            {
                case R.styleable.MyCustomView_textValue:
                    mTitleText = a.getString(attr);
                    break;
                case R.styleable.MyCustomView_textColor:
                    // 默认颜色设置为绿色
                    mTitleTextColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.MyCustomView_textSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    Log.i("------>", mTitleTextSize + "");
                    break;
                default:
                    break;
            }
        }
        a.recycle();

        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        this.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTitleText = randomText();
                postInvalidate();
            }

        });
    }
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }
        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;
        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode)
        {
            case MeasureSpec.EXACTLY:// 明确指定了大小或设成match_parent
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                width = getPaddingLeft() + getPaddingRight() + mBound.width();
                break;
        }

        /**
         * 设置高度
         */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode)
        {
            case MeasureSpec.EXACTLY:// 明确指定了
                height = getPaddingTop() + getPaddingBottom() + specSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                height = getPaddingTop() + getPaddingBottom() + mBound.height();
                break;
        }
        setMeasuredDimension(width, height);
        System.out.println("--->"+width +" "+height);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        System.out.println(getWidth() +"++++++"+getHeight());
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
        int startX = (getWidth() - mBound.width()) / 2;
        int startY = (getHeight() + mBound.height()) / 2;
        mPaint.setColor(Color.CYAN);
        //画背景
        canvas.drawRect(0, 0, mBound.width(), mBound.height(), mPaint);
        mPaint.setColor(mTitleTextColor);
        //画文字0
        canvas.drawText(mTitleText, startX, startY, mPaint);
    }
}
