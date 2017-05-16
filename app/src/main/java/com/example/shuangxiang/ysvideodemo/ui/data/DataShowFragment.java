package com.example.shuangxiang.ysvideodemo.ui.data;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/2.
 */

public class DataShowFragment extends BaseFragment {
    @BindView(R.id.iv_monitoring_notice)
    ImageView mIvMonitoringNotice;
    @BindView(R.id.tb_data_monitoring)
    Toolbar mTbDataMonitoring;
    @BindView(R.id.iv_datashow_analyze)
    ImageView mIvDatashowAnalyze;
    @BindView(R.id.tv_dataShow_circleTitle)
    TextView mTvDataShowCircleTitle;
    @BindView(R.id.tv_dataShow_circleNum)
    TextView mTvDataShowCircleNum;
    @BindView(R.id.tv_dataShow_circleUnit)
    TextView mTvDataShowCircleUnit;
    @BindView(R.id.ll_dataShow_circle)
    LinearLayout mLlDataShowCircle;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data_show;
    }

    @Override
    protected void init() {
        mTbDataMonitoring.setNavigationIcon(R.drawable.icon_mydevice_back);
        mTbDataMonitoring.setTitle("");
        setImmerseLayout(mTbDataMonitoring);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTbDataMonitoring);
        setHasOptionsMenu(true);
    }

    @Override
    protected void initData() {
        Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.datashow_circle_into);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            mLlDataShowCircle.startAnimation(operatingAnim);
        }




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


    @OnClick({R.id.iv_monitoring_notice, R.id.iv_datashow_analyze})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_monitoring_notice:
                startActivity(new Intent(getActivity(), WarningActivity.class));
                break;
            case R.id.iv_datashow_analyze:


                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
