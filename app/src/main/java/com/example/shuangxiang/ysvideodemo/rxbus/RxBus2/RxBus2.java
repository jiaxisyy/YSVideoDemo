package com.example.shuangxiang.ysvideodemo.rxbus.RxBus2;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by shuang.xiang on 2017/5/16.
 */

public class RxBus2 {
    private final PublishSubject<Object> _bus = PublishSubject.create();
}
