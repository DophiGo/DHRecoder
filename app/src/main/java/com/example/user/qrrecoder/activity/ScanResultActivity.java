package com.example.user.qrrecoder.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.adapter.DeviceItemViewBinder;
import com.example.user.qrrecoder.adapter.EmptyViewBinder;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.bean.EmptyView;
import com.example.user.qrrecoder.data.greendao.DeviceItem;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.data.greendaoauto.DeviceItemDao;
import com.example.user.qrrecoder.data.greendaoauto.UserDao;
import com.example.user.qrrecoder.data.greendaoutil.DBUtils;
import com.example.user.qrrecoder.entity.UploadRecords;
import com.example.user.qrrecoder.http.Enty.HttpResults;
import com.example.user.qrrecoder.http.retrofit.HttpSend;
import com.example.user.qrrecoder.utils.HttpErroStringUtils;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.ToastUtils;
import com.hdl.elog.ELog;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by USER on 2017/11/10.
 */

public class ScanResultActivity extends BaseActivity {
    @BindView(R.id.recy_deviceitem)
    RecyclerView recyDeviceitem;
    @BindView(R.id.fab_upload)
    FloatingActionButton fabUpload;
    private MultiTypeAdapter adapter;
    private Items items;
    private List<DeviceItem> deviceItems;
    private Context mContext;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mContext = this;
        user= MyApp.getActiveUser();
        if(user==null){
            ToastUtils.ShowError(this,getString(R.string.user_info_error),1500,true);
            toLogin();
        }
        initData();
    }

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_scanresult;
    }

    @Override
    public void setToolBarTitle() {
        if (toolbar != null) {
            toolbar.setTitle(R.string.scan_result);
        }
    }

    private void initData() {
        adapter = new MultiTypeAdapter();
        /* 注册类型和 View 的对应关系 */
        adapter.register(DeviceItem.class, new DeviceItemViewBinder());
        adapter.register(EmptyView.class, new EmptyViewBinder());
        recyDeviceitem.setLayoutManager(new LinearLayoutManager(this));
        recyDeviceitem.setAdapter(adapter);

        items = new Items();
        deviceItems = getUnUploadRecord();
        items.addAll(deviceItems);
        ELog.dxs("size:" + items.size());

        adapter.setItems(items);
        adapter.notifyItemRangeChanged(0, items.size() - 1);
        checkItemEmpty();
    }



    //检查数据源并可能显示空视图
    private void checkItemEmpty() {
        List<DeviceItem> deviceItems = getUnUploadRecord();
        Log.e("dxsTest","deviceItems:"+deviceItems.size());
        if (deviceItems != null && deviceItems.size() <= 0) {
            items.clear();
            items.add(new EmptyView());
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    //获取未上传的扫码记录
    private List<DeviceItem> getUnUploadRecord() {
        QueryBuilder<DeviceItem> builder = DBUtils.getDeviceItemService().queryBuilder();
        builder.where(DeviceItemDao.Properties.Faccount.eq(user.getAcount()));
        builder.where(DeviceItemDao.Properties.ServerState.eq(0));
        builder.orderDesc(DeviceItemDao.Properties.Scantime);
        return builder.list();
    }

    //将扫码记录更新为已上传，并更新数据库
    private void UploadRecord() {
        if (deviceItems != null && deviceItems.size() > 0) {
            for (DeviceItem item : deviceItems) {
                item.setServerState(1);
            }
        }
        DBUtils.getDeviceItemService().update(deviceItems);
        deviceItems.clear();
    }

    private MaterialDialog.Builder builder;

    private void createDialog() {
        builder = new MaterialDialog.Builder(this)
                .title(R.string.app_name)
                .content(R.string.scan_uploading)
                .progress(true, 0);
    }

    private void showSuccessDialog(String successCounts){
        final String toast = String.format(mContext.getString(R.string.upload_success_tip), successCounts);
        new MaterialDialog.Builder(this)
                .title(R.string.upload_success)
                .content(toast)
                .positiveText(R.string.back)
                .negativeText(R.string.scan)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        //回主页
                        Intent toMain=new Intent();
                        toMain.setClass(ScanResultActivity.this,MainActivity.class);
                        toMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(toMain);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        toScanActivity();
                    }
                })
                .show();
    }

    @OnClick(R.id.fab_upload)
    public void onViewClicked() {
        if (deviceItems == null || deviceItems.size() <= 0) {
            ToastUtils.ShowError(mContext, mContext.getString(R.string.scanrecord_empty), 1500, false);
            return;
        }
        createDialog();
        final MaterialDialog dialog = builder.build();
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                dialog.show();
            }

            @Override
            public void onNext(String stringHttpResults) {
                UploadRecord();
                checkItemEmpty();
                setResult(RESULT_OK);
                showSuccessDialog(stringHttpResults);
                ToastUtils.ShowSuccess(mContext, getString(R.string.upload_success));
            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
                String toast= HttpErroStringUtils.getShowStringByException(e);
                ToastUtils.ShowError(mContext, toast, 1500, false);
            }

            @Override
            public void onComplete() {
                dialog.dismiss();
            }
        };

        UploadRecords records = new UploadRecords(user.getAcount(),user.getSessionid(), deviceItems);
        HttpSend.getInstence().uploadRecord(records, observer);
    }

    private void toScanActivity(){
        Intent login = new Intent(this, ZbarActivity.class);
        startActivity(login);
        finish();
    }
}
