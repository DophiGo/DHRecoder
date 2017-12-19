package com.example.user.qrrecoder.base;

import android.util.Log;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by USER on 2017/11/8.
 */

public abstract class BaseFullScreenActivity extends BaseCoreActivity {
    @Override
    public boolean initBar() {
        return true;
    }

    @Override
    public boolean destroyBar() {
        return true;
    }
}
