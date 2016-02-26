package com.example.administrator.alldemos.supports;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.supports
 * Time: 2016/2/26 13:42
 * ---------------------------------------------------
 */
public class CustomMusic extends View {

    private int mRectCount = 10;
    private int mWidth = 10;
    private int mRectWidth;
    private int mRectHeight;
    private int offset = 2;
    private double mRandom;
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private ViewThread mViewThread;
    private ViewHandler mViewHandler = new ViewHandler(this);

    static class ViewHandler extends Handler{
        WeakReference<CustomMusic> weakReference;
        public ViewHandler(CustomMusic customMusic) {
            this.weakReference = new WeakReference<>(customMusic);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                CustomMusic customMusic = weakReference.get();
                if (customMusic != null) {
                    customMusic.invalidate();
                }
            }
        }
    }

    public CustomMusic(Context context) {
        super(context);
    }

    public CustomMusic(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMusic(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMusic(canvas);
        if (mViewThread == null) {
            mViewThread = new ViewThread();
            mViewThread.start();
        }
    }

    private void drawMusic(Canvas canvas){
        for (int i = 0; i < mRectCount; i++) {
            mRandom = Math.random();
            float currentHeight = (float)(mRectHeight*mRandom);
            canvas.drawRect(
                    (float) (mWidth * 0.2 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.2 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
        }
    }

    class ViewThread extends Thread {

        @Override
        public void run() {
            while (true) {
                mViewHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mPaint = new Paint();
        mRectWidth = (int) (mWidth * 0.8 / mRectCount);
        mLinearGradient = new LinearGradient(
                0, 0, mRectWidth,
                mRectHeight, Color.YELLOW, Color.BLUE,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(mLinearGradient);
    }

}
