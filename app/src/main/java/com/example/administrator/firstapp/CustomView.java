package com.example.administrator.firstapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ljgsonx on 2016/2/26.
 */
public class CustomView extends View {

    private int mViewType;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setViewGroupType(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setViewGroupType(context, attrs);
    }

    public void setViewGroupType(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        mViewType = ta.getInteger(R.styleable.CustomView_viewType, 0);
        ta.recycle();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewType == 0) {
                    Log.d("ViewA---->", "onClick");
                } else {
                    Log.d("ViewB---->", "onClick");
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mViewType == 0) {
            Log.d("ViewA---->", "dispatchTouchEvent");
        }else{
            Log.d("ViewB---->","dispatchTouchEvent");
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mViewType == 0) {
            Log.d("ViewA---->","onTouchEvent");
        }else{
            Log.d("ViewB---->","onTouchEvent");
        }
        return super.onTouchEvent(event);
    }
}
