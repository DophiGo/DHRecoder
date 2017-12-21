package com.example.user.qrrecoder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.bean.GroupTitle;
import com.example.user.qrrecoder.materdesign.TipTextView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dxs on 2017/12/21.
 */

public class GroupTitleViewBinder extends ItemViewBinder<GroupTitle,GroupTitleViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected GroupTitleViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_title, parent, false);
        return new GroupTitleViewBinder.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull GroupTitleViewBinder.ViewHolder holder, @NonNull GroupTitle item) {
        holder.textNumber.updateText(item.getTitle());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TipTextView textNumber;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumber=itemView.findViewById(R.id.tx_tip_title);
        }
    }
}
