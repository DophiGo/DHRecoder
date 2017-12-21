package com.example.user.qrrecoder.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.user.qrrecoder.BuildConfig;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dxs on 2017/12/21.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.tx_version)
    TextView txVersion;

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.about);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        txVersion.setText(AppUtils.getAppVersion());
    }
}
