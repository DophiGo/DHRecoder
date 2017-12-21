package com.example.user.qrrecoder.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.activity.LoginActivity;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.eventbus.eventbusaction.ForBidenAction;
import com.example.user.qrrecoder.eventbus.eventbusaction.LogoutAction;
import com.example.user.qrrecoder.eventbus.eventbusaction.UserAction;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by USER on 2017/11/8.
 */

public abstract class BaseCoreActivity extends AppCompatActivity {
    private static final String INSTANCEKEY_USER="user";
    public abstract boolean initBar();
    public abstract boolean destroyBar();
    public abstract void clearLoginData();
    public User activeUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SharedPrefreUtils.getInstance().getBooleanData(this, SPKey.SP_ISLOGIN)){
            initActiveUser(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    //在别处登陆
    @Subscribe()
    public void onLogout(LogoutAction logoutAction) {
        ToastUtils.ShowError(this,getString(R.string.user_info_error),1500,true);
        toLogin();
    }

    //账号被禁用
    @Subscribe()
    public void onForbiden(ForBidenAction action){
        ToastUtils.ShowError(this,getString(R.string.http_er_10902013),1500,true);
        toLogin();
    }

    private void initActiveUser(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            activeUser= (User) savedInstanceState.getSerializable(INSTANCEKEY_USER);
        }
        if(activeUser==null){
            activeUser= MyApp.getActiveUser();
            if(activeUser==null){
                ToastUtils.ShowError(this,getString(R.string.user_info_error),1500,true);
                toLogin();
                finish();
            }
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(INSTANCEKEY_USER,activeUser);
        super.onSaveInstanceState(outState);
    }

    public void toLogin(){
        clearLoginData();
        Intent login = new Intent(this, LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(login);
    }

    private MaterialDialog.Builder builder;
    private MaterialDialog dialog;

    public void showBaseLoadingDialog(@StringRes int title, @StringRes int contant) {
        builder = new MaterialDialog.Builder(this)
                .title(title)
                .content(contant)
                .progress(true, 0);
        dialog=builder.build();
        dialog.show();
    }

    public void showBaseLoadingDialog(@StringRes int contant) {
        showBaseLoadingDialog(R.string.app_name, contant);
    }
    public void DissMissLoadingDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }
}
