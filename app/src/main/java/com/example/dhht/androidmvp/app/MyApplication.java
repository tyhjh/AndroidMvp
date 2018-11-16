package com.example.dhht.androidmvp.app;

import android.app.Application;

import com.yorhp.picturepick.PicturePickUtil;

import log.LogUtils;
import snackBar.SnackbarUtil;
import toast.ToastUtil;

public class MyApplication extends Application {

    public static final boolean IS_DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        PicturePickUtil.init("com.example.dhht.androidmvp");
        ToastUtil.init(this);
        LogUtils.init(IS_DEBUG, null);
    }
}
