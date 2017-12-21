package com.example.user.qrrecoder.entity;

import android.text.TextUtils;
import android.util.Base64;

import com.example.user.qrrecoder.utils.StringUtils;
import com.hdl.elog.ELog;

/**
 * Created by dxs on 2017/12/21.
 * {"XTAgentDeviceId":48541841981781798189}
 */

public class DeviceInfo {
    private static final String KEY = "XTAgentDeviceId";
    private static final String KEY_REG = "\\{\"" + KEY + "\"\\:([\\w]+)\\}";
    private String qrContents = "";
    private String deviceID;
    private String key;

    public DeviceInfo(String qrContents) {
        this.qrContents = qrContents;
        initDeviceInfo();
    }

    public String getQrContents() {
        return qrContents;
    }

    public void setQrContents(String qrContents) {
        this.qrContents = qrContents;
        initDeviceInfo();
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private void initDeviceInfo() {
        if (TextUtils.isEmpty(qrContents)) {
            return;
        }
        String realInfo= new String(Base64.decode(qrContents,Base64.NO_WRAP));
        deviceID = StringUtils.getMatcher(KEY_REG, realInfo,1);
    }
}
