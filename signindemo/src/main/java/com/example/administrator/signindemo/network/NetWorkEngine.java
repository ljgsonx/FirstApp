package com.example.administrator.signindemo.network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.signindemo.network
 * Time: 2015/12/17 14:25
 * ---------------------------------------------------
 */
public class NetWorkEngine{

    private static final int CONNECT_TIMEOUT = 5 * 1000;
    private static final int READ_TIMEOUT = 40 * 1000;

    private static final int REQUEST_ERROR = 1;
    private static final int REQUEST_OK = 2;

    private OkHttpClient okHttpClient;
    private Handler handler = new MyHandler();

    private NetWorkContext netWorkContext;
    public OnRequestEndListener onRequestEndListener;

    public NetWorkEngine(NetWorkContext netWorkContext,OnRequestEndListener onRequestEndListener) {
        this.netWorkContext = netWorkContext;
        this.onRequestEndListener = onRequestEndListener;
        setClient();
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == REQUEST_ERROR) {
                onRequestEndListener.onRequestEnd(null,0);
            }else if (msg.what == REQUEST_OK) {
                String responseBody = (String) msg.obj;
                if ("my dear manager...".equals(responseBody)){
                    onRequestEndListener.onRequestEnd(responseBody, 0);
                }else{
                    onRequestEndListener.onRequestEnd("用户名或密码错误!", 1);
                }
            }
        }
    }

    /**
     * Description: 设置OKHttpClient实例与参数
     * Author: ljgsonx
     * Time: 2015/12/17 16:49
     */
    private void setClient() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public void doRequest() {
        if(NetWorkConstant.Request.GET.equals(netWorkContext.requestMethod)) {
            doGet();
        }else if (NetWorkConstant.Request.POST.equals(netWorkContext.requestMethod)) {
            doPost();
        }
    }

    private void doGet() {
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(new URL(netWorkContext.LOGIN_URL)).build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Message message = handler.obtainMessage();
            message.what = REQUEST_ERROR;
            handler.sendMessage(message);
            return;
        }
        Log.i("------>", "Request Url:" + netWorkContext.LOGIN_URL);
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Message message = handler.obtainMessage();
                message.what = REQUEST_ERROR;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Message message = handler.obtainMessage();
                message.what = REQUEST_OK;
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
    }

    private void doPost() {
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(new URL(netWorkContext.LOGIN_URL)).build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    public interface OnRequestEndListener {
        void onRequestEnd(String responseBody,int status);
    }
}
