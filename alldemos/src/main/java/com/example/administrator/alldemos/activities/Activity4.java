package com.example.administrator.alldemos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.administrator.alldemos.R;

/**
 * ---------------------------------------------------
 * Description: spinner测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/17 15:58
 * ---------------------------------------------------
 */
public class Activity4 extends AppCompatActivity {
    private int status=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_spinner);
        init();
    }

    private void init() {
        Button btn1 = (Button) findViewById(R.id.btn1);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status==0) {
                    spinner.setAdapter(ArrayAdapter.createFromResource(
                            Activity4.this,
                            R.array.show2,
                            android.R.layout.simple_spinner_dropdown_item));
                    status++;
                }else{
                    spinner.setAdapter(ArrayAdapter.createFromResource(
                            Activity4.this,
                            R.array.show,
                            android.R.layout.simple_spinner_dropdown_item));
                    status--;
                }
            }
        });
    }
}
