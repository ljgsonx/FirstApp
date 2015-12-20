package com.example.administrator.alldemos.utils;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by ljgsonx on 2015/12/4.
 */
public class MyLoader extends AsyncTaskLoader<Cursor>{

    private Cursor mCursorData;

    //加载自定义数据库数据
    private final static Uri URI_USER_PROVIDER= Uri.parse("content://com.example.administrator.alldemos.userprovider/user");
    private final static String[] mUserColumn = {"_id","userName"};

    //加载联系人数据
    private final static Uri URI_CONTENT= ContactsContract.Contacts.CONTENT_URI;
    private final static String[] mContactColumn= {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME};

    private int mLoaderType = 0;

    private MyLoaderObserver mMyLoaderObserver;

    public  MyLoader(Context context,int loaderType) {
        super(context);
        mLoaderType = loaderType;
    }

    @Override
    protected void onStartLoading() {
        System.out.println("onStartLoading");
        if (null != mCursorData) {
            System.out.println("deliver Result--2");
            deliverResult(mCursorData);
        }
        //只将加载user数据类型的loader实例注册给观察者
        if(mLoaderType == 0){
            if (null == mMyLoaderObserver) {
                mMyLoaderObserver = new MyLoaderObserver(this);
            }
        }

        if (takeContentChanged() || null == mCursorData) {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        System.out.println("loadInBackground");
        if(mLoaderType == 0){
            return getContext().getContentResolver().query(URI_USER_PROVIDER, mUserColumn, null, null, "_id asc");
        }else{
            return getContext().getContentResolver().query(URI_CONTENT, mContactColumn, null, null, "_id asc");
        }
    }

    @Override
    public void deliverResult(Cursor data) {
        System.out.println("deliverResult");
        if (isReset()) {
            System.out.println("ready to close cursor--1");
            releaseResources(data);
            return;
        }
        Cursor oldData = mCursorData;
        mCursorData = data;
        if (isStarted()) {
            System.out.println("deliver Result--1");
            super.deliverResult(data);
        }

        if (null != oldData && oldData != data) {
            System.out.println("ready to close cursor--2");
            releaseResources(oldData);
        }

    }

    @Override
    protected void onReset() {
        System.out.println("onReset");
        onStopLoading();
        if (null != mCursorData) {
            System.out.println("ready to close cursor--4");
            releaseResources(mCursorData);
            mCursorData = null;
        }

        if (null != mMyLoaderObserver) {
            mMyLoaderObserver = null;
        }
    }

    @Override
    public void onCanceled(Cursor data) {
        System.out.println("onCanceled");
        super.onCanceled(data);
        System.out.println("ready to close cursor--3");
        releaseResources(data);
    }

    @Override
    protected void onStopLoading() {
        System.out.println("onStopLoading");
        cancelLoad();
    }

    private void releaseResources(Cursor cursor){
        System.out.println("close cursor");
        cursor.close();
    }
}
