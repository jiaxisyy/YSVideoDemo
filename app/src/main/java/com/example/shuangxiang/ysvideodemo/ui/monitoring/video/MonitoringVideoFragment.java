package com.example.shuangxiang.ysvideodemo.ui.monitoring.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.shuangxiang.ysvideodemo.R;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.videogo.camera.CameraInfo.VIDEO_LEVEL_HD;

/**
 * Created by shuang.xiang on 2017/5/20.
 */

public class MonitoringVideoFragment extends Fragment implements SurfaceHolder.Callback {
    private static final String TAG = "TEST";
//    @BindView(R.id.tv_monitoring_videoTitle)
//    TextView mTvTitle;
//    @BindView(R.id.iv_monitoring_videoNotice)
//    ImageView mIvNotice;
//    @BindView(R.id.tb_monitoring_video)
//    Toolbar mTb;
    @BindView(R.id.realplay_sv_video)
    SurfaceView mRealPlaySv;
    private int mErrorCode = -1;
    private EZOpenSDK mInstance;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer mMEZPlayer;
    private Unbinder mUnbinder;


    private int getLayoutId() {
        return R.layout.fragment_monitoring_video;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }
    private void init() {
        //海康威视
        mInstance = EZOpenSDK.getInstance();
        mInstance.openLoginPage();
        mRealPlaySh = mRealPlaySv.getHolder();
        mRealPlaySh.addCallback(this);
        startVideo();

//        mTb.setNavigationIcon(R.drawable.icon_back);
//        mTb.setTitle("");
//        setImmerseLayout(mTb);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(mTb);
//        setHasOptionsMenu(true);
    }

    private void startVideo() {
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
//
//    protected void setImmerseLayout(View view) {
//        //先将状态栏透明化
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getActivity().getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //获取状态栏的高度
//            int statusBarHeight = getStatusBarHeight(getActivity());
//            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
//            view.setPadding(0, statusBarHeight, 0, 0);
//        }
//    }



//    @OnClick(R.id.iv_monitoring_videoNotice)
//    public void onViewClicked() {
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
