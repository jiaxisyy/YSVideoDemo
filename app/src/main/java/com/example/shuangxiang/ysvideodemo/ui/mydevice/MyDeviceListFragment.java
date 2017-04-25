package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceListFragment extends BaseFragment implements IMyDeviceListV {

    private MyDeviceListP mPresenter;
    @BindView(R.id.rg_mydevice_list)
    RadioGroup mRgMydeviceList;
    @BindView(R.id.iv_mydevice_search)
    ImageView mIvMydeviceSearch;
    @BindView(R.id.rv_mydevice_list)
    RecyclerView mRecyclerView;


    public MyDeviceListFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyDeviceListFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static MyDeviceListFragment getInstance() {
        if (instance == null) {
            synchronized (MyDeviceListFragment.class) {
                if (instance == null) {
                    instance = new MyDeviceListFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mydevice_pager_list;
    }

    @Override
    protected void init() {


        mRgMydeviceList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {


            }
        });
    }

    @Override
    protected void initData() {
        mPresenter = new MyDeviceListP(this);
        mPresenter.getAllDevice();
    }


    @OnClick(R.id.iv_mydevice_search)
    public void onViewClicked() {
    }

    @Override
    public void setData(List<String> data) {


    }

    @Override
    public void refresh() {

    }

    @Override
    public void upload() {

    }
}
