package com.example.user.qrrecoder.http.api;

import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.Enty.Info;
import com.example.user.qrrecoder.http.Enty.LoginResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by USER on 2017/11/16.
 * web API集合
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Observable<HttpResults<LoginResult>> login(@Field("faccount") String start, @Field("fpwd") String pwd);

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("app/ascanrecord")
    Observable<HttpResults> uploadRecord(@Query("account") String account,@Query("sessionid") int sessionid,@Body RequestBody route);

    @GET("logout")
    Observable<HttpResults> logout();

    @GET("info")
    Observable<HttpResults<Info>> getUserInfo(@Query("ftoken") String token);

    @GET("resetPwd")
    Observable<HttpResults> resetpwd(@Query("ftoken") String token,@Query("foldPwd") String oldPwd,@Query("fnewPwd") String newPwd);

    @GET("auth")
    Observable<HttpResults> auth(@Query("faccount") String account);

    @FormUrlEncoded
    @POST("create")
    Observable<HttpResults> create(@Field("faccount") String account,
                                   @Field("fcode") String code,
                                   @Field("fpwd") String pwd,
                                   @Field("fname") String name,
                                   @Field("ftel") String tel,
                                   @Field("fagent") String agent,
                                   @Field("faddr") String address,
                                   @Field("fagenttel") String agenttel);

    @GET("getCode")
    Observable<HttpResults> getCode(@Query("faccount") String account);

    @GET("forgetPwd")
    Observable<HttpResults> forgetPwd(@Query("faccount") String account,
                                      @Query("fcode") String code,
                                      @Query("fnewPwd") String pwd);

    @FormUrlEncoded
    @POST("trader/unbind")
    Observable<HttpResults> unbindevice(@Field("fkey") String key,
                                      @Field("fdeviceid") String deviceid,
                                      @Field("fuserid") String token);

}
