package com.example.administrator.alldemos.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ---------------------------------------------------
 * Description: SharedPreferences测试类，
 *              SharedPreferences是一个轻量级的存储类，特别适合用于保存软件配置参数。
 *              使用SharedPreferences保存数据，其背后是用xml文件存放数据，
 *              文件存放在/data/data/<package name>/shared_prefs目录下
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/18 17:23
 * ---------------------------------------------------
 */
public class Activity8 extends AppCompatActivity {

    private Button addSharedPreferencesBtn;
    private Button readSharedPreferencesBtn;
    private SharedPreferences preferences;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSharedPreferencesBtn = new Button(this);
        addSharedPreferencesBtn.setText("add SharedPreferencesBtn");
        preferences = getSharedPreferences("prefer_test", Context.MODE_APPEND);
        addSharedPreferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", "uuuuuu");
                editor.putInt("id", 11);
                editor.commit();
            }
        });
        readSharedPreferencesBtn = new Button(this);
        readSharedPreferencesBtn.setText("read SharedPreferencesBtn");
        readSharedPreferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=preferences.getString("name","");
                Integer id = preferences.getInt("id", 1);
                textView.setText(name + "   " + id);
            }
        });
        textView = new TextView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(addSharedPreferencesBtn);
        linearLayout.addView(readSharedPreferencesBtn);
        linearLayout.addView(textView);
        setContentView(linearLayout);
    }
}
