package com.example.administrator.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *---------------------------------------------------
 * Description: 接受intent传值并返回
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp
 * Time: 2015/11/10 15:52
 *---------------------------------------------------
 */
public class SecondActivity extends AppCompatActivity{

    private TextView textView;
    private Button button;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String result=intent.getStringExtra("flag");
        setContentView(R.layout.content2);
        init();
        textView.setText(result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        textView= (TextView) findViewById(R.id.theResult);
        button = (Button) findViewById(R.id.goBack);
    }
}
