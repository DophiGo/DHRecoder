package com.example.user.qrrecoder.data.greendaoutil;

import android.content.Context;

import com.example.user.qrrecoder.data.greendaoauto.DaoMaster;
import com.example.user.qrrecoder.data.greendaoauto.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;


/**
 * Created by USER on 2017/11/7.
 */

public class GreenDaoUtils {
    private static final String DEFAULT_DB_NAME="qrcoder_db";
    private static String DB_NAME;
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            MasterDaoHelper helper = new MasterDaoHelper(mContext, DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            daoMaster = getDaoMaster();
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static void enableQueryBuilderLog() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

}
