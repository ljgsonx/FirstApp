package com.example.administrator.firstapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
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
    private int defaultIvHeight = -1;
    private int mDividerColor = -1;
    private Context mContext;

    public TNumberPicker(Context context) {
        this(context, null);
    }

    public TNumberPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        mQNumberPicker = new QNumberPicker(context);
        initDivider();
        this.addView(mQNumberPicker);
        this.addView(iv1);
        this.addView(iv2);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TNumberPicker, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.TNumberPicker_contentPadding:
                    //默认20dp
                    int contentPadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
                    setContentPadding(contentPadding);
                    break;
                case R.styleable.TNumberPicker_dividerHeight:
                    //默认2dp
                    defaultIvHeight = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.TNumberPicker_textSize:
                    //默认16sp
                    int textSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    setTextSize(textSize);
                    break;
                case R.styleable.TNumberPicker_textColor:
                    //默认灰色
                    int resId = a.getResourceId(attr, -1);
                    if (resId == -1) {
                        int color = a.getColor(attr, Color.GRAY);
                        setTextColor(color);
                    }else {
                        setTextColorByResId(resId);
                    }
                    break;
                case R.styleable.TNumberPicker_dividerColor:
                    //默认蓝色
                    resId = a.getResourceId(attr, -1);
                    if (resId == -1) {
                        int color = a.getColor(attr, Color.BLUE);
                        setDividerColor(color);
                    }else {
                        setDividerColorByResId(resId);
                    }
                    break;
                case R.styleable.TNumberPicker_targetValueIndex:
                    int index = a.getInteger(attr, 0);
                    setIndexOfTargetValue(index);
                default:
                    break;
            }
        }
    }

    private void initDivider() {
        if (iv1 == null) {
            iv1 = new ImageView(mContext);
        }
        if (iv2 == null) {
            iv2 = new ImageView(mContext);
        }
        if (mDividerColor != -1) {
            iv1.setBackgroundColor(mDividerColor);
            iv2.setBackgroundColor(mDividerColor);
        } else {
            iv1.setBackgroundColor(Color.BLUE);
            iv2.setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mQNumberPicker.layout(getPaddingLeft(), getPaddingTop(), r, b);
        int qNumberPickerHeight = mQNumberPicker.getMeasuredHeight();
        if (defaultIvHeight == -1) {
            defaultIvHeight = 2;
        }
        iv1.layout(0, (qNumberPickerHeight / 3) - defaultIvHeight / 2 + getPaddingTop(), r, (qNumberPickerHeight / 3) + defaultIvHeight / 2 + getPaddingTop());
        iv2.layout(0, 2 * (qNumberPickerHeight / 3) - defaultIvHeight / 2 + getPaddingTop(), r, 2 * (qNumberPickerHeight / 3) + defaultIvHeight / 2 + getPaddingTop());
    }

    public QNumberPicker getQNumberPicker() {
        return mQNumberPicker;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setDisplayedData(String[] data) {
        mQNumberPicker.setDisplayedData(data);
    }

    public void setIndexOfTargetValue(int index) {
        mQNumberPicker.setIndexOfTargetValue(index);
    }

    public void setTextColorByResId(int colorId) {
        mQNumberPicker.setTextColorByResId(colorId);
    }

    public void setTextColor(int color) {
        mQNumberPicker.setTextColor(color);
    }

    public void setTextSize(int size) {
        mQNumberPicker.setTextSize(size);
    }

    public void setDividerColorByResId(int colorId) {
        this.mDividerColor = mContext.getResources().getColor(colorId);
        initDivider();
    }

    public void setDividerColor(int color) {
        this.mDividerColor = color;
        initDivider();
    }

    public void setDividerHeight(int height) {
        this.defaultIvHeight = height;
    }

    public void setContentPadding(int padding) {
        mQNumberPicker.setPadding(padding, padding, padding, padding);
    }
}
