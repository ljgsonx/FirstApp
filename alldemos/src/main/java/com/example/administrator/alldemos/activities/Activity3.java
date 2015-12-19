package com.example.administrator.alldemos.activities;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.alldemos.R;

/**
 * ---------------------------------------------------
 * Description: simpleCursorAdapter测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/17 14:48
 * ---------------------------------------------------
 */
public class Activity3 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView);
        CursorLoader cursorLoader = new CursorLoader(
                this,
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        listView.setAdapter(new SimpleCursorAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                cursorLoader.loadInBackground(),
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
