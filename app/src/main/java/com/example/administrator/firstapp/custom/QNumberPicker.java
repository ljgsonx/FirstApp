package com.example.administrator.firstapp.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.administrator.firstapp.DisplayUtil;

import java.util.ArrayList;

/**
 * Created by ljgsonx on 2016/2/27.
 */
public class QNumberPicker extends LinearLayout {

    private ArrayList<TextView> mDisplayedTvs;

    private Context mContext;
    private int mDefaultTextSize = 20;
    private int mDefaultPadding = 10;

    private int mLastY;
    private Scroller mScroller;
    private ScrollViewAdapter mScrollViewAdapter;
    private int mIndexOfTargetValue;

    private int mTvHeight;

    public QNumberPicker(Context context) {
        super(context);
        initView(context);
    }

    public QNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public QNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mDisplayedTvs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextView tv = new TextView(context);
            int paddingInPx = DisplayUtil.sp2px(context, mDefaultPadding);
            tv.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);
            tv.setTextSize(mDefaultTextSize);
            mDisplayedTvs.add(tv);
            this.addView(tv);
        }
        this.getChildAt(1).setAlpha(0.5f);
        this.getChildAt(3).setAlpha(0.5f);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(lp);
        this.setOrientation(VERTICAL);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mTvHeight = getChildAt(0).getMeasuredHeight();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mTvHeight * 3 + getPaddingBottom() + getPaddingTop() * 3;
        setLayoutParams(mlp);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(getPaddingLeft(), (i - 1) * mTvHeight + getPaddingTop() * i, r, i * mTvHeight + getPaddingTop() * i);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mTvHeight = getChildAt(0).getMeasuredHeight();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int movedPosition = getScrollY() / (mTvHeight + getPaddingTop());
                if (movedPosition == -1) {
                    TextView tv = (TextView) this.getChildAt(4);
                    int valueTag = (int) this.getChildAt(0).getTag();
                    this.removeView(tv);
                    if (valueTag == 0) valueTag = mScrollViewAdapter.getDisplayedData().length;
                    tv.setText(mScrollViewAdapter.getValueOfDisplayedView(valueTag + movedPosition));
                    tv.setTag(valueTag + movedPosition);
                    this.addView(tv, 0);
                    scrollTo(0, 0);
                }else if(movedPosition == 1) {
                    TextView tv = (TextView) this.getChildAt(0);
                    int valueTag = (int) this.getChildAt(4).getTag();
                    this.removeView(tv);
                    tv.setText(mScrollViewAdapter.getValueOfDisplayedView(valueTag + movedPosition));
                    tv.setTag(valueTag + movedPosition);
                    this.addView(tv, 4);
                    scrollTo(0, 0);
                }else {
                    setAlphaOfTv(getScrollY());
                    //防止移动过大
                    int dy = mLastY - y;
                    dy = dy % (mTvHeight + getPaddingTop());
                    scrollBy(0, dy);
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(getScrollY()) < (mTvHeight + getPaddingTop()) / 2) {
                    mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
                } else {
                    float hasScrolled = Math.abs(getScrollY());
                    float needed = mTvHeight + getPaddingTop() - hasScrolled;
                    needed = getScrollY() < 0 ? -needed : needed;
                    mScroller.startScroll(0, getScrollY(), 0, (int) needed);
                }
                break;
        }
        postInvalidate();
        return true;
    }

    private void setAlphaOfTv(int currentY) {
        float scale1 = 0.2f + Math.abs((float) currentY / (mTvHeight + getPaddingTop())) * 0.8f; //0.2~1
        float scale2 = 1.2f - scale1; //1~0.2
        float scale3 = 0.2f - Math.abs((float) currentY / (mTvHeight + getPaddingTop())) * 0.2f; //0.2~0
        float scale4 = 0.2f - scale3;//0~0.2
        if (currentY > 0) {
            this.getChildAt(1).setAlpha(scale3);
            this.getChildAt(2).setAlpha(scale2);
            this.getChildAt(3).setAlpha(scale1);
            this.getChildAt(4).setAlpha(scale4);
        } else {
            this.getChildAt(0).setAlpha(scale4);
            this.getChildAt(1).setAlpha(scale1);
            this.getChildAt(2).setAlpha(scale2);
            this.getChildAt(3).setAlpha(scale3);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int currY = mScroller.getCurrY();
            scrollTo(0, currY);
            setAlphaOfTv(currY);
            postInvalidate();
        }
    }

    public void setDisplayedData(String[] data) {
        mScrollViewAdapter = new ScrollViewAdapter();
        mScrollViewAdapter.setDisplayedData(data);
        setValues();
    }

    public void setTextSize(int textSize) {
        for (TextView tv : mDisplayedTvs) {
            tv.setTextSize(textSize);
        }
    }

    public void setTextColorByResId(int resId) {
        for (TextView tv : mDisplayedTvs) {
            tv.setTextColor(mContext.getResources().getColor(resId));
        }
    }

    public void setTextColor(int color) {
        for (TextView tv : mDisplayedTvs) {
            tv.setTextColor(color);
        }
    }

    public void setValues() {
        if(mScrollViewAdapter == null) return;
        if (mIndexOfTargetValue < 0 || mIndexOfTargetValue > mScrollViewAdapter.getDisplayedData().length-1) {
            throw new ArrayIndexOutOfBoundsException("the index of target value is out of the size of data!");
        }
        if (mIndexOfTargetValue < 2) {
            mIndexOfTargetValue += mScrollViewAdapter.getDisplayedData().length;
        }
        for (int i = 0; i < mDisplayedTvs.size(); i++) {
            mDisplayedTvs.get(i).setText(mScrollViewAdapter.getValueOfDisplayedView(mIndexOfTargetValue - 2 + i));
            mDisplayedTvs.get(i).setTag(mIndexOfTargetValue - 2 + i);
        }
    }

    public void setIndexOfTargetValue(int index) {
        this.mIndexOfTargetValue = index;
        setValues();
    }

    class ScrollViewAdapter{

        private String[] displayedData;

        public String getValueOfDisplayedView(int positionWithTimes) {
            int position = positionWithTimes % displayedData.length;
            return displayedData[position];
        }

        public void setDisplayedData(String[] data) {
            this.displayedData = data;
        }

        public String[] getDisplayedData() {
            return displayedData;
        }
    }

}
