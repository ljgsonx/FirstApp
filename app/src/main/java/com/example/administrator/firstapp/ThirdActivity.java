package com.example.administrator.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *--------------------------------------
 * Description: 单选按钮,复选按钮,listView的Demo
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp
 * Time: 2015/11/10 15:43
 *--------------------------------------
 */
public class ThirdActivity extends AppCompatActivity implements
        View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content3);
        InitUI();
        System.out.print("as");

    }

    private void InitUI() {
        View Layout1 = findViewById(R.id.Layout1);
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        //接口实现监听,都是需要进行设置监听
        if (Layout1 != null) Layout1.setOnClickListener(this);
        if (checkBox1 != null) checkBox1.setOnCheckedChangeListener(this);
        if (checkBox2 != null) checkBox2.setOnCheckedChangeListener(this);
        if (radioGroup1 != null) radioGroup1.setOnCheckedChangeListener(this);
        ListView myListView = (ListView) findViewById(R.id.listView1);
        myListView.setOnItemLongClickListener(this); // 设置ListView长按
        myListView.setOnItemClickListener(this);    // 设置ListView单击
        assert checkBox1 != null;
        checkBox1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("-------------------->", "Long Click");
                return true;
            }
        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getDataSource(),
                R.layout.layout_list_item, new String[]{"title"}, new int[]{R.id.textView1});
        myListView.setAdapter(simpleAdapter);
    }

    public List<Map<String, Object>> getDataSource() {
        List<Map<String, Object>> listems = new ArrayList<>();
        Map<String, Object> map = new HashMap();
        map.put("title", "北京");
        listems.add(map);

        map = new HashMap();
        map.put("title", "上海");
        listems.add(map);

        map = new HashMap();
        map.put("title", "广州");
        listems.add(map);

        map = new HashMap();
        map.put("title", "南京");
        listems.add(map);

        map = new HashMap();
        map.put("title", "苏州");
        listems.add(map);

        return listems;
    }

    @Override
    //一般layout button监听实现方法
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.Layout1) {
            TextView textView = (TextView) findViewById(R.id.textView1);
            textView.setText("layout监听");
            Toast.makeText(this, "layout监听", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    //checkbox监听实现方法
    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
        if (arg0.getId() == R.id.checkBox1) {
            if (arg1) {
                Toast.makeText(this, "选中单项1", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "取消单项1", Toast.LENGTH_SHORT).show();
            }
        } else if (arg0.getId() == R.id.checkBox2) {
            if (arg1) {
                Toast.makeText(this, "选中单项2", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "取消单项2", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    //RadioGroup监听实现方法
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        if (arg0.getId() == R.id.radioGroup1) {
            if (arg1 == R.id.radio1) {
                Toast.makeText(this, "选中男", Toast.LENGTH_SHORT).show();
            } else if (arg1 == R.id.radio2) {
                Toast.makeText(this, "选中女", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    //listview长按监听实现方法
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
        Log.i("+++++++++++++++++++", arg2 + "  " + arg3);
        Toast.makeText(this, "listview 长按" + getDataSource().get(arg2), Toast.LENGTH_SHORT).show();
        //这里返回一定是true , 不然会同时触发单击
        return true;
    }

    @Override
    //listview单击监听实现方法
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Toast.makeText(this, "listview 单击" + getDataSource().get(arg2), Toast.LENGTH_SHORT).show();

    }
}
