package com.example.user.qrrecoder.entity;

import android.util.Log;

import com.example.user.qrrecoder.data.greendao.DeviceItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dxs on 2017/12/1.
 * 上传记录模型
 */

public class UploadRecords {
    private String userId;
    private int sessionid;
    private List<DeviceItem> deviceItems;

    public UploadRecords() {
    }

    public UploadRecords(String userId, int sessionid, List<DeviceItem> deviceItems) {
        this.userId = userId;
        this.sessionid = sessionid;
        this.deviceItems = deviceItems;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

    public List<DeviceItem> getDeviceItems() {
        return deviceItems;
    }

    public void setDeviceItems(List<DeviceItem> deviceItems) {
        this.deviceItems = deviceItems;
    }

    public String getJsonString(){
        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(deviceItems);
    }
}
