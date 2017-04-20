package com.example.shuangxiang.ysvideodemo.ui.home;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.MyDeviceActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public class HomeFragment extends BaseFragment {


    @BindView(R.id.tb_home)
    Toolbar mTbHome;
    @BindView(R.id.banner_home)
    ConvenientBanner mBannerHome;
    @BindView(R.id.ll_home_myDevice)
    RelativeLayout mLlHomeMyDevice;
    @BindView(R.id.ll_home_monitoring)
    RelativeLayout mLlHomeMonitoring;
    @BindView(R.id.ll_home_warning)
    RelativeLayout mLlHomeWarning;
    @BindView(R.id.ll_home_setting)
    RelativeLayout mLlHomeSetting;
    @BindView(R.id.rv_home_product)
    RecyclerView mRvHomeProduct;
    Unbinder unbinder;
    Unbinder unbinder1;
    Unbinder unbinder2;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_home;
    }

    @Override
    protected void init() {




    }

    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = getStatusBarHeight(getActivity());
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @OnClick({R.id.ll_home_myDevice, R.id.ll_home_monitoring, R.id.ll_home_warning, R.id.ll_home_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_myDevice:
                startActivity(new Intent(getActivity(), MyDeviceActivity.class));
                break;
            case R.id.ll_home_monitoring:
                break;
            case R.id.ll_home_warning:
                break;
            case R.id.ll_home_setting:
                break;
        }
    }

}
