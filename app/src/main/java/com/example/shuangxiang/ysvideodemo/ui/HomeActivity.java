package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.home.HomeFragment;
import com.example.shuangxiang.ysvideodemo.ui.myself.MyselfFragment;

import butterknife.BindView;

/**
 * Created by shuang.xiang on 2017/4/18.
 */

public class HomeActivity extends BaseActivity {
    @BindView(R.id.fl_home)
    FrameLayout mFlHome;
    @BindView(R.id.bnv)
    BottomNavigationView mBnv;
    @BindView(R.id.ll_home_test)
    LinearLayout mLinearLayout;
    private Bundle mInstanceState;
    protected Activity mActivity;
    private FragmentTransaction mTransaction;
    private HomeFragment mHomeFragment;
    private MyselfFragment mMyselfFragment;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        //修改状态栏背景色
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.stastusbar_bg),0);
        mInstanceState = savedInstanceState;
    }
    private double getStatusBarHeight(Context context){
        double statusBarHeight = Math.ceil(25 * context.getResources().getDisplayMetrics().density);
        return statusBarHeight;
    }
    @Override
    protected void initSomething() {
        double statusBarHeight = getStatusBarHeight(this);
        Log.d("TEST","导航栏高="+statusBarHeight);
        ActivityManager.getInstance().addActivity(this);
//        mBnv.setItemTextColor(resources.getColorStateList(R.drawable.selector_home_bottom,
//                null));
//        mBnv.setItemIconTintList(resources.getColorStateList(R.drawable.selector_home_bottom, null));

        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.home_bottom_normal),
                getResources().getColor(R.color.home_bottom_checked)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        mBnv.setItemTextColor(csl);
        mBnv.setItemIconTintList(csl);

        setDefaultFragment();
        mBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnv_home:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_home,
                                HomeFragment.class);
                        break;
                    case R.id.bnv_my:
                        Utils.replace(getSupportFragmentManager(), R.id.fl_home,
                                MyselfFragment.class);
                        break;
                }
                return true;
            }
        });

    }

    /**
     * 首次加载的fragment
     */
    private void setDefaultFragment() {
        Utils.replace(getSupportFragmentManager(), R.id.fl_home,
                HomeFragment.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }
}
