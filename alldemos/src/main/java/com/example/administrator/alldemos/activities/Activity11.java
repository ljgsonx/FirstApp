package com.example.administrator.alldemos.activities;

import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *---------------------------------------------------
 * Description: ExpandableListView Test
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/12/7 9:51
 *---------------------------------------------------
 */
public class Activity11 extends ExpandableListActivity {

    private List<Map<String, String>> groupData;
    private List<List<Map<String, String>>> childData;

    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.expandable_list_content);
        init();
    }

    private void init() {
        expandableListView = (ExpandableListView) findViewById(android.R.id.list);
        setData();
        ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"name"},
                new int[]{android.R.id.text1},
                childData,
                android.R.layout.simple_expandable_list_item_2,
                new String[]{"address"},
                new int[]{android.R.id.text2});
        expandableListView.setAdapter(adapter);
    }

    public void setData(){
        //group data
        groupData = new ArrayList<Map<String, String>>();
        Map<String, String> groupDataMap = new HashMap<String, String>();
        groupDataMap.put("name", "xiao li");
        groupData.add(groupDataMap);
        groupDataMap = new HashMap<String, String>();
        groupDataMap.put("name", "xiao qiang");
        groupData.add(groupDataMap);
        groupDataMap = new HashMap<String, String>();
        groupDataMap.put("name", "xiao hong");
        groupData.add(groupDataMap);

        //children data
        childData=new ArrayList<List<Map<String, String>>>();
        //group1
        List<Map<String, String>> childDataList = new ArrayList<Map<String, String>>();
        Map<String, String> childDataMap = new HashMap<String, String>();
        childDataMap.put("address", "北京");
        childDataList.add(childDataMap);
        childDataMap = new HashMap<String, String>();
        childDataMap.put("address", "螺丝");
        childDataList.add(childDataMap);
        childDataMap = new HashMap<String, String>();
        childDataMap.put("address", "秘密");
        childDataList.add(childDataMap);
        childData.add(childDataList);
        //group2
        childDataList = new ArrayList<Map<String, String>>();
        childDataMap = new HashMap<String, String>();
        childDataMap.put("address", "青岛");
        childDataList.add(childDataMap);
        childDataMap = new HashMap<String, String>();
        childDataMap.put("address", "海南");
        childDataList.add(childDataMap);
        childData.add(childDataList);
        //group3
        childDataList = new ArrayList<Map<String, String>>();
        childDataMap = new HashMap<String, String>();
        childDataMap.put("address", "梅园");
        childDataList.add(childDataMap);
        childData.add(childDataList);
    }


}
