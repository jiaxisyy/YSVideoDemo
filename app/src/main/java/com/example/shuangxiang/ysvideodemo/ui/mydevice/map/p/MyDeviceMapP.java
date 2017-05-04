package com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.shuangxiang.ysvideodemo.MyLocationListener;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.rxbus.RxBus;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.IMyDeviceMapV;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by shuang.xiang on 2017/5/4.
 */

public class MyDeviceMapP implements IMydeviceMapP, BDLocationListener {
    private IMyDeviceMapV mView;
    private Context mContext;
    private LocationClient mLocationClient;
    private static final int MAKERTYPE_MYSELF = 0;
    private static final int MAKERTYPE_ON = -1;
    private static final int MAKERTYPE_OFF = 1;
    public BDLocationListener myListener = new MyLocationListener();
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    public MyDeviceMapP(IMyDeviceMapV view, Context context, MapView mapView) {
        mView = view;
        mContext = context;
        this.mMapView = mapView;
        mLocationClient = new LocationClient(mContext);
    }

    @Override
    public void clickOn() {

    }

    @Override
    public void clickOff() {

    }

    @Override
    public void clickAll() {
        initBaiDuMap();
        RxBus.getDefault().toObservable().subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                List<MyDeviceInfo.ListBean> list = (List<MyDeviceInfo.ListBean>) o;
                String name = list.get(0).getName();
                int size = list.size();
                Log.d("TEST", "MyDeviceMapP->clickAll->name->" + name);

                for (int i = 0; i < size; i++) {
                    if (list.get(i).getOnlineStatus().equals("ONLINE")) {
                        addMaker(Double.valueOf(list.get(i).getLatitude()), Double.valueOf(list.get(i)
                                .getLongitude()), MAKERTYPE_ON);
                    } else {
                        addMaker(Double.valueOf(list.get(i).getLatitude()), Double.valueOf(list.get(i)
                                .getLongitude()), MAKERTYPE_OFF);
                    }
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public void addMaker(double latitude, double lontitude, int makerType) {
        LatLng ll = new LatLng(latitude, lontitude);
        //构建Marker图标
        BitmapDescriptor maker = null;
        switch (makerType) {
            case MAKERTYPE_MYSELF:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_earlywarning_map_positioning);
                break;
            case MAKERTYPE_ON:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_mydevice_online_green);
                break;
            case MAKERTYPE_OFF:
                maker = BitmapDescriptorFactory.fromResource(R.drawable.icon_mydevice_offline_grey);
                break;
        }
        OverlayOptions mygps = new MarkerOptions()
                .position(ll)
                .icon(maker);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(mygps);
        if (makerType == MAKERTYPE_MYSELF) {
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }


    }


    @Override
    public void initBaiDuMap() {

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient.registerLocationListener(this);
        initLocation();
        mLocationClient.start();

    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        Log.d("TEST", "MyDeviceMapP->clickAll->size->onReceiveLocation");
        double latitude = bdLocation.getLatitude();
        double longitude = bdLocation.getLongitude();
        mLocationClient.stop();
        Log.d("TEST", "MyDeviceMapP->clickAll->size->onReceiveLocation->stop");
        addMaker(latitude, longitude, MAKERTYPE_MYSELF);

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);
//        可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的


        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要


        mLocationClient.setLocOption(option);
    }

}
