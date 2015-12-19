package com.example.administrator.alldemos.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.alldemos.R;
import com.example.administrator.alldemos.beans.DataEntity;

import java.util.List;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/16 11:44
 * ---------------------------------------------------
 */
public class MyBaseAdapter extends BaseAdapter{

    private List<DataEntity> dataList;
    private Context context;

    public MyBaseAdapter(Context context,List<DataEntity> dataList) {
        this.context=context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return (dataList==null)?0:dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView textView1;
        TextView textView2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println(convertView + "==" + position);
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity2_listview_layout, null);
            viewHolder=new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.activity2_tv1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.activity2_tv2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(dataList.get(position).getName());
        viewHolder.textView2.setText(dataList.get(position).getImgIntro());
        Drawable drawable=context.getResources().getDrawable(dataList.get(position).getImgId());
        drawable = zoomDrawable(drawable, 150, 150);
        viewHolder.textView2.setCompoundDrawablesWithIntrinsicBounds(
                drawable, null, null, null);
        return convertView;
    }


    static Bitmap drawableToBitmap(Drawable drawable) // drawable 转换成 bitmap
    {
        int width = drawable.getIntrinsicWidth();   // 取 drawable 的长宽
        int height = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() !=
                PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);     // 建立对应 bitmap
        Canvas canvas = new Canvas(bitmap);         // 建立对应 bitmap 的画布
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);      // 把 drawable 内容画到画布中
        return bitmap;
    }

    static Drawable zoomDrawable(Drawable drawable, int w, int h)
    {
        int width = drawable.getIntrinsicWidth();
        int height= drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable); // drawable 转换成 bitmap
        Matrix matrix = new Matrix();   // 创建操作图片用的 Matrix 对象
        float scaleWidth = ((float)w / width);   // 计算缩放比例
        float scaleHeight = ((float)h / height);
        matrix.postScale(scaleWidth, scaleHeight);         // 设置缩放比例
        // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
        return new BitmapDrawable(null,newbmp);       // 把 bitmap 转换成 drawable 并返回
    }
}


