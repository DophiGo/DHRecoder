package com.example.user.qrrecoder.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoauto.UserDao;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.data.greendaoutil.GreenDaoUtils;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by USER on 2017/11/7.
 */

public class MyApp extends Application {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        initGreenDao();
        initBugly();
    }

    //GreenDao初始化
    private void initGreenDao(){
        GreenDaoUtils.init(this);
    }

    //Bugly初始化
    private void initBugly(){
        Bugly.init(this, "abd986cd97", false);
    }

    //TODO 每次都查询数据库不太友好，可考虑在内存缓存，内存中没有的时候再去数据库查找
    public static User getActiveUser(){
        String account= SharedPrefreUtils.getInstance().getStringData(app,SPKey.SP_ACTIVEUSER);
        if(TextUtils.isEmpty(account)){
            return null;
        }
        QueryBuilder<User> builder=DBUtils.getUserService().queryBuilder();
        builder.where(UserDao.Properties.Acount.eq(account));
        return builder.unique();
    }
}
