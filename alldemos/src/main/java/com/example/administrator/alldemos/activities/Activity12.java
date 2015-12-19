package com.example.administrator.alldemos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.alldemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *---------------------------------------------------
 * Description: GridView Test
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/12/7 9:51
 *---------------------------------------------------
 */
public class Activity12 extends AppCompatActivity {

    private GridView gridView;
    private int[] icon = {R.drawable.png1,R.drawable.png2,R.drawable.png3,
            R.drawable.png4,R.drawable.png5,R.drawable.png6,R.drawable.png7,
            R.drawable.png8};
    private String[] iconName = {"通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
            "设置"};
    private List<Map<String, Object>> data_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity12_gridview_layout);
        init();
    }

    private void init() {
        gridView = (GridView) findViewById(R.id.gridview);
        data_list = new ArrayList<Map<String, Object>>();
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        gridView.setAdapter(new SimpleAdapter(
                this,
                data_list,
                R.layout.activity12_gridview_item,
                new String[]{"image","text"},
                new int[]{R.id.gridview_image,R.id.gridview_text}));
    }
}
