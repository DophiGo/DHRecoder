package com.example.user.qrrecoder.data.greendaoutil;

import com.example.user.qrrecoder.data.greendao.User;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by USER on 2017/11/7.
 */

public class UserService extends BaseService<User,Long> {
    public UserService(AbstractDao dao) {
        super(dao);
    }
}
