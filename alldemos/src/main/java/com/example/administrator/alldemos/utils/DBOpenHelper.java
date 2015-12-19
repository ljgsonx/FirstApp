package com.example.administrator.alldemos.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * ---------------------------------------------------
 * Description: 数据库关联类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/19 10:05
 * ---------------------------------------------------
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DBName="user.db";
    private static final int Version=1;

    public DBOpenHelper(Context context) {
        super(context, DBName, null, Version);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(db);
        //可以支持版本回滚
        if(oldVersion == 1 && newVersion == 2){
            //create a new table...
        }else if(newVersion == 2 && oldVersion == 1){
            //drop the new table...
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate");
        db.execSQL("CREATE TABLE USER(_id INTEGER PRIMARY KEY AUTOINCREMENT,userName VARCHAR(30),userPwd VARCHAR(30))");
        db.execSQL("insert into user(userName,userPwd) values('1234','qwer')");
    }

}
