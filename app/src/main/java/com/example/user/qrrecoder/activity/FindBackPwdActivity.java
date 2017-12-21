package com.example.user.qrrecoder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.retrofit.BaseObserver;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.HttpErroStringUtils;
import com.example.user.qrrecoder.utils.StringUtils;
import com.example.user.qrrecoder.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dxs on 2017/12/20.
 */

public class FindBackPwdActivity extends BaseActivity {
    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @BindView(R.id.et_emailcode)
    TextInputEditText etEmailcode;
    @BindView(R.id.btn_emailcode)
    TextView btnEmailcode;
    @BindView(R.id.et_pwd)
    TextInputEditText etPwd;
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.btn_change)
    Button btnChange;

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_findbackpwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.forget_pwd);
    }

    @OnClick({R.id.btn_emailcode, R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_emailcode:
                String accont=etEmail.getText().toString().trim();
                if(!StringUtils.isEmail(accont)){
                    ToastUtils.ShowError(context, getString(R.string.error_email_format), 1500, false);
                    return;
                }
                getEmailCode(accont);
                break;
            case R.id.btn_change:
                String account=etEmail.getText().toString().trim();
                String code=etEmailcode.getText().toString().trim();
                String newpwd=etPwd.getText().toString().trim();
                String newpwdagen=etUsername.getText().toString().trim();
                if(!StringUtils.isEmail(account)){
                    etEmail.setError(getString(R.string.error_info_less));
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    etEmailcode.setError(getString(R.string.error_info_less));
                    return;
                }
                if(TextUtils.isEmpty(newpwd)){
                    etPwd.setError(getString(R.string.error_info_less));
                    return;
                }
                if(TextUtils.isEmpty(newpwdagen)){
                    etUsername.setError(getString(R.string.error_info_less));
                    return;
                }
                if(!newpwd.equals(newpwdagen)){
                    etUsername.setError(getString(R.string.pwd_not));
                    return;
                }
                setNewPwd(account,code,newpwd);
                break;
        }
    }

    private void getEmailCode(String account){
        HttpSend.getInstence().getCode(account, new BaseObserver<HttpResults>() {

            @Override
            public void onSubscribe(Disposable d) {
                showBaseLoadingDialog(R.string.send_email_code);
            }

            @Override
            public void onSuccess(HttpResults httpResults) {
                ToastUtils.ShowSuccess(context,getString(R.string.code_send_success));
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

    private void setNewPwd(String account,String code,String pwd){
        HttpSend.getInstence().forgetPwd(account, code,pwd,new BaseObserver<HttpResults>() {
            @Override
            public void onSuccess(HttpResults httpResults) {
                ToastUtils.ShowSuccess(context,getString(R.string.opration_success));
                toLoginActivity();
            }

            @Override
            public void onHttpError(Throwable e) {

            }

            @Override
            public void onSubscribe(Disposable d) {
                showBaseLoadingDialog(R.string.forget_pwd);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                DissMissLoadingDialog();
            }
        });
    }

    private void toLoginActivity() {
        toLogin();
        finish();
    }
}
