package com.example.user.qrrecoder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.data.greendao.DeviceItem;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by USER on 2017/11/10.
 */

public class DeviceItemViewBinder extends ItemViewBinder<DeviceItem, DeviceItemViewBinder.ViewHolder> {
    private static final String DATE_FORMAT="MM-dd HH:mm:ss";

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_device, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull DeviceItem item) {
        holder.textNumber.setText(String.valueOf(holder.getAdapterPosition()+1));
        holder.textDeviceID.setText(String.valueOf(item.getDeviceid()));
        holder.textServerState.setText(getServerStateString(item));
        holder.textRecoderTime.setText(getRecordTimeString(item));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textNumber;
        private TextView textDeviceID;
        private TextView textServerState;
        private TextView textRecoderTime;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumber=itemView.findViewById(R.id.tx_number);
            textDeviceID=itemView.findViewById(R.id.tx_deviceid);
            textServerState=itemView.findViewById(R.id.tx_serverstate);
            textRecoderTime=itemView.findViewById(R.id.tx_recodertime);
        }
    }

    private String getServerStateString(DeviceItem item){
        if(item.getServerState()==0){
            return "未上传";
        }else if(item.getServerState()==1){
            return "已上传";
        }
        return String.valueOf(item.getServerState());
    }

    private String getRecordTimeString(DeviceItem item){
        Date date=new Date(item.getScantime());
        DateFormat format=new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }
}
