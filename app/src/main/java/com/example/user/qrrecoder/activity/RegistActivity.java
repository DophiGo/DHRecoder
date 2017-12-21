package com.example.user.qrrecoder.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.bean.UserInfoRegist;
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

public class RegistActivity extends BaseActivity {

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
    @BindView(R.id.et_usertel)
    TextInputEditText etUsertel;
    @BindView(R.id.et_companyname)
    TextInputEditText etCompanyname;
    @BindView(R.id.et_companytel)
    TextInputEditText etCompanytel;
    @BindView(R.id.et_companyaddr)
    TextInputEditText etCompanyaddr;
    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.rigist);
    }

    @OnClick({R.id.btn_emailcode, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_emailcode:
                //获取email验证码
                String account=etEmail.getText().toString().trim();
                if(!StringUtils.isEmail(account)){
                    ToastUtils.ShowError(context, getString(R.string.error_email_format), 1500, false);
                    return;
                }
                getEmailCode(account);
                break;
            case R.id.btn_ok:
                //提交
                String accounts=etEmail.getText().toString().trim();
                String code=etEmailcode.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                String name=etUsername.getText().toString().trim();
                String tel=etUsertel.getText().toString().trim();
                String compnayname=etCompanyname.getText().toString().trim();
                String compnaytel=etCompanytel.getText().toString().trim();
                String compnayaddr=etCompanyaddr.getText().toString().trim();
                UserInfoRegist userInfoRegist=new UserInfoRegist(accounts,code,pwd,name,tel,compnayname,compnayaddr,compnaytel);
                if(userInfoRegist.isEmpty()){
                    ToastUtils.ShowError(context, getString(R.string.error_info_less), 1500, false);
                    return;
                }
                CreateUser(userInfoRegist);
                break;
        }
    }

    private void CreateUser(UserInfoRegist userInfoRegist){
        HttpSend.getInstence().CreateUser(userInfoRegist, new BaseObserver<HttpResults>() {
            @Override
            public void onSuccess(HttpResults httpResults) {
                ToastUtils.ShowSuccess(context,getString(R.string.rigist_ok));
                toLoginActivity();
            }

            @Override
            public void onHttpError(Throwable e) {

            }

            @Override
            public void onSubscribe(Disposable d) {
                showBaseLoadingDialog(R.string.rigist);
            }

            @Override
            public void onComplete() {
                super.onComplete();
                DissMissLoadingDialog();
            }
        });
    }

    private void getEmailCode(String account){
        HttpSend.getInstence().auth(account, new BaseObserver<HttpResults>() {
            @Override
            public void onSuccess(HttpResults httpResults) {
                ToastUtils.ShowSuccess(context,getString(R.string.code_send_success));
            }

            @Override
            public void onHttpError(Throwable e) {

            }

            @Override
            public void onSubscribe(Disposable d) {
                showBaseLoadingDialog(R.string.send_email_code);
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
