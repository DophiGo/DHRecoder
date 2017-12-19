package com.example.user.qrrecoder.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.Toast;

import com.example.user.qrrecoder.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by dxs on 2017/12/4.
 */

public class ToastUtils {
    public static void ShowSuccess(Context context, String text, int time, boolean withIcon){
        Toasty.success(context, text, time, withIcon).show();
    }

    public static void ShowSuccess(Context context, String text){
        ShowSuccess(context, text, 1500, true);
    }

    public static void ShowScanSuccess(Context context, String text){
        Toasty.Config.getInstance()
                .setSuccessColor(context.getResources().getColor(R.color.color_80000000))
                .setTextSize(12).apply();
        Toast toast=Toasty.success(context, text, 500, false);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public static void ShowError(Context context, String text, int time,boolean withIcon){
        Toasty.error(context, text, time, withIcon).show();
    }

    public static void ShowNormoal(Context context, String text, int time,Drawable Icon){
        Toasty.normal(context, text, time, Icon).show();
    }
}
