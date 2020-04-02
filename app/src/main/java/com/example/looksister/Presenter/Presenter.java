package com.example.looksister.Presenter;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.looksister.Activity.IView;
import com.example.looksister.Interfaces.CallBack;
import com.example.looksister.Model.IModel;
import com.example.looksister.Model.Model;
import com.example.looksister.Sister;
import com.example.looksister.SisterConfig;
import com.example.looksister.okAPP;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements IPresenter {
    IView mView;
    IModel mModel;
    int i = 0;
    int length = 0;
    private static final String TAG = "Presenter";
    public static Sister sisters;

    public Presenter() {
    }

    public Presenter(final IView mView) {

        this.mView = mView;
        mModel = new Model(callBack);
    }

    CallBack callBack = new CallBack() {
        @Override
        public void onSussue(final Sister sister) {
            length=0;
            //mView.UpDataUI();
            //    Sister sisterRe=new Sister();
            List<Sister.ResultsBean> resultsBeans = new ArrayList<>();
            final Sister sister1 = new Sister(resultsBeans);
            for (Sister.ResultsBean resultsBean : sister.getResults()) {
                Glide.with(okAPP.getContext()).load(resultsBean.getUrl())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                end(sister,sister1);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                end(sister,sister1);
                                return false;
                            }
                        }).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {

                    }
                });
            }

//                Presenter.sisters = sister;
//                int i=0;
//                for (Sister.ResultsBean resultsBean:sister.getResults()){
//                   mView.UpDataUI(sister.getResults().get(i).toString());
//                   i++;
//                   if (i>9){
//                       i=0;
//                   }
//                }

        }

        @Override
        public void fali() {

        }
    };
    void end(Sister sister,Sister sister1){
        length++;
        if (length == sister.getResults().size()) {
            if (sister1.getResults().size() == 0) {
                SisterConfig.page++;
                //刷新失败，没一张可用的
            } else {
//                Presenter.sister.addResults(sisterRe.getResults());
                sister1.addResults(sister.getResults());
                Presenter.sisters = sister1;
            }
        }
        Log.e(TAG, "end: "+length+"/////"+SisterConfig.page);
    }
    @Override
    public void upData() {
        if (sisters.getResults().size() != 0) {
            mView.UpDataUI(Presenter.sisters.getResults().get(i).getUrl());
            i++;
            if (Presenter.sisters != null) {
                if (i >= Presenter.sisters.getResults().size()) i = 0;
            }
        } else {
            Toast.makeText(okAPP.getContext(), "2222222", Toast.LENGTH_SHORT).show();
        }

    }
}
