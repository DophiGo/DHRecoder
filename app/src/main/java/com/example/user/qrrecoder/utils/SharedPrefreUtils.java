package com.example.user.qrrecoder.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dxs on 2016/11/28.
 */

public class SharedPrefreUtils {
    private static final String SP_FILE_GWELL = "scan";
    private static class SharedPrefreHolder {
        private static final SharedPrefreUtils INSTANCE = new SharedPrefreUtils();
    }

    private SharedPrefreUtils() {
    }

    public static final SharedPrefreUtils getInstance() {
        return SharedPrefreHolder.INSTANCE;
    }

    public void putStringData(Context mContext, String key, String value){
        SharedPreferences sf = mContext.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getStringData(Context mContext, String key){
        SharedPreferences sf = mContext.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        return sf.getString(key,"");
    }

    public void putBooleanData(Context mContext, String key, boolean value){
        SharedPreferences sf = mContext.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public boolean getBooleanData(Context mContext, String key){
        SharedPreferences sf = mContext.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        return sf.getBoolean(key,false);
    }

    public void putLongData(Context mContext, String key, long value){
        SharedPreferences sf = mContext.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putLong(key,value);
        editor.apply();
    }

    public long getLongData(Context mContext, String key){
        SharedPreferences sf = mContext.getSharedPreferences(SP_FILE_GWELL,
                Context.MODE_PRIVATE);
        return sf.getLong(key,0);
    }
}
