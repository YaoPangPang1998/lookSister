package com.example.looksister;

import com.example.looksister.Interfaces.IWebManager;
import com.example.looksister.Interfaces.Sister_API;
import com.example.looksister.Interfaces.iwebCallback;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitManager implements IWebManager {
    //创建Rotrofit单例
    private Retrofit retrofit;
    public static RetrofitManager Instance;
    public static RetrofitManager getInstance(){
        if (Instance==null){
            synchronized (RetrofitManager.class){
                if (Instance==null){
                    Instance=new RetrofitManager();
                }
            }
        }
        return Instance;
    }
    //封装网络请求
    @Override
    public void get(String url, final iwebCallback iwebCallback) {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建接口实例
        Sister_API sister_api=retrofit.create(Sister_API.class);
        sister_api.getCall(SisterConfig.number,SisterConfig.page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Sister>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Sister value) {
                        iwebCallback.succeed(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
