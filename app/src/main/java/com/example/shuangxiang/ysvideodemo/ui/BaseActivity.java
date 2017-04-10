package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by shuang.xiang on 2017/3/2.
 */

public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(savedInstanceState);
        ButterKnife.bind(this);
    }

    protected abstract void initContentView(Bundle savedInstanceState);


}
