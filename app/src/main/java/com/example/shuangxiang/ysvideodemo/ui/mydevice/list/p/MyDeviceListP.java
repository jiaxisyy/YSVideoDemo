package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m.IMyDeviceListM;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m.MyDeviceListM;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        mModel.getAllResouce(null, mView.getName(), mView.getPagerNum(), mView.getPagerSize());
    }

    @Override
    public void getAllDeviceSucceed(List<MyDeviceInfo.ListBean> list) {

        Map<String, String> map = new HashMap<>();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            map.put(list.get(i).getName(), list.get(i).getOnlineStatus());
        }
        map.put("测试收费站", "ONLINE");
        mView.setData(map);

    }
}
