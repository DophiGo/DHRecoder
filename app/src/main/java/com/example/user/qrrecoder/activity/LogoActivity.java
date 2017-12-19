package com.example.user.qrrecoder.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoauto.UserDao;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.eventbus.eventbusaction.UserAction;
import com.example.user.qrrecoder.utils.AppUtils;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.QueryBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dxs on 2017/12/6.
 */

public class LogoActivity extends BaseActivity {
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tx_version)
    TextView txVersion;

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_logo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initUI();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toAPP();
            }
        }, 1500);
    }

    private void initUI() {
        txVersion.setText(AppUtils.getAppVersion());
    }

    private void toAPP() {
        boolean islogin = SharedPrefreUtils.getInstance().getBooleanData(this, SPKey.SP_ISLOGIN);
        if (islogin) {
            toMainActivity();
        } else {
            toLoginActivity();
        }
        finish();
    }

    private void toLoginActivity() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    private void toMainActivity() {
        String useraccount = SharedPrefreUtils.getInstance().getStringData(this, SPKey.SP_ACTIVEUSER);
        if (TextUtils.isEmpty(useraccount)) {
            toLoginActivity();
            return;
        }
        QueryBuilder<User> builder = DBUtils.getUserService().queryBuilder();
        User user = builder.where(UserDao.Properties.Acount.eq(useraccount)).unique();
        if (user == null) {
            toLoginActivity();
            return;
        }
        Intent main = new Intent(this, MainActivity.class);
//        startActivity(main);
        startActivity(main, ActivityOptions.makeSceneTransitionAnimation(this,ivLogo,getString(R.string.share_logo)).toBundle());
        EventBus.getDefault().postSticky(new UserAction(user));
    }
}
