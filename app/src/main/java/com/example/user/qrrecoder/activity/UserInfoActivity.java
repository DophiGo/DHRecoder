package com.example.user.qrrecoder.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.adapter.BaseAdapterOnClickListnerImp;
import com.example.user.qrrecoder.adapter.DeviceItemViewBinder;
import com.example.user.qrrecoder.adapter.EmptyViewBinder;
import com.example.user.qrrecoder.adapter.GroupButtonViewBinder;
import com.example.user.qrrecoder.adapter.GroupItemViewBinder;
import com.example.user.qrrecoder.adapter.GroupTitleViewBinder;
import com.example.user.qrrecoder.app.MyApp;
import com.example.user.qrrecoder.app.SPKey;
import com.example.user.qrrecoder.base.BaseActivity;
import com.example.user.qrrecoder.bean.EmptyView;
import com.example.user.qrrecoder.bean.GroupTitle;
import com.example.user.qrrecoder.bean.ItemButton;
import com.example.user.qrrecoder.bean.ItemTitle;
import com.example.user.qrrecoder.data.greendao.DeviceItem;
import com.example.user.qrrecoder.data.greendao.User;
import com.example.user.qrrecoder.utils.SharedPrefreUtils;
import com.example.user.qrrecoder.utils.ToastUtils;
import com.hdl.elog.ELog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by dxs on 2017/12/20.
 */

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.ry_userinfo)
    RecyclerView ryUserinfo;

    private MultiTypeAdapter adapter;
    private Items items;

    @Override
    protected int getConstomLayout() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    public void setToolBarTitle() {
        toolbar.setTitle(R.string.user_info);
    }

    private void initData() {
        adapter = new MultiTypeAdapter();
        /* 注册类型和 View 的对应关系 */
        adapter.register(GroupTitle.class, new GroupTitleViewBinder());
        adapter.register(ItemTitle.class, new GroupItemViewBinder());
        GroupButtonViewBinder binder=new GroupButtonViewBinder();
        binder.setItemClickListner(imp);
        adapter.register(ItemButton.class, binder);
        ryUserinfo.setLayoutManager(new LinearLayoutManager(this));
        ryUserinfo.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        ryUserinfo.setAdapter(adapter);

        initItems();
        adapter.setItems(items);
        adapter.notifyItemRangeChanged(0, items.size() - 1);
    }

    private void initItems(){
        items = new Items();
        GroupTitle titleUserInfo=new GroupTitle(getString(R.string.userinfo));
        GroupTitle titleCompanyInfo=new GroupTitle(getString(R.string.company_info));

        ItemTitle itemName=new ItemTitle(getString(R.string.user_name),activeUser.getFname());
        ItemTitle itemTel=new ItemTitle(getString(R.string.user_tel),activeUser.getTel());
        ItemTitle itemEmail=new ItemTitle(getString(R.string.user_account),activeUser.getAcount());
        ItemTitle itemCompanyName=new ItemTitle(getString(R.string.company_name),activeUser.getAgent());
        ItemTitle itemCompanyTel=new ItemTitle(getString(R.string.compony_tel),activeUser.getAgenttel());
        ItemTitle itemCompanyAddr=new ItemTitle(getString(R.string.company_addr),activeUser.getAddress());

        ItemButton itemButton=new ItemButton(getString(R.string.logout));

        items.add(titleUserInfo);
        items.add(itemName);
        items.add(itemTel);
        items.add(itemEmail);
        items.add(titleCompanyInfo);
        items.add(itemCompanyName);
        items.add(itemCompanyTel);
        items.add(itemCompanyAddr);
        items.add(itemButton);
    }

    private BaseAdapterOnClickListnerImp imp=new BaseAdapterOnClickListnerImp<ItemButton>() {
        @Override
        public void onItemClick(ItemButton itemButton) {
            //退出登陆
            showLogoutDialog();
        }
    };

    private void showLogoutDialog(){
        new MaterialDialog.Builder(this)
                .title(R.string.logout)
                .content(R.string.logout_tips)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        toLogin();
                        finish();
                    }
                })
                .show();
    }
}
