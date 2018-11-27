package com.example.user.qrrecoder.activity;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.base.BaseFullScreenActivity;
import com.example.user.qrrecoder.data.greendao.DeviceItem;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoauto.DeviceItemDao;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.entity.DeviceInfo;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.retrofit.BaseObserver;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.DeviceUtils;
import com.example.user.qrrecoder.utils.MusicUtils;
import com.example.user.qrrecoder.utils.StringUtils;
import com.example.user.qrrecoder.utils.ToastUtils;
import com.hdl.elog.ELog;

import org.greenrobot.greendao.query.QueryBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import io.reactivex.disposables.Disposable;

/**
 * Created by USER on 2017/11/10.
 */

public class ZbarActivity extends BaseFullScreenActivity implements QRCodeView.Delegate {
    private final static String FORMAT_TOAST = "扫描成功\n设备ID:%s\n设备序列号:%s";
    @BindView(R.id.zbarview)
    ZXingView zbarview;
    @BindView(R.id.btn_stop)
    ImageView btnStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar);
        ButterKnife.bind(this);
        initZbar();
    }

    private void initZbar() {
        zbarview.setDelegate(this);
    }

    private void starCamer() {
        zbarview.startCamera();
        zbarview.changeToScanQRCodeStyle();
        zbarview.setType(BarcodeType.ONLY_QR_CODE, null);
        zbarview.startSpotAndShowRect();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        zbarview.startSpotDelay(2000);
        try {
            DeviceInfo info=CreateDeviceItem(result);
            showUnBindDialog(info);
        } catch (Exception e) {
            e.printStackTrace();
            //二维码不正确
            ToastUtils.ShowError(this, getErrorQR(result), 1500, true);
        }
    }

    private void showUnBindDialog(final DeviceInfo info){
        if(TextUtils.isEmpty(info.getDeviceID())){
            //二维码不正确
            ToastUtils.ShowError(this, info.getQrContents(), 1500, true);
            return;
        }
        new MaterialDialog.Builder(this)
                .title(R.string.unbind)
                .content(StringUtils.getFormat(getString(R.string.unbind_tips),info.getDeviceID()))
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        unbindDevice(info);
                    }
                })
                .show();
    }

    //解绑设备
    private void unbindDevice(final DeviceInfo info){
        HttpSend.getInstence().unbindDevice(activeUser.getToken(), info.getDeviceID(), new BaseObserver<HttpResults>() {
            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                showBaseLoadingDialog(R.string.unbind);
            }

            @Override
            public void onSuccess(HttpResults httpResults) {
                showUnbindSuccess(info);
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

    //解绑成功
    private void showUnbindSuccess(DeviceInfo info){
        String toast=StringUtils.getFormat(getString(R.string.unbind_success),info.getDeviceID());
        new MaterialDialog.Builder(this)
                .title(R.string.unbind)
                .content(toast)
                .positiveText(R.string.scan)
                .negativeText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                      //Empty
                    }
                })
                .show();
    }



    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    private MusicUtils musictils;

    private void ding() {
        if (musictils == null) {
            musictils = new MusicUtils();
            musictils.initPoolMusic(this, R.raw.ding);
        }
        musictils.playPoolMusic();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        ELog.dxs("打开相机出错");
    }

    private String getToastContent(DeviceItem item) {
        return String.format(FORMAT_TOAST, item.getDeviceid(), item.getDeviceuuid());
    }

    //扫码得到的信息创建设备(会抛异常)
    private DeviceInfo CreateDeviceItem(String deviceInfo) {
        return new DeviceInfo(deviceInfo);
    }

    //拼接不正确二维码提示("不正确的二维码(result)")
    private String getErrorQR(String result) {
        StringBuilder sb = new StringBuilder(getString(R.string.error_qrillegl));
        sb.append("(");
        sb.append(result);
        sb.append(")");
        return sb.toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        starCamer();
    }

    @Override
    protected void onStop() {
        zbarview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zbarview.onDestroy();
        if (musictils != null) {
            musictils.releaseMusic();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    @OnClick(R.id.btn_stop)
    public void onViewClicked() {
        finish();
    }
}
