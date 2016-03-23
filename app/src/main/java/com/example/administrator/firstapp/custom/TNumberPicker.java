package com.example.administrator.firstapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.firstapp.R;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp.custom
 * Time: 2016/3/23 14:52
 * ---------------------------------------------------
 */
public class TNumberPicker extends RelativeLayout {

    private QNumberPicker mQNumberPicker;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private int mHeight;
    private int defaultIvHeight = 3;

    public TNumberPicker(Context context) {
        super(context);
        initView(context);
    }

    public TNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mQNumberPicker = new QNumberPicker(context);
        iv1 = new ImageView(context);
        iv2 = new ImageView(context);
        iv3 = new ImageView(context);
        iv1.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        iv2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        iv3.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        this.addView(mQNumberPicker);
        this.addView(iv1);
        this.addView(iv2);
        this.addView(iv3);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mHeight = b - t;
        System.out.println("---------");
        System.out.println(getPaddingLeft() + " " + getPaddingTop() + " " + getPaddingRight() + " " + getPaddingBottom());
        mQNumberPicker.layout(getPaddingLeft() / 2, getPaddingTop() / 2, getPaddingRight() / 2 + r, getPaddingBottom() / 2 + b);
        iv1.layout(0, (mHeight / 3) - defaultIvHeight / 2, r, (mHeight / 3) + defaultIvHeight / 2);
        iv2.layout(0, 2 * (mHeight / 3) - defaultIvHeight / 2, r, 2 * (mHeight / 3) + defaultIvHeight / 2);
    }

    public QNumberPicker getQNumberPicker() {
        return mQNumberPicker;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
