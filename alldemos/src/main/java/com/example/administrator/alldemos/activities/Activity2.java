package com.example.administrator.alldemos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.alldemos.beans.DataEntity;
import com.example.administrator.alldemos.utils.MyBaseAdapter;
import com.example.administrator.alldemos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------------------------------
 * Description: ListView中使用BaseAdapter的测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/12 16:04
 * ---------------------------------------------------
 */
public class Activity2 extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView);
        List<DataEntity> dataList=new ArrayList<>();
        DataEntity dataEntity = new DataEntity(1, "1", R.drawable.png1, "png1");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(2, "2", R.drawable.png2, "png2");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(3, "3", R.drawable.png3, "png3");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(4, "4", R.drawable.png4, "png4");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(5, "5", R.drawable.png5, "png5");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(6, "6", R.drawable.png6, "png6");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(7, "7", R.drawable.png7, "png7");
        dataList.add(dataEntity);
        dataEntity = new DataEntity(8, "8", R.drawable.png8, "png8");
        dataList.add(dataEntity);
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, dataList);
        listView.setAdapter(myBaseAdapter);
    }
}
