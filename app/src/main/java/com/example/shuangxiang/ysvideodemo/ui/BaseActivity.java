package com.example.shuangxiang.ysvideodemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by shuang.xiang on 2017/3/2.
 */

public abstract class BaseActivity extends AutoLayoutActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        ButterKnife.bind(this);
        initSomething();
    }

    protected abstract void initContentView(Bundle savedInstanceState);

    protected abstract void initSomething();


}
