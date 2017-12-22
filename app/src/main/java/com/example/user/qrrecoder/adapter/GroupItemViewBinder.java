package com.example.user.qrrecoder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.bean.GroupTitle;
import com.example.user.qrrecoder.bean.ItemTitle;
import com.example.user.qrrecoder.materdesign.TipTextView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dxs on 2017/12/21.
 */

public class GroupItemViewBinder extends ItemViewBinder<ItemTitle,GroupItemViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected GroupItemViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_content, parent, false);
        return new GroupItemViewBinder.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull GroupItemViewBinder.ViewHolder holder, @NonNull ItemTitle item) {
        holder.textKey.setText(item.getKey());
        holder.textValue.setText(item.getValue());
        holder.textValue.setTextColor(holder.textValue.getContext().getResources().getColor(R.color.color_a1a1a1));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textKey;
        private TextView textValue;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textKey=itemView.findViewById(R.id.tx_key);
            textValue=itemView.findViewById(R.id.tx_value);
        }
    }
}
