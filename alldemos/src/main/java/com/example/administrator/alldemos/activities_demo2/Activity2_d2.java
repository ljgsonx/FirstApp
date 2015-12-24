package com.example.administrator.alldemos.activities_demo2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.administrator.alldemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * ---------------------------------------------------
 * Description: ultra-pull-to-refresh框架测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities_demo2
 * Time: 2015/12/22 14:32
 * ---------------------------------------------------
 */
public class Activity2_d2 extends AppCompatActivity {

    private PtrClassicFrameLayout mPtrFrame;
    private StoreHouseHeader mHeader;
    private Button mchangeRefreshUIBtn;

    private List<Map<String, Object>> data_list;
    String [] from ={"gridview_image","gridview_text"};
    int [] to = {R.id.gridview_image,R.id.gridview_text};
    private int[] icon = { R.drawable.guide_350_01, R.drawable.guide_350_02,
            R.drawable.guide_350_03};
    private String[] iconName = { "通讯录", "日历", "照相机"};

    private Boolean isClassicHandler = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d2_activity2_layout);

        init();
    }

    private List<Map<String, Object>> getData(){
        data_list = new ArrayList<Map<String, Object>>();
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gridview_image", icon[i]);
            map.put("gridview_text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }

    private void init() {

        mchangeRefreshUIBtn = (Button) findViewById(R.id.changeRefreshUI);
        mchangeRefreshUIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClassicHandler = !isClassicHandler;
                setHeader();
            }
        });
        getData();
        final GridView gridListView = (GridView) findViewById(R.id.rotate_header_grid_view);
        gridListView.setAdapter(new SimpleAdapter(this, data_list, R.layout.activity12_gridview_item, from, to));

        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_grid_view_frame);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh(false);
            }
        }, 100);
        setHeader();
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            //处理获取数据业务
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mPtrFrame.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mPtrFrame.refreshComplete();
                            }
                        }, 0);
                    }
                }.start();
            }
        });
        if(isClassicHandler){
            mPtrFrame.addPtrUIHandler(new PtrUIHandler() {
                private int mLoadTime = 0;
                @Override
                public void onUIReset(PtrFrameLayout frame) {
                    mLoadTime++;
                    if (mLoadTime % 2 == 0) {
                        mHeader.setScale(1);
                        mHeader.initWithStringArray(R.array.storehouse);
                    } else {
                        mHeader.setScale(0.5f);
                        mHeader.initWithStringArray(R.array.akta);
                    }
                }
                @Override
                public void onUIRefreshPrepare(PtrFrameLayout frame) {
                }
                @Override
                public void onUIRefreshBegin(PtrFrameLayout frame) {
                }
                @Override
                public void onUIRefreshComplete(PtrFrameLayout frame) {

                }
                @Override
                public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
                }
            });
        }
    }

    private void setHeader() {
        if (isClassicHandler) {
            mHeader = new StoreHouseHeader(Activity2_d2.this);
            mHeader.setPadding(0, 15, 0, 0);
            mHeader.initWithStringArray(R.array.storehouse);
            mHeader.setBackgroundColor(Color.DKGRAY);
            mPtrFrame.setHeaderView(mHeader);
            mPtrFrame.addPtrUIHandler(mHeader);
        }else{
            PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(Activity2_d2.this);
            header.setPadding(0, 15, 0, 0);
            header.setBackgroundColor(Color.DKGRAY);
            mPtrFrame.setHeaderView(header);
            mPtrFrame.addPtrUIHandler(header);
        }
    }
}
