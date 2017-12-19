package com.example.user.qrrecoder.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.user.qrrecoder.app.MyApp;

/**
 * Created by dxs on 2017/12/6.
 * 与APP属性相关的工具入口
 */

public class AppUtils {

    public static String getAppVersion(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = MyApp.app.getPackageManager()
                    .getPackageInfo(MyApp.app.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }
}
