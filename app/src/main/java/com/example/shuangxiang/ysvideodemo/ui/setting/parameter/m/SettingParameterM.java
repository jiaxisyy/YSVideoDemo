package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p.SettingParameterP;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class SettingParameterM implements ISettingParameterM {
    private SettingParameterP mSettingParameterP;

    public SettingParameterM(SettingParameterP settingParameterP) {
        mSettingParameterP = settingParameterP;
    }

    @Override
    public void getParameterTitle(String url) {
        ApiManager.getInstance().getParameterTitle(url).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe(new Observer<ParameterInfo[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ParameterInfo[] parameterInfos) {
                mSettingParameterP.getTitleSucceed(parameterInfos);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ERROR", "SettingParameterM->" + e.getMessage().toString());

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void getParameterValue(String url) {
        ApiManager.getInstance().getParameterValue(url).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mSettingParameterP.getValueSucceed(s);

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
