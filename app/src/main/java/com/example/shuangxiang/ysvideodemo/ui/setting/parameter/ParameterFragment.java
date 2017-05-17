package com.example.shuangxiang.ysvideodemo.ui.setting.parameter;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p.MyDeviceMapP;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/17.
 */

public class ParameterFragment extends BaseFragment implements MyDeviceMapP.IToDataShow {
    @BindView(R.id.iv_parameter_notice)
    ImageView mIvNotice;
    @BindView(R.id.tb_setting_parameter)
    Toolbar mTb;
    @BindView(R.id.iv_parameter_toControl)
    ImageView mIvParameterToControl;
    @BindView(R.id.rv_setting_parameter)
    RecyclerView mRv;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting_parameter;
    }

    @Override
    protected void init() {
        mTb.setNavigationIcon(R.drawable.icon_mydevice_back);
        mTb.setTitle("");
        setImmerseLayout(mTb);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTb);
        setHasOptionsMenu(true);


    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.iv_parameter_notice, R.id.iv_parameter_toControl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_parameter_notice:
                break;
            case R.id.iv_parameter_toControl:
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

    @Override
    public void setId(String id) {
        Log.d("TEST", "setting-parameter-id=" + id);

    }
}
