package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.PermissionUtils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.IMyDeviceMapV;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p.MyDeviceMapP;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceMapFragment extends BaseFragment implements IMyDeviceMapV, ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 2;
    @BindView(R.id.mapView_mydevice)
    MapView mMapView;
    @BindView(R.id.ll_mydevice_on)
    LinearLayout mLlOn;
    @BindView(R.id.ll_mydevice_off)
    LinearLayout mLlOff;
    @BindView(R.id.ll_mydevice_all)
    LinearLayout mLlAll;
    private MyDeviceMapP mPresenter;

    public MyDeviceMapFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyDeviceMapFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static MyDeviceMapFragment getInstance() {
        if (instance == null) {
            synchronized (MyDeviceMapFragment.class) {
                if (instance == null) {
                    instance = new MyDeviceMapFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mydevice_pager_map;
    }

    @Override
    protected void init() {
        mPresenter = new MyDeviceMapP(this, getActivity(), mMapView);
        PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_ACCESS_FINE_LOCATION, mPermissionGrant);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {


        PermissionUtils.requestPermissionsResult(getActivity(), requestCode, permissions, grantResults, mPermissionGrant);
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_ACCESS_FINE_LOCATION",
                            Toast.LENGTH_SHORT).show();
                    mPresenter.clickAll();
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    Toast.makeText(getActivity(), "Result Permission Grant CODE_ACCESS_COARSE_LOCATION",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void initData() {

    }

    @Override
    public void showDeviceOn() {

    }

    @Override
    public void showDeviceOff() {

    }

    @Override
    public void showDeviceAll() {


    }

    @Override
    public void showToast(String toast) {
        CustomToast.showToast(getActivity(), toast, Toast.LENGTH_SHORT);
    }


    @OnClick({R.id.ll_mydevice_on, R.id.ll_mydevice_off, R.id.ll_mydevice_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mydevice_on:
                break;
            case R.id.ll_mydevice_off:
                break;
            case R.id.ll_mydevice_all:
                break;
        }
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        Log.d("TEST", "MyDeviceMapFragment->onDestroy");
//        if (mMapView != null) {
//            mMapView.onDestroy();
//        }
//    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
