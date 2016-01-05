package com.baiwang.bwbaselib;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *---------------------------------------------------
 * Description: 初始化contentview 及 toolbar的helper类
 * Author: ljgsonx
 * Belong to: com.baiwang.bwbaselib
 * Time: 2016/1/5 18:20
 *---------------------------------------------------
 */
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

    /*toolbar是否可拉伸*/
    private boolean isCollapsing = false;

    /*自定义layout*/
    private int mLayoutId;

    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mLayoutId = layoutId;
    }

    private void initAll(int layoutId){
        /*初始化整个内容*/
        initContentView();
        /*初始化toolbar*/
        initToolBar();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
    }

    private void initContentView() {
        mContentView = isCollapsing?mInflater.inflate(R.layout.activity_base_collapse, null)
                :mInflater.inflate(R.layout.activity_base, null);
    }

    private void initToolBar() {
        //取消CollapsingToolbarLayout的title,否则占空间
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) mContentView.findViewById(R.id.collapsingToolbarLayout);
        if(null != collapsingToolbarLayout)
            collapsingToolbarLayout.setTitleEnabled(false);

        mToolBar = (Toolbar) mContentView.findViewById(R.id.toolbar);
    }

    //将提供的自定义view添加进目标容器中
    private void initUserView(int id) {
        ViewGroup userRootView = (ViewGroup) mContentView.findViewById(R.id.userRootView);
        mUserView = mInflater.inflate(id, userRootView);
    }

    /**
     * Description: 设置是否toolbar可伸展
     * Author: ljgsonx
     * Time: 2016/1/5 18:17
     */
    public void setCollapsingToolbar(boolean isCollapsing){
        this.isCollapsing = isCollapsing;
        initAll(mLayoutId);
    }

    /**
     * Description: 获取contentview,自定义layout已被加入其中
     * Author: ljgsonx
     * Time: 2016/1/5 18:19
     */
    public View getContentView() {
        if(null == mContentView){
            initAll(mLayoutId);
        }
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }
}