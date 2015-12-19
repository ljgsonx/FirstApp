package com.baiwang.bwbaselib;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ToolBarHelper {

    /*上下文，创建view的时候需要用到*/
    private Context mContext;

    /*base view*/
    private View mContentView;

    /*用户定义的view*/
    private View mUserView;

    /*toolbar*/
    private Toolbar mToolBar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化整个内容*/
        initContentView();
        /*初始化toolbar*/
        initToolBar();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
    }

    private void initContentView() {
        mContentView = mInflater.inflate(R.layout.activity_base, null);
    }

    private void initToolBar() {
        mToolBar = (Toolbar) mContentView.findViewById(R.id.toolbar);
    }

    //将提供的自定义view添加进目标容器中
    private void initUserView(int id) {
        ViewGroup userRootView = (ViewGroup) mContentView.findViewById(R.id.userRootView);
        mUserView = mInflater.inflate(id, userRootView);
    }

    public View getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}