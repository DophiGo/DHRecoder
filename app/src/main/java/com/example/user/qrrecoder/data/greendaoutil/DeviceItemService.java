package com.example.user.qrrecoder.data.greendaoutil;

import com.example.user.qrrecoder.data.greendao.DeviceItem;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by USER on 2017/11/7.
 */

public class DeviceItemService extends BaseService<DeviceItem,Long> {
    public DeviceItemService(AbstractDao dao) {
        super(dao);
    }
}
