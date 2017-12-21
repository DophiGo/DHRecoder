package com.example.user.qrrecoder.http.retrofit;

import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.eventbus.eventbusaction.ForBidenAction;
import com.example.user.qrrecoder.eventbus.eventbusaction.LogoutAction;
import com.example.user.qrrecoder.eventbus.eventbusaction.UserAction;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.utils.HttpErroStringUtils;
import com.example.user.qrrecoder.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by dxs on 2017/12/21.
 */

public abstract class BaseObserver<T extends HttpResults> implements Observer<T> {

    public abstract void onSuccess(T t);

    public abstract void onHttpError(Throwable e);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
        onComplete();
    }

    @Override
    public void onError(Throwable e) {
        if (HttpError.ERROR_801011004.equals(e.getMessage())) {
            //回到登陆页
            EventBus.getDefault().post(new LogoutAction());
        } else if (HttpError.ERROR_801011003.equals(e.getMessage())) {
            EventBus.getDefault().post(new ForBidenAction());
        } else {
            String toast = HttpErroStringUtils.getShowStringByException(e);
            ToastUtils.ShowError(MyApp.app, toast, 1500, false);
        }
        onHttpError(e);
        onComplete();
    }

    @Override
    public void onComplete() {

    }
}
