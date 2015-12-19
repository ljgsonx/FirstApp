package com.example.administrator.alldemos.utils;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;


/**
 * ---------------------------------------------------
 * Description: 用户contentProvider
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/20 10:46
 * ---------------------------------------------------
 */
public class UserProvider extends ContentProvider {

    public static final String USER_TYPE = "com.example.administrator.alldemos.dir/user";
    public static final String USER_ITEM_TYPE = "com.example.administrator.alldemos.item/user";

    public static final String AUTHORITY = "com.example.administrator.alldemos.userprovider";

    public static final String DATA_CHANGE_ACTION = "com.example.alldemos.userdata_change";

    public static final int USERS = 1;
    public static final int USER = 2;

    public static final Uri USER_URI = Uri.parse("content://" + AUTHORITY + "/user");

    private DBOpenHelper dbOpenHelper;
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "user", USERS);
        uriMatcher.addURI(AUTHORITY, "user/#", USER);
    }

    @Override
    public boolean onCreate() {
        dbOpenHelper = new DBOpenHelper(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case USERS:
                return db.query("user", projection, selection, selectionArgs, null, null, sortOrder);
            case USER:
                long userId = ContentUris.parseId(uri);
                String theId = "_id=" + userId;
                theId += !("".equals(selection) || (null == selection)) ? "and (" + selection + ")" : "";
                return db.query("user", projection, theId, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USERS:
                return USER_TYPE;
            case USER:
                return USER_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        //发送广播,通知观察者数据已改变
        getContext().sendBroadcast(new Intent(MyLoaderObserver.ACTION_USER_DATACHANGE));

        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        long id = 0;
        switch (uriMatcher.match(uri)) {
            case USERS:
                id = db.insert("user", "userName", values);
                return ContentUris.withAppendedId(uri, id);
            case USER:
                id = db.insert("user", "userName", values);
                String path = uri.toString();
                return Uri.parse(path.substring(0, path.lastIndexOf("/")) + id);
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case USERS:
                count = db.delete("user", selection, selectionArgs);
                break;
            case USER:
                long userId = ContentUris.parseId(uri);
                String theId = "_id=" + userId;
                theId += !("".equals(selection) || (null == selection)) ? "and (" + selection + ")" : "";
                count = db.delete("user", theId, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        db.close();
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case USERS:
                count = db.update("user", values, selection, selectionArgs);
                break;
            case USER:
                long userId = ContentUris.parseId(uri);
                String theId = "_id=" + userId;
                theId += !("".equals(selection) || (null == selection)) ? "and (" + selection + ")" : "";
                count = db.update("user", values, theId, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        db.close();
        return count;
    }
}
