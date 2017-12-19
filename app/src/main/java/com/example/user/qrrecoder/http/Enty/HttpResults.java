package com.example.user.qrrecoder.http.Enty;

/**
 * Created by dxs on 2017/11/17.
 */

public class HttpResults<T> {
    private String error_code;
    private String error;

    private T data;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
