package com.example.administrator.firstapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by ljgsonx on 2016/2/26.
 */
public class CustomViewGroup extends RelativeLayout {

    private int mViewGroupType;

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setViewGroupType(context,attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setViewGroupType(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setViewGroupType(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomViewGroup);
        mViewGroupType = ta.getInteger(R.styleable.CustomViewGroup_viewGroupType, 0);
        ta.recycle();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mViewGroupType == 0) {
                    Log.d("ViewGroupA---->", "onTouch" + event.getAction());
                } else {
                    Log.d("ViewGroupB---->", "onTouch:" + event.getAction());
                }
                return false;
            }

        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewGroupType == 0) {
                    Log.d("ViewGroupA---->", "onClick");
                } else {
                    Log.d("ViewGroupB---->", "onClick");
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mViewGroupType == 0) {
            Log.d("ViewGroupA---->","dispatchTouchEvent");
        }else{
            Log.d("ViewGroupB---->","dispatchTouchEvent");
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mViewGroupType == 0) {
            Log.d("ViewGroupA---->","onInterceptTouchEvent");
        }else{
            Log.d("ViewGroupB---->","onInterceptTouchEvent");
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mViewGroupType == 0) {
            Log.d("ViewGroupA---->","onTouchEvent");
        }else{
            Log.d("ViewGroupB---->","onTouchEvent");
        }
        return super.onTouchEvent(event);
    }

}
