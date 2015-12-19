package com.example.administrator.alldemos.activities;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.alldemos.R;
import com.example.administrator.alldemos.utils.MyLoader;
import com.example.administrator.alldemos.utils.MyLoaderObserver;

/**
 * ---------------------------------------------------
 * Description: LoaderManager使用自定义Loader测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/12/4 17:39
 * ---------------------------------------------------
 */
public class Activity21 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final static Uri URI_USER_PROVIDER= Uri.parse("content://com.example.administrator.alldemos.userprovider/user");
    private final static int LOADER_ID = 1;
    private final static int CONTACT_LOADER_ID = 2;
    private final static String[] mUserColumn = {"_id","userName"};
    private final static int[] mUserColumnView = {R.id.activity2_tv1,R.id.activity2_tv2};

    private final static String[] mContactColumn= {ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME};

    private ListView mListView;
    private Button mButton1;
    private Button mButton2;

    private int mLoaderType = 0;
    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    private SimpleCursorAdapter mSimpleCursorAdapter;

    private LoaderManager mLoaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Activity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //初始化LoaderManager
        //可以先初始化,再添加adapter,因为loader在主线程不被占用时才开始加载
        mCallbacks = this;
        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(LOADER_ID, null, mCallbacks);

        //初始化控件
        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listView);
        LinearLayout linearLayout = (LinearLayout)mListView.getParent();
        mButton1 = new Button(this);
        mButton1.setText("添加");
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("userName", "new user");
                contentValues.put("userPwd", "-------");
                Activity21.this.getContentResolver().insert(URI_USER_PROVIDER, contentValues);
            }
        });
        mButton2 = new Button(this);
        mButton2.setText("切换列表");
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoaderType = mLoaderType == 0 ? 1 : 0;
                if(mLoaderType == 0){
                    setAdapter(mUserColumn);
                    mLoaderManager.initLoader(LOADER_ID, null, mCallbacks);
                }else{
                    setAdapter(mContactColumn);
                    mLoaderManager.initLoader(CONTACT_LOADER_ID, null, mCallbacks);
                }
            }
        });
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(mButton1,0);
        linearLayout.addView(mButton2,1);
        setAdapter(mUserColumn);
    }

    public void setAdapter(String[] dataColumn){
        mSimpleCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.activity2_listview_layout,
                null,
                dataColumn,
                mUserColumnView, 0);
        mListView.setAdapter(mSimpleCursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i("------>", "onCreateLoader");
        return new MyLoader(this,mLoaderType);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i("------>", "onLoadFinished");
        //防止加载过来的数据是另一种数据源发生改变时引起的adapter自动更新,
        //所以加一层判断,判断数据源是当前listview想要的数据源
        if(mLoaderType == 0 && loader.getId() == LOADER_ID){
            mSimpleCursorAdapter.swapCursor(data);
        }else if(mLoaderType == 1 && loader.getId() == CONTACT_LOADER_ID){
            mSimpleCursorAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i("------>", "onLoaderReset");
        mSimpleCursorAdapter.swapCursor(null);
    }
}
