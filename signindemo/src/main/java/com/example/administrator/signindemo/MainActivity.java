package com.example.administrator.signindemo;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.signindemo.network.NetWorkContext;
import com.example.administrator.signindemo.network.NetWorkEngine;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 *---------------------------------------------------
 * Description: 使用网络框架okhttp,进行登录功能测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.signindemo
 * Time: 2015/12/19 12:18
 *---------------------------------------------------
 */
public class MainActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListener,NetWorkEngine.OnRequestEndListener{

    private TextView loginTitleTv;
    private boolean isShowToolbar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked Me", Toast.LENGTH_LONG).show();
            }
        });
        setSupportActionBar(toolbar);


        //隐藏状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimary);//通知栏所需颜色
        //tintManager.setStatusBarAlpha(0.0f);

        loginTitleTv = (TextView) findViewById(R.id.loginTitle);
        loginTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowToolbar){
                    toolbar.setVisibility(View.VISIBLE);
                    isShowToolbar = false;
                }else{
                    toolbar.setVisibility(View.GONE);
                    isShowToolbar = true;
                }
            }
        });

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.loginFragment, new LoginFragment(), "LoginFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(String userName, String userPwd) {
        NetWorkContext netWorkContext = new NetWorkContext();
        netWorkContext.LOGIN_URL += "?userName=" + userName + "&userPwd=" + userPwd;
        new NetWorkEngine(netWorkContext, this).doRequest();
        Toast.makeText(this, "Request started", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestEnd(String responseBody,int status) {

        Toast.makeText(this, "Request Finished", Toast.LENGTH_LONG).show();
        Fragment fragment = HomeFragment.newInstance("Welcome,");
        Bundle args = fragment.getArguments();
        args.putString("userName", responseBody);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.loginFragment, fragment)
                .addToBackStack(null)
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                .commit();
        if(status == 0){
            loginTitleTv.setText("登录成功");
        }else{
            loginTitleTv.setText("登录失败");
        }
    }

    @Override
    public void onBackPressed() {
        int num =  getFragmentManager().getBackStackEntryCount();
        if(num > 1){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }

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
