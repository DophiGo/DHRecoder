package com.example.user.qrrecoder.data.greendaoutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.qrrecoder.data.greendaoauto.DaoMaster;
import com.example.user.qrrecoder.data.greendaoauto.DeviceItemDao;
import com.example.user.qrrecoder.data.greendaoauto.UserDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;


/**
 * Created by USER on 2017/11/7.
 */

public class MasterDaoHelper extends DaoMaster.OpenHelper {
    public MasterDaoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }
            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },DeviceItemDao.class, UserDao.class);
    }
}
