package com.example.user.qrrecoder.http.retrofit;

/**
 * Created by dxs on 2017/11/17.
 */

public class ApiException extends RuntimeException{
    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}
