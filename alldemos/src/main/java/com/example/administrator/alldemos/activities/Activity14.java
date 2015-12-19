package com.example.administrator.alldemos.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.alldemos.fragments.MyFragment;
import com.example.administrator.alldemos.R;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *---------------------------------------------------
 * Description: DrawerLayout Test
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities
 * Time: 2015/12/7 9:47
 *---------------------------------------------------
 */
public class Activity14 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private ArrayList<String> navigationList;
    private ArrayList<Integer> imgIdList;
    private ArrayAdapter<String> arrayAdapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ActionBar actionBar;
    private String originTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity14_drawerlayout);
        init();
    }

    private void init() {
        actionBar = getSupportActionBar();
        if(actionBar != null) {
            //ActionBar自带的back功能
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        navigationList = new ArrayList<String>();
        navigationList.add("my boy");
        navigationList.add("my girl");
        navigationList.add("my lady");

        imgIdList = new ArrayList<Integer>();
        imgIdList.add(R.drawable.png1);
        imgIdList.add(R.drawable.png2);
        imgIdList.add(R.drawable.png3);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navigationList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        originTitle = getTitle().toString();
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle("please select your option");
                invalidateOptionsMenu(); // automatically call onCreateOptionsMenu() and onPrepareOptionMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                actionBar.setTitle(originTitle);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        updateFragmentContent(0);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        updateFragmentContent(position);

    }

    public void updateFragmentContent(int position) {
        Fragment myFragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt("imgId", imgIdList.get(position));
        myFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, myFragment).commit();
        drawerLayout.closeDrawer(listView);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("onPrepareOptionsMenu");
        boolean isOpen = drawerLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_websearch).setVisible(!isOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_websearch) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse("http://www.baidu.com");
            intent.setData(uri);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    //用来设置允许给自定义menu设置图标
    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
