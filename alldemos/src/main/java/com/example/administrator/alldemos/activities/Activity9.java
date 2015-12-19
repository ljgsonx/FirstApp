package com.example.administrator.alldemos.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.administrator.alldemos.R;
import com.example.administrator.alldemos.beans.User;
import com.example.administrator.alldemos.utils.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------------------------------
 * Description: SQLite数据库操作测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/19 9:28
 * ---------------------------------------------------
 */
public class Activity9 extends AppCompatActivity implements View.OnClickListener{

    private UserService userService;
    private SimpleCursorAdapter adapter;
    private EditText editText_userName;
    private EditText editText_userPwd;

    private Button add_Btn;
    private Button select_Btn;
    private Button update_Btn;
    private Button delete_Btn;

    private EditText editText_operateData;
    private ListView listView_showInfo;

    private Map<String,Boolean> listViewItemStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_layout);
        init();
    }

    private void init() {
        listViewItemStatus = new HashMap<String, Boolean>();
        userService = new UserService(this);

        editText_userName = (EditText) findViewById(R.id.userName);
        editText_userPwd = (EditText) findViewById(R.id.userPwd);

        add_Btn = (Button) findViewById(R.id.registerBtn);
        select_Btn = (Button) findViewById(R.id.selectAllBtn);
        update_Btn = (Button) findViewById(R.id.updateBtn);
        delete_Btn = (Button) findViewById(R.id.deleteBtn);
        add_Btn.setOnClickListener(this);
        select_Btn.setOnClickListener(this);
        update_Btn.setOnClickListener(this);
        delete_Btn.setOnClickListener(this);

        editText_operateData = (EditText) findViewById(R.id.operateData);

        listView_showInfo = (ListView) findViewById(R.id.displayInfo);
        updateListView();
        listView_showInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userId = ((TextView) view.findViewById(R.id.userInfoTest1)).getText().toString();
                if (listViewItemStatus.get(userId)==null||!listViewItemStatus.get(userId)) {
                    listViewItemStatus.put(userId, true);
                } else {
                    listViewItemStatus.put(userId, false);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        List<String> userIds = new ArrayList<>();
        Iterator iterator = listViewItemStatus.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if((Boolean)entry.getValue()) {
                userIds.add((String) entry.getKey());
                System.out.println("==>"+entry.getKey());
            }
        }
        switch (v.getId()){
            case R.id.registerBtn:
                userService.save(new User(
                        editText_userName.getText().toString(),editText_userPwd.getText().toString()));
                updateListView();
                break;
            case R.id.selectAllBtn:
                updateListView();
                break;
            case R.id.updateBtn:
                userService.updateMulti(userIds,editText_operateData.getText().toString());
                listViewItemStatus.clear();
                updateListView();
                break;
            case R.id.deleteBtn:
                userService.deleteMulti(userIds);
                listViewItemStatus.clear();
                updateListView();
                break;
            default:
                break;
        }
    }

    public void updateListView(){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity9_listview_layout,
                userService.selectAllUser(),
                new String[]{"_id", "userName"},
                new int[]{R.id.userInfoTest1, R.id.userInfoTest2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        listView_showInfo.setAdapter(adapter);

    }
}
