package com.example.administrator.firstapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.firstapp.R;
import com.example.administrator.firstapp.custom.TNumberPicker;

/**
 * Created by ljgsonx on 2016/2/27.
 */
public class NumerPickerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerpicker_layout);
        TNumberPicker tNumberPicker = (TNumberPicker) findViewById(R.id.TNumberPicker);
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        tNumberPicker.getQNumberPicker().setDisplayedData(numbers);
        tNumberPicker.getQNumberPicker().setTargetValueByIndex(8);
        tNumberPicker.getQNumberPicker().setTextColor(R.color.colorAccent);
        tNumberPicker.getQNumberPicker().setTextSize(30);
    }
}
