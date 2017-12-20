package com.example.user.qrrecoder.activity;

import android.os.Bundle;
import android.widget.Button;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dxs on 2017/12/20.
 */

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.btn_logout)
    Button btnLogout;

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        toLogin();
    }
}
