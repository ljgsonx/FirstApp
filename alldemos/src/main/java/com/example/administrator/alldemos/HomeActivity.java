package com.example.administrator.alldemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/12/22 14:26
 * ---------------------------------------------------
 */
public class HomeActivity extends AppCompatActivity {
    private ListView listView;
    private String[] demos=new String[]{
            "demos 1",
            "demos 2"
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
                        startActivity(new Intent(HomeActivity.this, Demos1.class));
                        break;
                    case 1:
                        startActivity(new Intent(HomeActivity.this, Demos2.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
