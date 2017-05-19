package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IMyDeviceListV {

    void setData(List<String> names, List<String> status,List<String> ids,List<String> dataTemplateIds);

    String getName();

    int getPagerNum();

    int getPagerSize();

    void refresh();

    void upload();

}
