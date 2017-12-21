package com.example.user.qrrecoder.http.retrofit;

import com.example.user.qrrecoder.http.Enty.HttpResults;

import io.reactivex.functions.Function;

/**
 * Created by dxs on 2017/12/21.
 */

public class HttpResultNoneDataFunc<T extends HttpResults> implements Function<T,T> {
    @Override
    public T apply(T t) throws Exception {
        if(!t.getCode().equals("0")){
            throw new ApiException(t.getCode());
        }
        return t;
    }
}
