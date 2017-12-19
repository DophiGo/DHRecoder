package com.example.user.qrrecoder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.base.BaseFullScreenActivity;
import com.example.user.qrrecoder.data.greendao.DeviceItem;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoauto.DeviceItemDao;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.utils.DeviceUtils;
import com.example.user.qrrecoder.utils.MusicUtils;
import com.example.user.qrrecoder.utils.ToastUtils;
import com.hdl.elog.ELog;

import org.greenrobot.greendao.query.QueryBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * Created by USER on 2017/11/10.
 */

public class ZbarActivity extends BaseFullScreenActivity implements QRCodeView.Delegate {
    private final static String FORMAT_TOAST = "扫描成功\n设备ID:%s\n设备序列号:%s";
    @BindView(R.id.zbarview)
    ZBarView zbarview;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.tx_scan_number)
    TextView txScanNumber;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar);
        ButterKnife.bind(this);
        initZbar();
        user = MyApp.getActiveUser();
        if (user==null) {
            ToastUtils.ShowError(this,getString(R.string.user_info_error),1500,true);
            toLogin();
        }
    }

    private void initZbar() {
        zbarview.setDelegate(this);
        changeCount(0);
    }

    private void starCamer() {
        zbarview.startSpotDelay(800);
        zbarview.startCamera();
//        zbarview.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        zbarview.showScanRect();
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        zbarview.startSpotDelay(800);
        try {
            String deviceInfo = DeviceUtils.getDeviceinfo(result);
            if (deviceInfo != null) {
                DeviceItem item = CreateDeviceItem(deviceInfo);
                DBUtils.getDeviceItemService().saveOrUpdate(item);
                ToastUtils.ShowScanSuccess(this, getToastContent(item));
                changeCount(getUnUploadRecord());
                ding();
                vibrate();
            } else {
                throw new IllegalArgumentException("deviceInfo is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //二维码不正确
            ToastUtils.ShowError(this, getErrorQR(result), 1500, true);
        }
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

    private int getUnUploadRecord() {
        QueryBuilder<DeviceItem> queryBuilder = DBUtils.getDeviceItemService().queryBuilder();
        queryBuilder.where(DeviceItemDao.Properties.Faccount.eq(user.getAcount()));
        queryBuilder.where(DeviceItemDao.Properties.ServerState.eq(0));
        return queryBuilder.list().size();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        ELog.dxs("打开相机出错");
    }

    private void changeCount(long count) {
        txScanNumber.setText(String.valueOf(count));
    }

    private String getToastContent(DeviceItem item) {
        return String.format(FORMAT_TOAST, item.getDeviceid(), item.getDeviceuuid());
    }

    //扫码得到的信息创建设备(会抛异常)
    private DeviceItem CreateDeviceItem(String deviceInfo) {
        long time = System.currentTimeMillis();
        String[] info = DeviceUtils.getDeviceInfoFromeUrl(deviceInfo);
        DeviceItem item = new DeviceItem();
        item.setDeviceuuid(info[0]);
        item.setDeviceid(Integer.parseInt(info[1]));
        item.setScantime(time);
        item.setUserid(user.getUserid());
        item.setFaccount(user.getAcount());
        return item;
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

    @OnClick({R.id.btn_stop, R.id.tx_scan_number})
    public void onViewClicked(View view) {
        Intent list = new Intent(this, ScanResultActivity.class);
        startActivityForResult(list, 1);
        if (view.getId() == R.id.btn_stop) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                changeCount(0);
            }
        }
    }
}
