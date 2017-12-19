package com.example.user.qrrecoder.eventbus.eventbusaction;

import com.example.user.qrrecoder.data.greendao.User;

/**
 * Created by USER on 2017/11/9.
 * 带用户的传递
 */

public class UserAction {
    private User user;

    public UserAction(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
