package com.example.user.qrrecoder.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.activity.LoginActivity;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hdl.elog.ELog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by USER on 2017/11/7.
 */

public abstract class BaseActivity extends BaseCoreActivity {
    public static final int TOOLBAR_MARGING = 20;
    public Toolbar toolbar;
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getConstomLayout());
        initToolBar();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setToolBarParemsDefault();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar);
    }

    private void setToolBarParemsDefault() {
        setToolBarNavigation();
        setToolBarTitle();
    }

    public void setToolBarNavigation() {
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_action_back);
        }
    }

    public void setToolBarTitle() {
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
        }
    }

    protected abstract int getConstomLayout();



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackListner(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!destroyBar()) {
            //ImmersionBar.with(this).destroy();
        }
    }

    public void onBackListner(MenuItem item) {
        finish();
    }

    @Override
    public boolean initBar() {
        return false;
    }

    @Override
    public boolean destroyBar() {
        return false;
    }

    @Override
    public void clearLoginData() {
        SharedPrefreUtils.getInstance().putBooleanData(context, SPKey.SP_ISLOGIN,false);
    }
}
