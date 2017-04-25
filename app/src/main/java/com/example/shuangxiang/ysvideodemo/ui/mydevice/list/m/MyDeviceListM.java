package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class MyDeviceListM implements IMyDeviceListM {
    private MyDeviceListP mPresenter;

    public MyDeviceListM(MyDeviceListP presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getAllResouce(String orgId, int pageNum, int pageSize) {
        Observable<MyDeviceInfo> observable = ApiManager.getAllDevices(orgId, pageNum, pageSize);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe(new Observer<MyDeviceInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyDeviceInfo myDeviceInfo) {
                List<MyDeviceInfo.ListBean> list = myDeviceInfo.getList();
                mPresenter.getAllDeviceSucceed(list);

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
