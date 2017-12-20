package com.example.user.qrrecoder.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.http.Enty.HttpResults;
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

                break;
        }
    }

    private void getEmailCode(String account){
        HttpSend.getInstence().getCode(account, new Observer<HttpResults>() {
            @Override
            public void onSubscribe(Disposable d) {
                showBaseLoadingDialog(R.string.send_email_code);
            }

            @Override
            public void onNext(HttpResults httpResults) {
                ToastUtils.ShowSuccess(context,getString(R.string.code_send_success));
            }

            @Override
            public void onError(Throwable e) {
                String toast= HttpErroStringUtils.getShowStringByException(e);
                ToastUtils.ShowError(context, toast, 1500, false);
            }

            @Override
            public void onComplete() {
                DissMissLoadingDialog();
            }
        });
    }
}
