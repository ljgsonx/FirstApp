package com.example.administrator.alldemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.alldemos.activities_demo2.Activity1_d2;
import com.example.administrator.alldemos.activities_demo2.Activity2_d2;
import com.example.administrator.alldemos.activities_demo2.Activity3_d2;
import com.example.administrator.alldemos.activities_demo2.Activity4_d2;
import com.example.administrator.alldemos.activities_demo2.Activity5_d2;

/**
 * ---------------------------------------------------
 * Description: 第二批demo
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/12/22 14:29
 * ---------------------------------------------------
 */
public class Demos2 extends AppCompatActivity {
    private ListView listView;
    private String[] demos=new String[]{
            "custom view",
            "ultra pull to refresh",
            "footer test",
            "baidumap test",
            "StaggeredGridLayoutManager Test"

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demos));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Demos2.this, Activity1_d2.class));
                        break;
                    case 1:
                        startActivity(new Intent(Demos2.this, Activity2_d2.class));
                        break;
                    case 2:
                        startActivity(new Intent(Demos2.this, Activity3_d2.class));
                        break;
                    case 3:
                        startActivity(new Intent(Demos2.this, Activity4_d2.class));
                        break;
                    case 4:
                        startActivity(new Intent(Demos2.this, Activity5_d2.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
