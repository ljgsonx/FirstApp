package com.example.administrator.networkdemo;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.net.URL;

/**
 *---------------------------------------------------
 * Description: 网络框架测试类
 * Author: ljgsonx
 * Belong to: com.example.administrator.networkdemo
 * Time: 2015/12/15 16:41
 *---------------------------------------------------
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private RequestQueue mQueue;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mQueue = Volley.newRequestQueue(getContext());
    }

    //volley test
    public void test_volley(){
        Log.i("------>", "start to test_volley!");
        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("------>", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("------>", error.getMessage(), error);
                    }
                });
        mQueue.add(stringRequest);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://wthrcdn.etouch.cn/weather_mini?citykey=101010100", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("------>", response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("------>", error.getMessage(), error);
                    }
                });
        mQueue.add(jsonObjectRequest);
    }

    //okHttp test
    public void test_okHttp() {
        Log.i("------>", "start to test_okHttp!");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        try {
            com.squareup.okhttp.Response response = client.newCall(request).execute();
            Log.i("------>", response.body().string());
        }catch (Exception e){
            Log.i("------>", "error");
        }
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if(null != mQueue)
            mQueue.stop();
    }
}