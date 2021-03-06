package com.example.user.qrrecoder.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.APPConfig;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.base.BaseFullScreenActivity;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoauto.UserDao;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.eventbus.eventbusaction.UserAction;
import com.example.user.qrrecoder.http.Enty.LoginResult;
import com.example.user.qrrecoder.http.retrofit.BaseObserver;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.HttpErroStringUtils;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.QueryBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by USER on 2017/11/7.
 */

public class LoginActivity extends BaseFullScreenActivity {

    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.tx_find_pwd)
    TextView txFindPwd;
    @BindView(R.id.tx_rigist)
    TextView txRigist;
    private Context mContext;
    private String account = "";
    private String pwd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext = this;
        initData();
    }

    private void initData() {
        String useraccount = SharedPrefreUtils.getInstance().getStringData(this, SPKey.SP_ACTIVEUSER);
        if (!TextUtils.isEmpty(useraccount)) {
            QueryBuilder<User> builder = DBUtils.getUserService().queryBuilder();
            User user = builder.where(UserDao.Properties.Acount.eq(useraccount)).unique();
            if (user != null) {
                etUsername.setText(user.getAcount());
                etPassword.setText(user.getUserpwd());
            }
        }
    }

    private MaterialDialog.Builder builder;

    private void createDialog() {
        builder = new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .content(R.string.logining)
                .progress(true, 0);
    }

    //检查用户输入
    private boolean checkParems() {
        account = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtils.ShowError(this, getString(R.string.user_account), APPConfig.Release.DEFAULT_TOAST_ERROR_TIME, true);
            return false;
        }

        pwd = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.ShowError(this, getString(R.string.user_pwd), APPConfig.Release.DEFAULT_TOAST_ERROR_TIME, true);
            return false;
        }
        return true;
    }

    private void Login(final String account, final String pwd) {
        final MaterialDialog dialog = builder.build();
        HttpSend.getInstence().login(account, pwd, new BaseObserver<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                User user = new User();
                user.setAcount(result.getFaccount());
                user.setUserpwd(pwd);
                user.setFname(result.getFname());
                user.setToken(result.getFtoken());
                SharedPrefreUtils.getInstance().putStringData(mContext, SPKey.SP_ACTIVEUSER, user.getAcount());
                SharedPrefreUtils.getInstance().putBooleanData(mContext, SPKey.SP_ISLOGIN, true);
                DBUtils.getUserService().saveOrUpdate(user);
                EventBus.getDefault().postSticky(new UserAction(user));
                finish();
            }

            @Override
            public void onHttpError(Throwable e) {
            }

            @Override
            public void onSubscribe(Disposable d) {
                dialog.show();
            }

            @Override
            public void onComplete() {
                dialog.dismiss();
            }
        });
    }

    @OnClick({R.id.bt_go,R.id.tx_rigist,R.id.tx_find_pwd})
    public void onViewClicked(View view) {
        if(view.getId()==R.id.bt_go){
            createDialog();
            if (checkParems()) {
                Login(account, pwd);
            }
        }else if(view.getId()==R.id.tx_rigist){
            toRigist();
        }else if(view.getId()==R.id.tx_find_pwd){
            toFindBackPwd();
        }
    }

    private void toRigist(){
        Intent intent=new Intent(this,RegistActivity.class);
        startActivity(intent);
    }

    private void toFindBackPwd(){
        Intent intent=new Intent(this,FindBackPwdActivity.class);
        startActivity(intent);
    }
}
