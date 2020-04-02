package com.example.looksister;

import com.example.looksister.Interfaces.IWebManager;

public class WebFacttroy {
    /*
    * 网络工厂模式
    * */
    public static IWebManager getWebManager(){
        return RetrofitManager.getInstance();
    }
}
