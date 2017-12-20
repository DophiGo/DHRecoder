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
                //小豚
            case HttpError.ERROR_801001004:
                return MyApp.app.getString(R.string.http_er_801001004);
            case HttpError.ERROR_801011001:
                return MyApp.app.getString(R.string.http_er_801011001);
            case HttpError.ERROR_801011002:
                return MyApp.app.getString(R.string.http_er_801011002);
            case HttpError.ERROR_801011003:
                return MyApp.app.getString(R.string.http_er_801011003);
            case HttpError.ERROR_801011004:
                return MyApp.app.getString(R.string.http_er_801011004);
            case HttpError.ERROR_801011005:
                return MyApp.app.getString(R.string.http_er_801011005);
            case HttpError.ERROR_801011006:
                return MyApp.app.getString(R.string.http_er_801011006);
            case HttpError.ERROR_801011007:
                return MyApp.app.getString(R.string.http_er_801011007);
            case HttpError.ERROR_801011009:
                return MyApp.app.getString(R.string.http_er_801011009);
            case HttpError.ERROR_801011010:
                return MyApp.app.getString(R.string.http_er_801011010);
            case HttpError.ERROR_801011011:
                return MyApp.app.getString(R.string.http_er_801011011);
            case HttpError.ERROR_801011012:
                return MyApp.app.getString(R.string.http_er_801011012);
            default:
                return MyApp.app.getString(R.string.http_er)+"("+error+")";
        }
    }
}
