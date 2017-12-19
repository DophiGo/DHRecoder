package com.example.user.qrrecoder.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.activity.LoginActivity;
import com.example.user.qrrecoder.utils.ToastUtils;

/**
 * Created by USER on 2017/11/8.
 */

public abstract class BaseCoreActivity extends AppCompatActivity {
    public abstract boolean initBar();
    public abstract boolean destroyBar();

    public void toLogin(){
        Intent login = new Intent(this, LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
    }
}
