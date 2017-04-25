package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceMapFragment extends BaseFragment {

    public MyDeviceMapFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyDeviceMapFragment instance;
    //定义一个共有的静态方法，返回该类型实例
    public static MyDeviceMapFragment getInstance() {
        if (instance == null) {
            synchronized (MyDeviceMapFragment.class) {
                if (instance == null) {
                    instance = new MyDeviceMapFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mydevice_pager_map;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }
}
