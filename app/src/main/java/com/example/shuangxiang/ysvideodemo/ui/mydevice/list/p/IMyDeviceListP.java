package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IMyDeviceListP {

    void getAllDevice();


    void getAllDeviceSucceed(List<MyDeviceInfo.ListBean> list);

}
