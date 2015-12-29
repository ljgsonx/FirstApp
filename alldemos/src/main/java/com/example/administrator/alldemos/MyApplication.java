package com.example.administrator.alldemos;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/12/26 14:29
 * ---------------------------------------------------
 */
public class MyApplication extends Application {

    private static final String APPLICATION_ID = "NLQob3CrP948AMxMHBkD4X32UEX7gfsGzXJkaLiK";
    private static final String CLIENT_KEY = "V8puHF8AYWOBVCtyfMD8ZV3zGw7EYuDB9mUFJZ5h";

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParsePush.subscribeInBackground("thechannel");
    }
}
