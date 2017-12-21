package com.example.user.qrrecoder.bean;

/**
 * Created by dxs on 2017/12/21.
 */

public class ItemTitle {
    private String key;
    private String value;

    public ItemTitle() {
    }

    public ItemTitle(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
