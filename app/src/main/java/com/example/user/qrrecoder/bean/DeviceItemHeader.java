package com.example.user.qrrecoder.bean;

/**
 * Created by USER on 2017/11/10.
 * 扫描设备列表的悬停头部
 */

public class DeviceItemHeader {
    private String header_number;
    private String header_deviceid;
    private String header_state;

    public DeviceItemHeader() {
    }

    public DeviceItemHeader(String header_number, String header_deviceid, String header_state) {
        this.header_number = header_number;
        this.header_deviceid = header_deviceid;
        this.header_state = header_state;
    }

    public String getHeader_number() {
        return header_number;
    }

    public void setHeader_number(String header_number) {
        this.header_number = header_number;
    }

    public String getHeader_deviceid() {
        return header_deviceid;
    }

    public void setHeader_deviceid(String header_deviceid) {
        this.header_deviceid = header_deviceid;
    }

    public String getHeader_state() {
        return header_state;
    }

    public void setHeader_state(String header_state) {
        this.header_state = header_state;
    }
}
