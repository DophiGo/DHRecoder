package com.example.user.qrrecoder.adapter;

import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dxs on 2017/12/21.
 */

public abstract class BaseViewBinder<T,VH extends RecyclerView.ViewHolder> extends ItemViewBinder<T,VH>{
    public BaseAdapterOnClickListnerImp listnerImp;
    public void setItemClickListner(BaseAdapterOnClickListnerImp listnerImp){
        this.listnerImp=listnerImp;
    }

    public void ItemClick(T t){
        if(listnerImp!=null){
            listnerImp.onItemClick(t);
        }
    }
}
