package com.example.user.qrrecoder.utils;

import android.content.Intent;
import android.net.Uri;

import com.example.user.qrrecoder.app.MyApp;

/**
 * Created by dxs on 2017/12/21.
 */

public class IntentUtis {
    /**
     * 跳转网页
     * @param url 网页地址
     */
    public static void toWebBroser(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApp.app.startActivity(intent);
    }
}
