package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.adapter.MyViewPagerAdapter;
import com.zhy.autolayout.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceActivity extends BaseActivity {
    @BindView(R.id.tb_mydevice)
    Toolbar mTbMydevice;

    @BindView(R.id.tbl_mydevice)
    TabLayout mTabLayout;
    @BindView(R.id.vp_mydevice)
    ViewPager mViewPager;
    private List<String> tb_titles;
    private List<View> mViewList = new ArrayList<>();


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mydevice);
    }


    @Override
    protected void initSomething() {
        mTbMydevice.setTitle("");
        setSupportActionBar(mTbMydevice);
        mTbMydevice.setNavigationIcon(R.drawable.icon_mydevice_back);
        setImmerseLayout(mTbMydevice);//状态栏颜色设置
        tb_titles = new ArrayList<>();
        //添加标题
        tb_titles.add(Constants.Define.DEVICELIST.toString());
        tb_titles.add(Constants.Define.DEVICEMAP.toString());

        mTabLayout.setLayoutMode(TabLayout.MODE_FIXED);
//        addFragment();
        for (int i = 0; i < tb_titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tb_titles.get(i)));
        }
        initView();
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initView() {
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragment(MyDeviceListFragment.getInstance(), Constants.Define.DEVICELIST);
        myViewPagerAdapter.addFragment(MyDeviceMapFragment.getInstance(), Constants.Define.DEVICEMAP);
        mViewPager.setAdapter(myViewPagerAdapter);
    }


    private void addFragment() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view_list = inflater.inflate(R.layout.mydevice_pager_list, null);
        View view_map = inflater.inflate(R.layout.mydevice_pager_map, null);
        mViewList.add(view_list);
        mViewList.add(view_map);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(mViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mViewList.get(position));
                return mViewList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tb_titles.get(position);
            }
        });


    }

    /**
     * 状态栏颜色设置
     *
     * @param view 与顶部view相同
     */
    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
