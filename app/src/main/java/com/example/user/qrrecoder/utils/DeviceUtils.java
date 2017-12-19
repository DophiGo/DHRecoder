package com.example.user.qrrecoder.utils;

import android.text.TextUtils;
import android.util.Log;

import com.hdl.elog.ELog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by USER on 2017/11/10.
 * 设备相关工具
 */

public class DeviceUtils {
    private final static String DEVICEURL="http://yoosee.co/\\?D=([^(&)]*)";
    private final static String DEVICEINFO_SPLITE="-";

    public static boolean isLegal(String result){
        return getDeviceinfo(result)!=null;
    }

    /**
     * 二维码识别匹配
     * @param result 二维码类容
     * @return 包含设备信息的字符串（D=序列号-设备ID）
     */
    public static String getDeviceinfo(String result){
        if(!TextUtils.isEmpty(result)){
            Pattern mPattern = Pattern.compile(DEVICEURL);
            Matcher mMatcher = mPattern.matcher(result);
            while (mMatcher.find()) {
                return mMatcher.group(1);
            }
        }
        return null;
    }

    /**
     * 获取设备信息
     * @param deviceInfo 包含设备信息的字符串（D=序列号-设备ID）
     * @return 设备序列号与ID
     * @throws IllegalArgumentException
     */
    public static String[] getDeviceInfoFromeUrl(String deviceInfo)throws IllegalArgumentException{
        if(TextUtils.isEmpty(deviceInfo)||!deviceInfo.contains(DEVICEINFO_SPLITE)){
            throw new IllegalArgumentException("deviceInfo "+deviceInfo+" is not illegal");
        }
        return deviceInfo.split(DEVICEINFO_SPLITE);
    }
}
