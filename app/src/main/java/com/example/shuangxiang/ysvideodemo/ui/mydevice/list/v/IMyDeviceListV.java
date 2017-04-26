package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v;

import java.util.Map;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IMyDeviceListV {

    void setData(Map<String, String> map);

    String getName();

    int getPagerNum();

    int getPagerSize();

    void refresh();

    void upload();

}
