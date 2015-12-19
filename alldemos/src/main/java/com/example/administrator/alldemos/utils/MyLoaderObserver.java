package com.example.administrator.alldemos.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by ljgsonx on 2015/12/5.
 */
public class MyLoaderObserver extends BroadcastReceiver {

    private MyLoader mMyLoader;

    public final static String ACTION_USER_DATACHANGE = "com.example.alldemos.userdatachange";

    public MyLoaderObserver(MyLoader myLoader) {
        this.mMyLoader = myLoader;
        IntentFilter intentFilter = new IntentFilter(ACTION_USER_DATACHANGE);
        //动态注册广播接受者
        myLoader.getContext().registerReceiver(this, intentFilter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mMyLoader.onContentChanged();
    }
}
