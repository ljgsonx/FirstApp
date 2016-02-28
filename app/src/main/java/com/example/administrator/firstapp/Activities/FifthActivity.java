package com.example.administrator.firstapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.firstapp.ItemTest;
import com.example.administrator.firstapp.R;
import com.example.administrator.firstapp.custom.TestPopwindow2;

import java.util.ArrayList;

/**
 * ---------------------------------------------------
 * Description: 测试PopupWindow
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp
 * Time: 2015/11/10 16:09
 * ---------------------------------------------------
 */
public class FifthActivity extends AppCompatActivity implements View.OnClickListener,
        PopupWindow.OnDismissListener {
    private ArrayList<ItemTest> mArrayList = new ArrayList<ItemTest>();
    private TestPopwindow2 mTestPopwindow2 = null;
    private int mnSeclectItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content5);

        InitUI();

    }

    private void InitUI() {
        if (mArrayList != null) {
            mArrayList.clear();
            for (int i = 0; i < 3; i++) {
                ItemTest itemTest1 = new ItemTest(R.drawable.bmp1, "图片一");
                mArrayList.add(itemTest1);
                ItemTest itemTest2 = new ItemTest(R.drawable.bmp2, "图片二");
                mArrayList.add(itemTest2);
            }
        }

        // 实例化TestPopwindow2
        mTestPopwindow2 = new TestPopwindow2(this);
        // 设置点击其他位置mTestPopwindow2消失
        mTestPopwindow2.setOnDismissListener(this);

        Button buttonTest2 = (Button) findViewById(R.id.buttonTest2);
        buttonTest2.setOnClickListener(this);
    }

    private void OnPopwindowTest2() {
        if (mTestPopwindow2 == null)
            return;

        //回到接受
        mTestPopwindow2.setOnData(new TestPopwindow2.OnGetData() {

            //记录上一次选中的item
            @Override
            public int onSeclectItem() {
                return mnSeclectItem;
            }

            //回调接受函数
            @Override
            public void onDataCallBack(int nSectlect, ArrayList<ItemTest> mArrayList) {
                Toast.makeText(getApplicationContext(),
                        "listview 的点击" + String.valueOf(nSectlect),
                        Toast.LENGTH_SHORT).show();
                mnSeclectItem = nSectlect;
            }

            //传递数据源过去
            @Override
            public ArrayList<ItemTest> onArrayList() {
                return mArrayList;
            }
        });

        // location获得控件的位置
        int[] location = new int[2];
        View v = findViewById(R.id.buttonTest2);
        if (v != null)
            v.getLocationOnScreen(location); // 控件在屏幕的位置
        mTestPopwindow2.setAnimationStyle(R.style.AppBaseTheme);

        // mTestPopwindow2弹出在某控件(button)的下面
        mTestPopwindow2.showAtLocation(v, Gravity.TOP | Gravity.LEFT,
                location[0] - v.getWidth(), location[1] + v.getHeight());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonTest2:
                OnPopwindowTest2();
                break;
            default:
                break;
        }
    }

    // 点击其他地方消失
    @Override
    public void onDismiss() {

    }
}
