package com.example.shuangxiang.ysvideodemo.ui.home;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.presenter.HomeFragmentPresenter;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.view.IHomeFragmentView;
import com.example.shuangxiang.ysvideodemo.ui.home.product.adapter.HomeProductAdapter;
import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;
import com.example.shuangxiang.ysvideodemo.ui.home.product.presenter.HomeProductPresenter;
import com.example.shuangxiang.ysvideodemo.ui.home.product.view.IHomeProductView;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.MyDeviceActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public class HomeFragment extends BaseFragment implements IHomeFragmentView, IHomeProductView {
    private HomeFragmentPresenter mPresenter;
    private HomeProductPresenter mProductPresenter;
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


    @Override
    protected int getLayoutId() {

        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        setImmerseLayout(mTbHome);
        setBanner();
        mProductPresenter = new HomeProductPresenter(this, getActivity());
        mProductPresenter.load();

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

    @Override
    public void setBanner() {
        mPresenter = new HomeFragmentPresenter(this, getActivity());
        mPresenter.loadBanner();
    }

    @Override
    public void setBannersListUrl(List<String> list) {

        mBannerHome.setPages(new CBViewHolderCreator<NetworkGlideView>() {
            @Override
            public NetworkGlideView createHolder() {
                return new NetworkGlideView();
            }
        }, list).setPageIndicator(new int[]{R.drawable.yuan_dangqian, R.drawable.yuan_default});
        mBannerHome.startTurning(5000);
    }

    @Override
    public void setProductResouce(List<ProductInfo.ListBean> list) {

        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setAutoMeasureEnabled(true);
        mRvHomeProduct.setLayoutManager(layoutManager);
        mRvHomeProduct.setAdapter(new HomeProductAdapter(list, getActivity()));

    }

//    // 开始自动翻页
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //开始自动翻页
//        convenientBanner.startTurning(5000);
//    }
//
//    // 停止自动翻页
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //停止翻页
//        convenientBanner.stopTurning();
//    }
}