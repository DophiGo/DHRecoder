package com.example.user.qrrecoder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.bean.DeviceItemHeader;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by USER on 2017/11/10.
 */

public class DevitemHeaderViewBinder extends ItemViewBinder<DeviceItemHeader,DevitemHeaderViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return null;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DeviceItemHeader item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textNumber;
        private TextView textDeviceID;
        private TextView textServerState;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumber=itemView.findViewById(R.id.tx_number);
            textDeviceID=itemView.findViewById(R.id.tx_deviceid);
            textServerState=itemView.findViewById(R.id.tx_serverstate);
        }
    }
}
