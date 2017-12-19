package com.example.user.qrrecoder.http.retrofit;

import android.util.Log;

import com.example.user.qrrecoder.http.Enty.HttpResults;

import io.reactivex.functions.Function;

/**
 * Created by dxs on 2017/11/17.
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */

public class HttpResultFunc<T > implements Function<HttpResults<T>,T> {
    @Override
    public T apply(HttpResults<T> tHttpResults) throws Exception {
        Log.e("dxsTest","tHttpResults:"+tHttpResults);
        if (!tHttpResults.getError_code().equals("0")) {
            throw new ApiException(tHttpResults.getError_code());
        }
        return tHttpResults.getData();
    }
}
