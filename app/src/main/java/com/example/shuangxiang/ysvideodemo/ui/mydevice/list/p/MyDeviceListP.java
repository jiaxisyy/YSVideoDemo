package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m.IMyDeviceListM;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m.MyDeviceListM;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class MyDeviceListP implements IMyDeviceListP {
    private IMyDeviceListV mView;
    private IMyDeviceListM mModel;

    public MyDeviceListP(IMyDeviceListV view) {
        mView = view;
        mModel = new MyDeviceListM(this);
    }

    @Override
    public void getAllDevice() {
        //查询条件
        mModel.getAllResouce("", 1, 10);
    }

    @Override
    public void getAllDeviceSucceed(List<MyDeviceInfo.ListBean> list) {
        List<String> titles = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            titles.add(list.get(i).getName());
        }
        mView.setData(titles);

    }
}
