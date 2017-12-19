package com.example.user.qrrecoder.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.transition.Fade;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.eventbus.eventbusaction.UserAction;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.SizeUtils;
import com.hdl.elog.ELog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tx_userinfo)
    TextView txUserinfo;
    @BindView(R.id.tx_scanresult)
    TextView txScanresult;
    @BindView(R.id.rl_logout)
    RelativeLayout rlLogout;
    @BindView(R.id.btn_scan)
    Button btnScan;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mContext = this;
        getWindow().setReturnTransition(new Fade().setDuration(300));
    }

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setToolBarNavigation() {
        //不需要返回箭头
        toolbar.setContentInsetsAbsolute(SizeUtils.dp2px(TOOLBAR_MARGING), 0);
    }

    @Subscribe(sticky = true)
    public void onUserLoginSuccess(UserAction userAction) {
        User user = userAction.getUser();
        StringBuilder sb = new StringBuilder();
        sb.append("编号：" + user.getUserid());
        sb.append("\n");
        sb.append("姓名：" + user.getUsername());
        sb.append("\n");
        sb.append("联系：" + user.getAcount());
        sb.append("\n");
        sb.append("公司：" + user.getFname());
        sb.append("\n");
        txUserinfo.setText(sb.toString());
    }

    @OnClick({R.id.btn_scan, R.id.tx_scanresult, R.id.rl_logout})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_scan) {
            RequirePermission();
        } else if (view.getId() == R.id.tx_scanresult) {
            //去扫码列表页
            toScanRecord();
        } else if (view.getId() == R.id.rl_logout) {
            LogOut();
        }
    }

    private void LogOut() {
        new MaterialDialog.Builder(this)
                .title(R.string.logout)
                .content(R.string.logout_tips)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        SharedPrefreUtils.getInstance().putBooleanData(mContext, SPKey.SP_ISLOGIN, false);
                        logoutFromServer();
                    }
                })
                .show();
    }

    //不管结果如何都跳转到登陆页(欠考虑)
    private void logoutFromServer() {
        HttpSend.getInstence().logout(new Observer<HttpResults>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResults httpResults) {

            }

            @Override
            public void onError(Throwable e) {
                toLogin();
                finish();
            }

            @Override
            public void onComplete() {
                toLogin();
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void RequirePermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        ELog.dxs("aBoolean:" + aBoolean);
                        Intent scan = new Intent(MainActivity.this, ZbarActivity.class);
                        startActivity(scan);
//                        Login();
                    }
                });
    }

    private void toScanRecord() {
        Intent list = new Intent(this, ScanResultActivity.class);
        startActivity(list);
    }
}
