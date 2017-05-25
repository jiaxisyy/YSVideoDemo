package com.example.shuangxiang.ysvideodemo.ui.setting.control;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.setting.adapter.ControlRvAdapter;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.ParameterFragment;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p.SettingParameterP;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v.ISettingParameterV;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/17.
 */

public class ControlFragment extends BaseFragment implements ISettingParameterV {
    @BindView(R.id.iv_control_notice)
    ImageView mIvNotice;
    @BindView(R.id.tb_setting_control)
    Toolbar mTb;
    @BindView(R.id.iv_control_toParameter)
    LinearLayout mIvControlToParameter;
    @BindView(R.id.rv_setting_control)
    RecyclerView mRv;
    @BindView(R.id.tv_control_TbTitle)
    TextView mTbTitle;
    private ControlRvAdapter mAdapter;
    private ProgressDialog mDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting_control;
    }

    @Override
    protected void init() {
        mTb.setNavigationIcon(R.drawable.icon_mydevice_back);
        mTb.setTitle("");
        setImmerseLayout(mTb);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTb);
        setHasOptionsMenu(true);
        String title = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE);
        if (title != null && !title.equals("")) {
            mTbTitle.setText(title);
        }
    }

    @Override
    protected void initData() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.show();

        SettingParameterP settingParameterP = new SettingParameterP(this, getActivity());
        settingParameterP.getTitle("CONTROL");
    }

    @Override
    protected boolean isCache() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.iv_control_notice, R.id.iv_control_toParameter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_control_notice:
                startActivity(new Intent(getActivity(), WarningActivity.class));
                break;
            case R.id.iv_control_toParameter:
                Utils.replace(getFragmentManager(), R.id.fl_home2, ParameterFragment.class);
                break;
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

    @Override
    public void setRvData(List<String> names, List<String> values, List<String> ids, List<String>
            units, List<String> defaultAddress) {
        if (names.size() > 0 && values.size() > 0 && ids.size() > 0 && units.size() > 0 && defaultAddress.size() > 0) {
            //默认显示在线
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRv.setHasFixedSize(true);
            layoutManager.setAutoMeasureEnabled(true);
            mRv.setLayoutManager(layoutManager);
            mAdapter = new ControlRvAdapter(getActivity(), names, values);
            mRv.setAdapter(mAdapter);
        }else {
            CustomToast.showToast(getActivity(),Constants.Define.SERVERDATAERROR, Toast.LENGTH_SHORT);
        }
        mDialog.dismiss();

    }

    @Override
    public void setToast(String s) {

    }
}
