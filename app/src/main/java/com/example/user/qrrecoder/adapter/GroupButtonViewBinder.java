package com.example.user.qrrecoder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.qrrecoder.R;
import com.example.user.qrrecoder.bean.GroupTitle;
import com.example.user.qrrecoder.bean.ItemButton;
import com.example.user.qrrecoder.materdesign.TipTextView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by dxs on 2017/12/21.
 */

public class GroupButtonViewBinder extends BaseViewBinder<ItemButton,GroupButtonViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected GroupButtonViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_button, parent, false);
        return new GroupButtonViewBinder.ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull GroupButtonViewBinder.ViewHolder holder, @NonNull final ItemButton item) {
        holder.textNumber.setText(item.getBtn());
        holder.textNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemClick(item);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private Button textNumber;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumber=itemView.findViewById(R.id.btn_logout);
        }
    }
}
