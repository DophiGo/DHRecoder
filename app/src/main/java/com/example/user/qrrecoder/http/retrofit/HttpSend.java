package com.example.user.qrrecoder.http.retrofit;

import com.example.user.qrrecoder.app.APPConfig;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.bean.UserInfoRegist;
import com.example.user.qrrecoder.entity.UploadRecords;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.Enty.Info;
import com.example.user.qrrecoder.http.Enty.LoginResult;
import com.example.user.qrrecoder.http.api.ApiService;
import com.example.user.qrrecoder.utils.MD5;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.security.MessageDigest;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dxs on 2017/11/17.
 * web 请求集合
 * 暂时未作封装，直接使用Retrofit的API，很多重复代码需要封装配置
 */

public class HttpSend {
    //APP地址
    private final static String BASEURL = "http://oss.zhiduodev.com/oss/app/operator/";
    //解绑地址
    private final static String BASEURL_UNBIND = "http://api.zhiduodev.com/";

    private static class HtpSendHolder {
        public static final HttpSend INSTENCE = new HttpSend();
    }

    private HttpSend() {

    }

    public static HttpSend getInstence() {
        return HtpSendHolder.INSTENCE;
    }

    ClearableCookieJar cookieJar;

    public void login(String userName, String userPwd, BaseObserver<LoginResult> observer) {
        ApiService service=getService();
        userPwd = entryPwd(userPwd);
        service.login(userName, userPwd)
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void uploadRecord(UploadRecords records, Observer<String> observer) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别
        //设置 Debug Log 模式
        // init cookie manager
        if (cookieJar == null) {
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.app));
        }
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        //"application/json; charset=utf-8"
        RequestBody body =
                RequestBody.create(okhttp3.MediaType.parse("application/json"), records.getJsonString());
        ApiService uploadRecord = retrofit.create(ApiService.class);
        uploadRecord.uploadRecord(records.getUserId(), records.getSessionid(), body)
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void logout(Observer<HttpResults> observer) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别
        //设置 Debug Log 模式
        // init cookie manager
        if (cookieJar == null) {
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.app));
        }
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        ApiService uploadRecord = retrofit.create(ApiService.class);
        uploadRecord.logout()
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getInfo(String token, BaseObserver<Info> observer) {
        ApiService service=getService();
        service.getUserInfo(token)
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取找回密码验证码
     *
     * @param account  账户（邮箱）
     * @param observer
     */
    public void getCode(String account, BaseObserver<HttpResults> observer) {
        ApiService service=getService();
        service.getCode(account)
                .map(new HttpResultNoneDataFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取找回密码验证码
     *
     * @param account  账户（邮箱）
     * @param observer
     */
    public void forgetPwd(String account,String code,String pwd, BaseObserver<HttpResults> observer) {
        ApiService service=getService();
        pwd=entryPwd(pwd);
        service.forgetPwd(account,code,pwd)
                .map(new HttpResultNoneDataFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 验证账号（发验证码）
     *
     * @param account  账户（邮箱）
     * @param observer
     */
    public void auth(String account, BaseObserver<HttpResults> observer) {
        ApiService service=getService();
        service.auth(account)
                .map(new HttpResultNoneDataFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 申请账号
     *
     * @param account  账号信息
     * @param observer
     */
    public void CreateUser(UserInfoRegist account, BaseObserver<HttpResults> observer) {
        ApiService service=getService();
        String pwd=entryPwd(account.getPwd());
        service.create(account.getAccount(), account.getEmailcode()
                , pwd, account.getName(), account.getTel(), account.getAgent(), account.getAddr(), account.getAgenttel())
                .map(new HttpResultNoneDataFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 重置密码
     * @param token
     * @param oldPwd
     * @param newpwd
     * @param observer
     */
    public void resetPwd(String token,String oldPwd, String newpwd,BaseObserver<HttpResults> observer) {
        ApiService service=getService();
        String old=entryPwd(oldPwd);
        String newp=entryPwd(newpwd);
        service.resetpwd(token,old,newp)
                .map(new HttpResultNoneDataFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 解绑设备
     * @param token
     * @param deviceid 设备ID
     * @param observer
     */
    public void unbindDevice(String token, String deviceid,BaseObserver<HttpResults> observer) {
        ApiService service=getService(BASEURL_UNBIND);
        service.unbindevice(APPConfig.Release.UNBIND_KEY,deviceid,token)
                .map(new HttpResultNoneDataFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    private ApiService getService(){
        return getService(BASEURL);
    }

    private ApiService getService(String url){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Log信息拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别
        //设置 Debug Log 模式
        // init cookie manager
        if (cookieJar == null) {
            cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApp.app));
        }
        builder.addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();

        return retrofit.create(ApiService.class);
    }

    private String entryPwd(String pwd){
        return new MD5().getMD5ofStr(pwd);
    }
}
