package com.baiwang.bwbaselib;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 *---------------------------------------------------
 * Description: 封装了toolbar的base Activity
 * Author: ljgsonx
 * Belong to: com.baiwang.bwbaselib
 * Time: 2016/1/5 18:48
 *---------------------------------------------------
 */
public class BaseActivity extends AppCompatActivity {

    private ToolBarHelper mToolBarHelper;
    private Toolbar mToolbar;
    private boolean isCollapsing = false;

    private int mLayoutResID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        this.mLayoutResID = layoutResID;
        initAll();
    }

    private void initAll(){
        if(null == mToolBarHelper)
            mToolBarHelper = new ToolBarHelper(this,mLayoutResID);
        mToolBarHelper.setCollapsingToolbar(isCollapsing);
        setContentView(mToolBarHelper.getContentView());

        /*开始自定义toolbar*/
        onCreateCustomToolBar();

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimary);
    }

    /**
     * Description: 设置toolbar是否可伸展
     * Author: ljgsonx
     * Time: 2016/1/5 18:41
     */
    public void setCollapsingToolbar(boolean isCollapsing){
        this.isCollapsing = isCollapsing;
        initAll();
    }

    /**
     * Description: 自定义toolbar
     * Author: ljgsonx
     * Time: 2016/1/5 18:41
     */
    public void onCreateCustomToolBar(){
        mToolbar = mToolBarHelper.getToolBar();
        mToolbar.setTitle("");
        //mToolbar.setLogo(R.drawable.logo);
        //mToolbar.setContentInsetsRelative(50, 0);

        /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(mToolbar);

        //必须在setSupportActionBar之后设置,也可以在toolbar xml中设置app:navigationIcon,同时自定义图标
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
