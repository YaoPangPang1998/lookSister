package com.example.looksister;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class okAPP extends Application {
    private static okAPP context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Utils.init(this);
    }
    public static okAPP getContext(){return context;}
}
