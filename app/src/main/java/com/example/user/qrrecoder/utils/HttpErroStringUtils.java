package com.example.user.qrrecoder.utils;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.http.retrofit.HttpError;

/**
 * Created by dxs on 2017/12/7.
 */

public class HttpErroStringUtils {
    public static String getShowStringByException(Throwable e){
       return getShowStringByErrorCode(e.getMessage());
    }

    public static String getShowStringByErrorCode(String error){
        switch (error){
            case HttpError.ERROR_10903013:
                return MyApp.app.getString(R.string.http_er_10903013);
            case HttpError.ERROR_10901020:
                return MyApp.app.getString(R.string.http_er_10901020);
            case HttpError.ERROR_10903007:
                return MyApp.app.getString(R.string.http_er_10903007);
            case HttpError.ERROR_10902013:
                return MyApp.app.getString(R.string.http_er_10902013);
            case HttpError.ERROR_10901022:
                return MyApp.app.getString(R.string.http_er_10901022);
            default:
                return MyApp.app.getString(R.string.http_er)+"("+error+")";
        }
    }
}
