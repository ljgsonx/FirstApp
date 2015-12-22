package com.example.administrator.alldemos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.alldemos.Demos1;
import com.example.administrator.alldemos.R;

/**
 *---------------------------------------------------
 * Description: 删除与添加快捷方式
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/12/7 9:45
 *---------------------------------------------------
 */
public class Activity19 extends AppCompatActivity {

    private Button mButton1;
    private Button mButton2;
    private LinearLayout mLinearLayout;

    private final static String ACTION_START_SHORTCUT = "com.example.shortcut";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mButton1 = new Button(this);
        mButton2 = new Button(this);
        mLinearLayout = new LinearLayout(this);

        mButton1.setText("添加快捷方式");
        mButton2.setText("删除快捷方式");

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                _intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
                _intent.putExtra("duplicate", false);
                _intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(Activity19.this, R.drawable.bmp2));
                _intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Activity19.this,Demos1.class).setAction(ACTION_START_SHORTCUT));
                sendBroadcast(_intent);
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
                _intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
                _intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Activity19.this,Demos1.class).setAction(ACTION_START_SHORTCUT));
                sendBroadcast(_intent);
            }
        });

        mLinearLayout.addView(mButton1);
        mLinearLayout.addView(mButton2);
        setContentView(mLinearLayout);
    }
}
