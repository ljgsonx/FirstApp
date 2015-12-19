package com.example.administrator.alldemos.activities;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.alldemos.R;

/**
 * ---------------------------------------------------
 * Description: LoaderManager测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/12/4 17:39
 * ---------------------------------------------------
 */
public class Activity20 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final static Uri URI_USER_PROVIDER= Uri.parse("content://com.example.administrator.alldemos.userprovider/user");
    private final static int LOADER_ID = 1;
    private final static String[] mUserColumn = {"_id","userName"};
    private final static int[] mUserColumnView = {R.id.activity2_tv1,R.id.activity2_tv2};

    private ListView mListView;
    private Button mButton;

    private LoaderManager.LoaderCallbacks<Cursor> mCallbacks;

    private SimpleCursorAdapter mSimpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mListView = (ListView) findViewById(R.id.listView);
        LinearLayout linearLayout = (LinearLayout)mListView.getParent();
        mButton = new Button(this);
        mButton.setText("添加");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("userName", "new user");
                contentValues.put("userPwd", "-------");
                Activity20.this.getContentResolver().insert(URI_USER_PROVIDER, contentValues);
            }
        });
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(mButton);

        mSimpleCursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.activity2_listview_layout,
                null,
                mUserColumn,
                mUserColumnView, 0);
        mListView.setAdapter(mSimpleCursorAdapter);

        mCallbacks = this;
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, mCallbacks);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i("------>", "onCreateLoader");
        return new CursorLoader(Activity20.this, URI_USER_PROVIDER, mUserColumn, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i("------>", "onLoadFinished");
        switch (loader.getId()) {
            case LOADER_ID:
                mSimpleCursorAdapter.swapCursor(data);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i("------>", "onLoaderReset");
        mSimpleCursorAdapter.swapCursor(null);
    }
}
