package com.example.shuangxiang.ysvideodemo.feedback;

import android.os.Bundle;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

/**
 * Created by shuang.xiang on 2017/4/12.
 */

public class FeedbackActivity extends BaseActivity implements IFeedbackView {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initSomething() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public String getFeedbackPhone() {
        return null;
    }

    @Override
    public String getFeedbackMessage() {
        return null;
    }
}
