package com.example.user.qrrecoder.http.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * Created by WXY on 2017/1/3.
 */

public class DHSharedPrefreUtils {
    private static final String SP_FILE_GWELL = "xiaotun_scanner_http";

    private static class SharedPrefreHolder {
        private static final DHSharedPrefreUtils INSTANCE = new DHSharedPrefreUtils();
    }

    public static DHSharedPrefreUtils getInstance() {
        return SharedPrefreHolder.INSTANCE;
    }


    public void putStringData(Context context, String key, String value) {
        SharedPreferences sf = context.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringData(Context context, String key) {
        SharedPreferences sf = context.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        return sf.getString(key, "");
    }

    public void putBooleanData(Context context, String key, boolean value) {
        SharedPreferences sf = context.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBooleanData(Context context, String key) {
        SharedPreferences sf = context.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        return sf.getBoolean(key, false);
    }

    /**
     * 获取当前国家语言
     *
     * @param context
     * @return
     */
    public static String getCountryLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage()+"-"+locale.getCountry();
    }
}
