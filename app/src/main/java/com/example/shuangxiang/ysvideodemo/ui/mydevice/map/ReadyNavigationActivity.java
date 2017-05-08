package com.example.shuangxiang.ysvideodemo.ui.mydevice.map;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p.IMyDeviceMapNavigationP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p.IMydeviceMapP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p.MyDeviceMapP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.v.IMyDeviceMapNavigationV;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.v.IMyDeviceMapV;
import com.zhy.autolayout.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/5/8.
 */

public class ReadyNavigationActivity extends BaseActivity implements IMyDeviceMapV, IMyDeviceMapNavigationV {
    @BindView(R.id.tb_readyNavigation)
    Toolbar mTb;
    @BindView(R.id.mapView_ready)
    MapView mMapView;
    @BindView(R.id.tv_readyNavigation_address)
    TextView mTvAddress;
    @BindView(R.id.tv_readyNavigation_startNavigation)
    TextView mTvStartNavigation;
    private IMydeviceMapP mPresenter;
    private IMyDeviceMapNavigationP mNavagationP;
    private double DEFAULT_LATITUDE = 0.0;
    private double DEFAULT_LONGITUDE = 0.0;
    private double mStartLatitude;
    private double mEndLatitude;
    private double mStartLongitude;
    private double mEndLongitude;
    private boolean hasInitSuccess = false;
    private String mAddress;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mydevice_ready_navigation);
    }

    @Override
    protected void initSomething() {
        mAddress = getIntent().getStringExtra("name");
        mStartLatitude = getIntent().getDoubleExtra("startLatitude", DEFAULT_LATITUDE);
        mEndLatitude = getIntent().getDoubleExtra("endLatitude", DEFAULT_LATITUDE);
        mStartLongitude = getIntent().getDoubleExtra("startLongitude", DEFAULT_LONGITUDE);
        mEndLongitude = getIntent().getDoubleExtra("endLongitude", DEFAULT_LONGITUDE);
        mTvAddress.setText(mAddress);
        mTb.setTitle("");
        setSupportActionBar(mTb);
        mTb.setNavigationIcon(R.drawable.icon_back);
        setImmerseLayout(mTb);//状态栏颜色设置
        showDeviceAll();
        mMapView.showZoomControls(false);

    }


    /**
     * 状态栏颜色设置
     *
     * @param view 与顶部view相同
     */
    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("TEST", "MyDeviceActivity->onOptionsItemSelected1");
                finish();
                Log.d("TEST", "MyDeviceActivity->onOptionsItemSelected2");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDeviceOn() {

    }

    @Override
    public void showDeviceOff() {

    }

    @Override
    public void showDeviceAll() {
        mPresenter = new MyDeviceMapP(this, this, mMapView);
        mPresenter.clickAll();
    }

    @Override
    public void showToast(String toast) {

    }

    @OnClick(R.id.tv_readyNavigation_startNavigation)
    public void onViewClicked() {


    }


}
