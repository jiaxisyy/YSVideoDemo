package com.example.shuangxiang.ysvideodemo.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.login.view.LoginActivity;

/**
 * Created by shuang.xiang on 2017/4/17.
 */

public class StartActivity extends BaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activiti_start);

    }

    @Override
    protected void initSomething() {
        final boolean isFirst = CacheUtils.getBoolean(StartActivity.this, Constants.Define.FIRST_START, true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Constants.Define.START_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isFirst) {
                    startActivity(new Intent(StartActivity.this, StartPagerActivity.class));
                } else {
                    startActivity(new Intent(StartActivity.this, LoginActivity.class));
                }
                finish();
            }
        }).start();


    }


}
