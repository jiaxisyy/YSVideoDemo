package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.adapter.MydeviceListRVAdapter;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.decoration.MyDecoration;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.search.MyDeviceListSearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceListFragment extends BaseFragment implements IMyDeviceListV {

    @BindView(R.id.rb_mydevice_on)
    RadioButton mRbOn;
    @BindView(R.id.rb_mydevice_off)
    RadioButton mRbOff;
    @BindView(R.id.rb_mydevice_all)
    RadioButton mRbAll;
    private MyDeviceListP mPresenter;
    @BindView(R.id.rg_mydevice_list)
    RadioGroup mRgMydeviceList;
    @BindView(R.id.iv_mydevice_search)
    ImageView mIvMydeviceSearch;
    @BindView(R.id.rv_mydevice_list)
    RecyclerView mRecyclerView;
    private MydeviceListRVAdapter mAdapter;

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

    }

    @Override
    protected void initData() {
        mPresenter = new MyDeviceListP(this);
        mPresenter.getAllDevice();
    }


    @OnClick(R.id.iv_mydevice_search)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MyDeviceListSearchActivity.class));
    }

    @Override
    public void setData(final List<String> names, final List<String> status) {
        //初始化在线
        final List<String> namesOn = new ArrayList<>();
        final List<String> statusOn = new ArrayList<>();
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (status.get(i).equals("ONLINE")) {
                namesOn.add(names.get(i));
                statusOn.add("ONLINE");
            }
        }
        //初始化离线
        final List<String> namesOff = new ArrayList<>();
        final List<String> statusOff = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (status.get(i).equals("OFFLINE")) {
                namesOff.add(names.get(i));
                statusOff.add("OFFLINE");
            }
        }
        mRgMydeviceList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_mydevice_on:
                        mAdapter.setData(namesOn, statusOn);
                        break;
                    case R.id.rb_mydevice_off:
                        mAdapter.setData(namesOff, statusOff);
                        break;
                    case R.id.rb_mydevice_all:
                        mAdapter.setData(names, status);
                        break;
                }

            }
        });

        //默认显示在线
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MydeviceListRVAdapter(namesOn, statusOn, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));

    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPagerNum() {
        return Constants.Define.DEFAULTPAGENUM;
    }

    @Override
    public int getPagerSize() {
        return Constants.Define.DEFAULTPAGESIZE;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void upload() {

    }


}
