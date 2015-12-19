package com.example.administrator.alldemos.activities;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.alldemos.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ---------------------------------------------------
 * Description: 文件系统的增删改查测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/18 11:25
 * ---------------------------------------------------
 */
public class Activity5 extends AppCompatActivity {

    private final static String fileName="create_test.txt";

    LinearLayout linearLayout1;
    LinearLayout linearLayout2;

    private Button createFileBtn;
    private Button readFileBtn;
    private Button clearFileBtn;
    private Button addContentsBtn;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createFileBtn = new Button(this);
        createFileBtn.setId(R.id.createFileBtn);
        createFileBtn.setText("create a file and add contents!");
        createFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
                Toast.makeText(Activity5.this, "create Success!", Toast.LENGTH_LONG).show();
            }
        });
        readFileBtn = new Button(this);
        readFileBtn.setId(R.id.readFileBtn);
        readFileBtn.setText("Read the file");
        readFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(readFile());
            }

        });
        clearFileBtn = new Button(this);
        clearFileBtn.setText("clear the File");
        clearFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFile();
                textView.setText(readFile());
            }
        });
        textView = new TextView(this);
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(25);
        editText = new EditText(this);
        editText.setHint("Input something...");
        editText.setWidth(1);
        editText.setLayoutParams(new LinearLayout.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 0.7f));
        addContentsBtn = new Button(this);
        addContentsBtn.setText("add it!");
        addContentsBtn.setLayoutParams(new LinearLayout.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 0.3f));
        addContentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContentsToFile(editText.getText().toString());
                textView.setText(readFile());
            }
        });
        linearLayout2 = new LinearLayout(this);
        linearLayout2.addView(editText);
        linearLayout2.addView(addContentsBtn);
        linearLayout1 = new LinearLayout(this);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.addView(createFileBtn);
        linearLayout1.addView(readFileBtn);
        linearLayout1.addView(clearFileBtn);
        linearLayout1.addView(linearLayout2);
        linearLayout1.addView(textView);
        setContentView(linearLayout1);

    }

    private String readFile() {
        byte[] data=null;
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        try {
            //也可以用new FileInputStream("/data/data/com.example.administrator.alldemos/create_test.text");
            fileInputStream = this.getBaseContext().openFileInput(fileName);
            byteArrayOutputStream = new ByteArrayOutputStream();
            //一次读取1kb的字节
            byte[] buffer = new byte[1024];
            int len = -1;
            //这种读取流方式在网络读取时容易卡住死循环
            while ((len = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            data = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data);
    }

    private void createFile() {
        try {
            FileOutputStream fileOutputStream = this.openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write("this is the content of the file\r\n".getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addContentsToFile(String contents) {
        try {
            FileOutputStream fileOutputStream = this.openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(contents.getBytes());
            fileOutputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearFile() {
        try {
            FileOutputStream fileOutputStream = this.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write("".getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
