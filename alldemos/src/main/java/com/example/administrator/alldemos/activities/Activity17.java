package com.example.administrator.alldemos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.alldemos.R;

/**
 * ---------------------------------------------------
 * Description: context menu Test
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/11/23 9:57
 * ---------------------------------------------------
 */
public class Activity17 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("long click on me...");
        textView.setTextSize(30);
        textView.setPadding(30, 30, 30, 30);
        registerForContextMenu(textView);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.addView(textView);
        setContentView(linearLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contextmenu, menu);
        Toast.makeText(Activity17.this, "onCreateContextMenu", Toast.LENGTH_LONG).show();
    }
}
