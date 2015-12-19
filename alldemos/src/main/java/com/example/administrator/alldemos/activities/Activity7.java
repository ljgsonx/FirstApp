package com.example.administrator.alldemos.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.alldemos.beans.DataEntity;
import com.example.administrator.alldemos.utils.PullXmlUtil;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Description: 使用pull解析器创建xml与读取xml文件
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/18 16:14
 * ---------------------------------------------------
 */
public class Activity7 extends AppCompatActivity {

    private Button createXmlBtn;
    private Button readXmlBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createXmlBtn = new Button(this);
        createXmlBtn.setText("create xml");
        createXmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fileOutputStream = Activity7.this.getBaseContext().openFileOutput("test.xml", Context.MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    List<DataEntity> dataEntities = new ArrayList<DataEntity>();
                    dataEntities.add(new DataEntity(1, "first", 11, "img1"));
                    dataEntities.add(new DataEntity(2, "second", 22, "img2"));
                    PullXmlUtil.writeXML(dataEntities, bufferedWriter);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        readXmlBtn = new Button(this);
        readXmlBtn.setText("read xml");
        readXmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = Activity7.this.getBaseContext().openFileInput("test.xml");
                    List<DataEntity> dataEntities = PullXmlUtil.readXML(fileInputStream);

                    for (DataEntity dataEntity : dataEntities) {
                        String str=textView.getText().toString();
                        textView.setText(str+"\r\n"+dataEntity.getId() + " " + dataEntity.getName() + " " + dataEntity.getImgId() + " " + dataEntity.getImgIntro());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        textView = new TextView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(createXmlBtn);
        linearLayout.addView(readXmlBtn);
        linearLayout.addView(textView);
        setContentView(linearLayout);

    }
}
