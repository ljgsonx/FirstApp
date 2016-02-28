package com.example.administrator.firstapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.firstapp.R;
import com.example.administrator.firstapp.custom.QNumberPicker;

/**
 * Created by ljgsonx on 2016/2/27.
 */
public class NumerPickerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numerpicker_layout);
        QNumberPicker qNumberPicker = (QNumberPicker) findViewById(R.id.QNumberPicker);
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        qNumberPicker.setDisplayedData(numbers);
        qNumberPicker.setTargetValueByIndex(8);
        qNumberPicker.setTextColor(R.color.colorAccent);
        qNumberPicker.setTextSize(30);
    }
}
