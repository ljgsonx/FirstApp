package com.example.administrator.alldemos.activities_demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.alldemos.R;

import java.util.ArrayList;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos.activities_demo2
 * Time: 2016/1/6 17:27
 * ---------------------------------------------------
 */
public class Activity5_d2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d2_activity5_layout);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //列数为两列
        int spanCount = 2;
        mLayoutManager = new StaggeredGridLayoutManager(
                spanCount,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //构建一个临时数据源
        for (int i = 0; i < 100; i++) {
            items.add("i:" + i);
        }
        mAdapter = new ItemAdapter(items);
        mRecyclerView.setAdapter(mAdapter);
    }


    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.viewHolder> {
        private ArrayList<String> items = new ArrayList<>();

        public ItemAdapter(ArrayList<String> items) {
            this.items = items;
        }

        @Override
        public viewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.d2_activity5_itemcard,
                    viewGroup, false);
            return new viewHolder(view);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(viewHolder viewHolder, int position) {
            String info = items.get(position);
            View view = viewHolder.itemView;
            TextView textView = (TextView) view.findViewById(R.id.info_text);
            textView.setText(info);
            //手动更改高度，不同位置的高度有所不同
            textView.setHeight(100 + (position % 3) * 30);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class viewHolder extends RecyclerView.ViewHolder {
            public viewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
