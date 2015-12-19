package com.example.administrator.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * ---------------------------------------------------
 * Description: ListView使用测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp
 * Time: 2015/11/12 9:20
 * ---------------------------------------------------
 */
public class SixthActivity extends AppCompatActivity {

    private ListView theLv;
    private String[] itemStrs = new String[]{"1", "2", "3", "4", "5"};
    private HashMap<Integer,Boolean> itemStates=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content6);
        init();

    }

    private void init() {
        for(int i=0;i<itemStrs.length;i++){
            itemStates.put(i,false);
        }
        theLv= (ListView) findViewById(R.id.theLv);
        theLv.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, itemStrs));
        theLv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        theLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                if(itemStates.get(position)){
                    itemStates.put(position,false);
                }else{
                    itemStates.put(position,true);
                }
                Toast.makeText(SixthActivity.this,
                        position + " " + id + " " + itemStates.get(position),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
