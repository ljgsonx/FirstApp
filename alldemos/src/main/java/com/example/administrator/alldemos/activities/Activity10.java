package com.example.administrator.alldemos.activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
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

/**
 * ---------------------------------------------------
 * Description: Contentprovider使用的测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/20 11:49
 * ---------------------------------------------------
 */
public class Activity10 extends AppCompatActivity implements View.OnClickListener{

    private Button add_Btn;
    private Button select_Btn;
    private Button update_Btn;
    private Button delete_Btn;

    private EditText editText_operateData;
    private ListView listView_showInfo;

    private static String[] theSelectedOne=new String[]{"",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_layout);
        init();


    }

    private void init() {
        add_Btn = (Button) findViewById(R.id.registerBtn);
        select_Btn = (Button) findViewById(R.id.selectAllBtn);
        update_Btn = (Button) findViewById(R.id.updateBtn);
        delete_Btn = (Button) findViewById(R.id.deleteBtn);
        editText_operateData = (EditText) findViewById(R.id.operateData);
        listView_showInfo = (ListView) findViewById(R.id.displayInfo);
        listView_showInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userId = ((TextView) view.findViewById(R.id.userInfoTest1)).getText().toString();
                if(theSelectedOne[0].equals(userId)){
                    if(theSelectedOne[1].equals("selected")) {
                        theSelectedOne[1] = "unselected";
                    }else{
                        theSelectedOne[1] = "selected";
                    }
                }else {
                    theSelectedOne[0] = userId;
                    theSelectedOne[1] = "selected";
                }
            }
        });
        add_Btn.setOnClickListener(this);
        select_Btn.setOnClickListener(this);
        update_Btn.setOnClickListener(this);
        delete_Btn.setOnClickListener(this);

    }

    public void updateListView(){
        Uri selectAllUri= Uri.parse("content://com.example.administrator.alldemos.userprovider/user");
        Cursor cursor =  this.getBaseContext().getContentResolver().query(selectAllUri, null, null, null, "_id desc");
        startManagingCursor(cursor);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity9_listview_layout,
                cursor,
                new String[]{"_id", "userName"},
                new int[]{R.id.userInfoTest1, R.id.userInfoTest2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        listView_showInfo.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        ContentResolver contentResolver = this.getBaseContext().getContentResolver();
        switch (v.getId()){
            case R.id.registerBtn:
                Uri insertUri = Uri.parse("content://com.example.administrator.alldemos.userprovider/user");
                ContentValues contentValues = new ContentValues();
                contentValues.put("userName", "miss li");
                contentValues.put("userPwd", "-------");
                insertUri = contentResolver.insert(insertUri, contentValues);
                System.out.println("after insert a user=> " + insertUri);
                updateListView();
                break;
            case R.id.selectAllBtn:
                updateListView();
                break;
            case R.id.updateBtn:
                Uri updateUri;
                if(!"".equals(theSelectedOne[0])&&"selected".equals(theSelectedOne[1])){
                    updateUri = Uri.parse("content://com.example.administrator.alldemos.userprovider/user/"+theSelectedOne[0]);
                }else{
                    updateUri = Uri.parse("content://com.example.administrator.alldemos.userprovider/user");
                }
                String updateData = editText_operateData.getText().toString();
                contentValues = new ContentValues();
                contentValues.put("userName", updateData);
                contentResolver.update(updateUri, contentValues, null, null);
                updateListView();
                break;
            case R.id.deleteBtn:
                Uri deleteUri;
                if(!"".equals(theSelectedOne[0])&&"selected".equals(theSelectedOne[1])){
                    deleteUri = Uri.parse("content://com.example.administrator.alldemos.userprovider/user/"+theSelectedOne[0]);
                    contentResolver.delete(deleteUri, null, null);
                    updateListView();
                }
                break;
            default:
                break;
        }
    }
}
