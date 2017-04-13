package com.example.shuangxiang.ysvideodemo.myservice;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by shuang.xiang on 2017/4/13.
 */

public class MyServiceActivity extends BaseActivity implements IMyService {
    @BindView(R.id.tb_myservice)
    Toolbar mTbMyservice;
    @BindView(R.id.tv_callPhone)
    TextView mTvCallPhone;

    @Override
    protected void initContentView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_myservice);
    }

    @Override
    protected void initSomething() {
        mTbMyservice.setNavigationIcon(R.drawable.icon_back);
        setSupportActionBar(mTbMyservice);

    }

    @OnClick(R.id.tv_callPhone)
    public void onViewClicked() {

        showDialog();

    }

    @Override
    public void showDialog() {


    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void finishActivity() {

    }
}
