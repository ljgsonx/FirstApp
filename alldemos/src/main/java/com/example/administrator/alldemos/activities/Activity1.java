package com.example.administrator.alldemos.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.example.administrator.alldemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Description: ListView中使用SimpleAdapter的测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/12 14:38
 * ---------------------------------------------------
 */
public class Activity1 extends AppCompatActivity {

    private ListView listView;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new SimpleAdapter(
                this,
                getData(),
                R.layout.activity1_listview_layout,
                new String[]{"img","txt"},
                new int[]{R.id.imgItem,R.id.textItem}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<Map<String,Object>> list=getData();
                int imgId= (int) list.get(position).get("img");
                View theView=getLayoutInflater().inflate(
                        R.layout.activity1_imgshow,
                        (LinearLayout)listView.getParent(),
                        false);
                theView.findViewById(R.id.imgShowId).setBackgroundResource(imgId);
                popupWindow = new PopupWindow(
                        theView,
                        ActionBar.LayoutParams.MATCH_PARENT,
                        ActionBar.LayoutParams.MATCH_PARENT,true);
                popupWindow.showAtLocation(listView, Gravity.CENTER, 0, 0);
                theView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    private static List<Map<String,Object>> getData() {
        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("img",R.drawable.bmp1);
        map.put("txt","图片1");
        list.add(map);
        map=new HashMap<>();
        map.put("img",R.drawable.bmp2);
        map.put("txt","图片2");
        list.add(map);
        return list;
    }

}
