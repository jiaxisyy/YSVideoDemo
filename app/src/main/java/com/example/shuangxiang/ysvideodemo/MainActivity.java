package com.example.shuangxiang.ysvideodemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.RetrofitActivity;
import rxbus2.RxBus2Activity;

import static com.videogo.camera.CameraInfo.VIDEO_LEVEL_HD;

public class MainActivity extends BaseActivity implements SurfaceHolder.Callback {

    private static final String TAG = "MainActivity";
    @BindView(R.id.btn_openRxBus2)
    Button mBtnOpenRxBus2;
    private int mErrorCode = -1;
    private EZOpenSDK mInstance;
    private SurfaceView mRealPlaySv;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer mMEZPlayer;
    private Button mOpenRetro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mInstance = EZOpenSDK.getInstance();

        mInstance.openLoginPage();

        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<EZDeviceInfo> result = null;
                    result = mInstance.getDeviceList(0, 10);
                    EZDeviceInfo mDeviceInfo = result.get(0);
                    String deviceSerial = mDeviceInfo.getDeviceSerial();
                    int cameraNum = mDeviceInfo.getCameraNum();
                    mMEZPlayer = mInstance.createPlayer(deviceSerial, cameraNum);
//                  mMEZPlayer.setHandler(mHandler);
                    mInstance.setVideoLevel(deviceSerial, cameraNum, VIDEO_LEVEL_HD);
                    mMEZPlayer.setSurfaceHold(mRealPlaySh);
                    mMEZPlayer.startRealPlay();

                } catch (BaseException e) {
                    mErrorCode = e.getErrorCode();
                    Log.e(TAG, mErrorCode + "");
                }
            }
        }).start();
    }

    protected void initView() {
        mRealPlaySv = (SurfaceView) findViewById(R.id.realplay_sv);
        mRealPlaySh = mRealPlaySv.getHolder();
        mRealPlaySh.addCallback(this);
        Button btn_weixinLogin = (Button) findViewById(R.id.btn_weixin_login);
        btn_weixinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, BaiDuMapActivity.class));
                finish();


            }
        });
        mOpenRetro = (Button) findViewById(R.id.btn_openRetrofit);
        mOpenRetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
                finish();

            }
        });

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (mMEZPlayer != null) {
            mMEZPlayer.setSurfaceHold(surfaceHolder);
        }
        mRealPlaySh = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @OnClick(R.id.btn_openRxBus2)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, RxBus2Activity.class));
        finish();
    }
}
