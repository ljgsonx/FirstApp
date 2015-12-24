package com.example.administrator.alldemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.alldemos.activities.Activity1;
import com.example.administrator.alldemos.activities.Activity10;
import com.example.administrator.alldemos.activities.Activity11;
import com.example.administrator.alldemos.activities.Activity12;
import com.example.administrator.alldemos.activities.Activity13;
import com.example.administrator.alldemos.activities.Activity14;
import com.example.administrator.alldemos.activities.Activity15;
import com.example.administrator.alldemos.activities.Activity16;
import com.example.administrator.alldemos.activities.Activity17;
import com.example.administrator.alldemos.activities.Activity18;
import com.example.administrator.alldemos.activities.Activity19;
import com.example.administrator.alldemos.activities.Activity2;
import com.example.administrator.alldemos.activities.Activity20;
import com.example.administrator.alldemos.activities.Activity21;
import com.example.administrator.alldemos.activities.Activity22;
import com.example.administrator.alldemos.activities.Activity23;
import com.example.administrator.alldemos.activities.Activity3;
import com.example.administrator.alldemos.activities.Activity4;
import com.example.administrator.alldemos.activities.Activity5;
import com.example.administrator.alldemos.activities.Activity6;
import com.example.administrator.alldemos.activities.Activity7;
import com.example.administrator.alldemos.activities.Activity8;
import com.example.administrator.alldemos.activities.Activity9;

/**
 *---------------------------------------------------
 * Description: 第一批demo
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/12/24 18:09
 *---------------------------------------------------
 */
public class Demos1 extends AppCompatActivity {

    private ListView listView;
    private String[] demos=new String[]{
            "1.ListView by SimpleActivity",
            "2.ListView by BaseActivity",
            "3.ListView by SimpleCursorAdapter",
            "4.Spinner Test",
            "5.Create and Read File",
            "6.Create and Read SdCard File",
            "7.Create and Read Xml File",
            "8.SharedPreferences test",
            "9.SQLite Test",
            "10.ContentProvider Test",
            "11.ExpendableListView Test",
            "12.GridView Test",
            "13.DrawerLayout Test",
            "14.DrawerLayout + ActionBar",
            "15.FragmentTabHost Test",
            "16.Dialog Test",
            "17.ContextMenu Test",
            "18.Handler Test",
            "19.ShortCut Test",
            "20.LoaderManager Test",
            "21.Self-Loader Test",
            "22.AppWidget Test",
            "23.ViewPager Test"
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
                        startActivity(new Intent(Demos1.this, Activity1.class));
                        break;
                    case 1:
                        startActivity(new Intent(Demos1.this, Activity2.class));
                        break;
                    case 2:
                        startActivity(new Intent(Demos1.this, Activity3.class));
                        break;
                    case 3:
                        startActivity(new Intent(Demos1.this, Activity4.class));
                        break;
                    case 4:
                        startActivity(new Intent(Demos1.this, Activity5.class));
                        break;
                    case 5:
                        startActivity(new Intent(Demos1.this, Activity6.class));
                        break;
                    case 6:
                        startActivity(new Intent(Demos1.this, Activity7.class));
                        break;
                    case 7:
                        startActivity(new Intent(Demos1.this, Activity8.class));
                        break;
                    case 8:
                        startActivity(new Intent(Demos1.this, Activity9.class));
                        break;
                    case 9:
                        startActivity(new Intent(Demos1.this, Activity10.class));
                        break;
                    case 10:
                        startActivity(new Intent(Demos1.this, Activity11.class));
                        break;
                    case 11:
                        startActivity(new Intent(Demos1.this, Activity12.class));
                        break;
                    case 12:
                        startActivity(new Intent(Demos1.this, Activity13.class));
                        break;
                    case 13:
                        startActivity(new Intent(Demos1.this, Activity14.class));
                        break;
                    case 14:
                        startActivity(new Intent(Demos1.this, Activity15.class));
                        break;
                    case 15:
                        startActivity(new Intent(Demos1.this, Activity16.class));
                        break;
                    case 16:
                        startActivity(new Intent(Demos1.this, Activity17.class));
                        break;
                    case 17:
                        startActivity(new Intent(Demos1.this, Activity18.class));
                        break;
                    case 18:
                        startActivity(new Intent(Demos1.this, Activity19.class));
                        break;
                    case 19:
                        startActivity(new Intent(Demos1.this, Activity20.class));
                        break;
                    case 20:
                        startActivity(new Intent(Demos1.this, Activity21.class));
                        break;
                    case 21:
                        startActivity(new Intent(Demos1.this, Activity22.class));
                        break;
                    case 22:
                        startActivity(new Intent(Demos1.this, Activity23.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
