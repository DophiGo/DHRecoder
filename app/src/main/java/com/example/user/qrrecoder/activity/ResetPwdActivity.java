package com.example.user.qrrecoder.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Button;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.retrofit.BaseObserver;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * Created by dxs on 2017/12/21.
 */

public class ResetPwdActivity extends BaseActivity {
    @BindView(R.id.et_oldpwd)
    TextInputEditText etOldpwd;
    @BindView(R.id.et_pwd)
    TextInputEditText etPwd;
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.btn_change)
    Button btnChange;
    @Override
    protected int getConstomLayout() {
        return R.layout.activity_resetpwd;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.change_pwd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        String oldPwd=etOldpwd.getText().toString().trim();
        String pwd=etPwd.getText().toString().trim();
        String pwdAgen=etUsername.getText().toString().trim();

        if(TextUtils.isEmpty(oldPwd)){
            ToastUtils.ShowError(context,getString(R.string.set_pwd),1500,true);
            return;
        }
        if(TextUtils.isEmpty(pwd)||TextUtils.isEmpty(pwdAgen)||!pwd.equals(pwdAgen)){
            ToastUtils.ShowError(context,getString(R.string.pwd_not),1500,true);
            return;
        }
        resetPwd(activeUser,oldPwd,pwdAgen);
    }

    private void resetPwd(User user,String oldPwd,String newPwd){
        HttpSend.getInstence().resetPwd(user.getToken(), oldPwd, newPwd, new BaseObserver<HttpResults>() {

            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                showBaseLoadingDialog(R.string.change_pwd);
            }

            @Override
            public void onSuccess(HttpResults httpResults) {
                ToastUtils.ShowSuccess(context,getString(R.string.reset_pwd_success));
                toLogin();
                finish();
            }

            @Override
            public void onHttpError(Throwable e) {

            }

            @Override
            public void onComplete() {
                super.onComplete();
                DissMissLoadingDialog();
            }
        });
    }
}
