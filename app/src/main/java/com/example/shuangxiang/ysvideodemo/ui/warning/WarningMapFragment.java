package com.example.shuangxiang.ysvideodemo.ui.warning;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningMapFragment extends BaseFragment {
    public WarningMapFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile WarningMapFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static WarningMapFragment getInstance() {
        if (instance == null) {
            synchronized (WarningMapFragment.class) {
                if (instance == null) {
                    instance = new WarningMapFragment();
                }
            }
        }
        return instance;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_warning_map;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {

    }
}
