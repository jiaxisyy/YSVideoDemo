package com.example.shuangxiang.ysvideodemo.ui.warning.record.search;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningListSearchActivity extends BaseActivity {
    @BindView(R.id.tv_warning_listSearch_startTime)
    TextView mTvStartTime;
    @BindView(R.id.tv_warning_listSearch_endTime)
    TextView mTvEndTime;
    @BindView(R.id.tv_warning_listSearch_cancel)
    TextView mTvCancel;
    @BindView(R.id.rv_warning_list_search)
    RecyclerView mRv;

    @Override
    protected void initContentView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_warning_list_search);
    }

    @Override
    protected void initSomething() {

    }


    @OnClick({R.id.tv_warning_listSearch_startTime, R.id.tv_warning_listSearch_endTime, R.id.tv_warning_listSearch_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_warning_listSearch_startTime:
                break;
            case R.id.tv_warning_listSearch_endTime:
                break;
            case R.id.tv_warning_listSearch_cancel:
                finish();
                break;
        }
    }
}
