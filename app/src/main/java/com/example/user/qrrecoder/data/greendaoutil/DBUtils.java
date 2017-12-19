package com.example.user.qrrecoder.data.greendaoutil;

import com.example.user.qrrecoder.data.greendaoauto.DeviceItemDao;
import com.example.user.qrrecoder.data.greendaoauto.UserDao;

/**
 * Created by USER on 2017/11/7.
 */

public class DBUtils {
    private static DeviceItemService deviceItemService;
    private static UserService userService;

    private static DeviceItemDao getDeviceItemDao() {
        return GreenDaoUtils.getDaoSession().getDeviceItemDao();
    }

    public static DeviceItemService getDeviceItemService() {
        if (deviceItemService == null) {
            deviceItemService = new DeviceItemService(getDeviceItemDao());
        }
        return deviceItemService;
    }

    private static UserDao getUserDao() {
        return GreenDaoUtils.getDaoSession().getUserDao();
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService(getUserDao());
        }
        return userService;
    }
}
