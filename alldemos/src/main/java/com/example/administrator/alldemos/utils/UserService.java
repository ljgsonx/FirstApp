package com.example.administrator.alldemos.utils;

import android.content.Context;
import android.database.Cursor;

import com.example.administrator.alldemos.beans.User;

import java.util.List;

/**
 * ---------------------------------------------------
 * Description: 用户操作业务类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/19 10:15
 * ---------------------------------------------------
 */
public class UserService {
    private DBOpenHelper dbOpenHelper = null;

    public UserService(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void save(User user) {
        dbOpenHelper.getWritableDatabase().execSQL(
                "insert into user(userName,userPwd) values(?,?)",
                new String[]{user.getUserName(),user.getUserPwd()});
    }

    public void delete(User user) {
        dbOpenHelper.getWritableDatabase().execSQL("delete from user where name=?",new String[]{user.getUserName()});
    }

    public void deleteMulti(List<String> ids){
        if (ids.size() > 0) {
            StringBuffer sb = new StringBuffer();
            Integer[] intIds = new Integer[ids.size()];
            for (int i=0;i<ids.size();i++) {
                sb.append("?,");
                intIds[i] = Integer.parseInt(ids.get(i));
            }
            sb.deleteCharAt(sb.length() - 1);
            dbOpenHelper.getWritableDatabase().execSQL("delete from user where _id in ("+sb+")",intIds);
        }
    }

    public void update(User user) {
        dbOpenHelper.getWritableDatabase().execSQL("update user set userName=? where _id=?", new String[]{user.getUserName(), user.getId() + ""});
    }

    public void updateMulti(List<String> ids,String userName) {
        if (ids.size() > 0) {
            StringBuffer sb = new StringBuffer();
            String[] data = new String[ids.size()+1];
            data[0]=userName;
            for (int i=0;i<ids.size();i++) {
                sb.append("?,");
                data[i+1] = (ids.get(i));
            }
            sb.deleteCharAt(sb.length() - 1);
            dbOpenHelper.getWritableDatabase().execSQL("update user set userName=? where _id in ("+sb+")",data);
        }
    }
    public User selectOneUser(User user) {
        Cursor cursor=dbOpenHelper.getReadableDatabase().rawQuery("select _id,userName,userPwd from user where _id=?", new String[]{user.getId() + ""});
        if (cursor.moveToNext()) {
            return new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        }
        return null;
    }

    public Cursor selectAllUser() {
        return dbOpenHelper.getReadableDatabase().rawQuery("select _id,userName,userPwd from user", new String[]{});
    }

}
