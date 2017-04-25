package com.example.shuangxiang.ysvideodemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment {


    private Unbinder mUnbinder;

    /**
     * 获取布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void init();
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initData();
        init();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
