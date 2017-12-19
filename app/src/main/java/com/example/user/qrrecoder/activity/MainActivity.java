package com.example.user.qrrecoder.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.transition.Fade;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.APPConfig;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.eventbus.eventbusaction.UserAction;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.Enty.Info;
import com.example.user.qrrecoder.http.Enty.LoginResult;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.HttpErroStringUtils;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.SizeUtils;
import com.example.user.qrrecoder.utils.ToastUtils;
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

    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mContext = this;
        getWindow().setReturnTransition(new Fade().setDuration(300));

        initNaviView();
    }

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_main;
    }

    private void initNaviView() {
        //设置抽屉DrawerLayout
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.upload_state, R.string.upload_record);
        mDrawerToggle.syncState();//初始化状态
        drawerLayout.addDrawerListener(mDrawerToggle);

        //设置导航栏NavigationView的点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_userinfo:
                        Log.e("dxsTest", "menuItem:" + "nav_userinfo");
                        break;
                    case R.id.nav_changepwd:
                        Log.e("dxsTest", "menuItem:" + "nav_changepwd");
                        break;
                    case R.id.nav_share:
                        Log.e("dxsTest", "menuItem:" + "nav_share");
                        break;
                    case R.id.nav_about_app:
                        Log.e("dxsTest", "menuItem:" + "nav_about_app");
                        break;
                }
                //menuItem.setChecked(true);//点击了把它设为选中状态
                drawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });
    }


    @Override
    public void setToolBarNavigation() {
        //不需要返回箭头
        toolbar.setContentInsetsAbsolute(SizeUtils.dp2px(TOOLBAR_MARGING), 0);
    }

    @Subscribe(sticky = true)
    public void onUserLoginSuccess(UserAction userAction) {
        updateUiInfo(userAction.getUser());
        Log.e("dxsTest", "onUserLoginSuccess:" + userAction.getUser().toString());
        //getUserInfo(userAction.getUser());
    }

    @OnClick({R.id.btn_scan})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_scan) {
            RequirePermission();
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

    private void getUserInfo(final User user) {
        HttpSend.getInstence().getInfo(user.getToken(), new Observer<Info>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Info info) {
                user.setFname(info.getFname());
                user.setAgent(info.getFagent());
                user.setAgenttel(info.getFagenttel());
                user.setTel(info.getFtel());
                user.setAddress(info.getFaddr());
                DBUtils.getUserService().saveOrUpdate(user);
            }

            @Override
            public void onError(Throwable e) {
                //统一处理非法账号
            }

            @Override
            public void onComplete() {
            }
        });
    }

    private void updateUiInfo(User user) {
        View view = navView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.tx_username);
        TextView userAccount = view.findViewById(R.id.tx_email);
        userName.setText(user.getFname());
        userAccount.setText(user.getAcount());
    }
}
