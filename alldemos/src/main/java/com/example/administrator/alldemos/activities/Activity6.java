package com.example.administrator.alldemos.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ---------------------------------------------------
 * Description: 读取sd卡的文件的测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/18 15:02
 * ---------------------------------------------------
 */
public class Activity6 extends AppCompatActivity {

    private Button createSdCardFile;
    private Button readFileFromSdCard;
    private EditText editText;
    private LinearLayout linearLayout;

    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSdCardFile = new Button(this);
        createSdCardFile.setText("Create sdCardFile");
        file = new File(Environment.getExternalStorageDirectory(), "sdCardFileTest.txt");
        createSdCardFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

                    try {
                        FileOutputStream outputStream = new FileOutputStream(file);
                        outputStream.write("this is the content of the file".getBytes());
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        readFileFromSdCard = new Button(this);
        readFileFromSdCard.setText("Read File From sdCard");
        readFileFromSdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    //一次读取1kb的字节
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    //这种读取流方式在网络读取时容易卡住死循环
                    while ((len = fileInputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                    String data = new String(byteArrayOutputStream.toByteArray());
                    editText.setText(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        editText = new EditText(this);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(createSdCardFile);
        linearLayout.addView(readFileFromSdCard);
        linearLayout.addView(editText);
        setContentView(linearLayout);
    }
}
