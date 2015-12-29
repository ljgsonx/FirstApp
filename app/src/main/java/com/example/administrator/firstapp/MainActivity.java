package com.example.administrator.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * ---------------------------------------------------
 * Description: 1.接收输入的用户名和密码,跳转至secondActivity显示,并返回；
 *              2.loadurl,loadData测试
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp
 * Time: 2015/11/10 15:53
 * ---------------------------------------------------
 */
public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button loginCancel;
    private EditText userName;
    private EditText userPwd;
    private WebView webView;

    private String userNameStr;
    private String userPwdStr;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //+++++----+++++---
        System.out.println("====");
        init();
        loginBtn.setOnClickListener(cl);
        loginCancel.setOnClickListener(cl);
        //fot git test
    }

    View.OnClickListener cl = new View.OnClickListener() {
        @Override
//        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public void onClick(View v) {
            if (v.getId() == R.id.loginBtn) {
                userNameStr = userName.getText().toString();
                userPwdStr = userPwd.getText().toString();
                Toast.makeText(MainActivity.this, "用户名：" + userNameStr + "; 密码：" + userPwdStr, Toast.LENGTH_LONG).show();
//                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//                String result=HttpRequester.sendGet("http://www.baidu.com", "");
//                for(int i=0;i<result.length();i=i+2000){
//                    if((2000+i)>=result.length()) {
//                        System.out.println(result.substring(i, result.length()));
//                    }else {
//                        System.out.println(result.substring(i, 2000 + i));
//                    }
//                }
//                webView.loadDataWithBaseURL(null, result, "text/html", "UTF-8", null);
                webView.loadUrl("http://www.baidu.com");
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("flag", "用户名：" + userNameStr + "; 密码：" + userPwdStr);
                startActivityForResult(intent, REQUEST_CODE);

                //拨打电话
/*                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:13770087922"));
                startActivity(intent);*/
            } else if (v.getId() == R.id.loginCancel) {
                userName.setText("");
                userPwd.setText("");
            }
        }
    };

    private void init() {
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginCancel = (Button) findViewById(R.id.loginCancel);
        userName = (EditText) findViewById(R.id.userName);
        userPwd = (EditText) findViewById(R.id.userPwd);
        webView = (WebView) findViewById(R.id.result);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        //实现webview内跳转
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }
}
