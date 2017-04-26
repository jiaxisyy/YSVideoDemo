package com.example.shuangxiang.ysvideodemo.ui.myself;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.feedback.FeedbackActivity;
import com.example.shuangxiang.ysvideodemo.myservice.MyServiceActivity;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public class MyselfFragment extends BaseFragment {
    @BindView(R.id.tb_myself)
    Toolbar mTbMyself;
    @BindView(R.id.rl_myself_myservice)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.rl_myself_feedback)
    RelativeLayout mRlMyselfFeedback;

    public MyselfFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyselfFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static MyselfFragment getInstance() {
        if (instance == null) {
            synchronized (MyselfFragment.class) {
                if (instance == null) {
                    instance = new MyselfFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void init() {
        setImmerseLayout(mTbMyself);

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.rl_myself_myservice)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MyServiceActivity.class));
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


    @OnClick(R.id.rl_myself_feedback)
    public void onViewClickedFeedback() {
        startActivity(new Intent(getActivity(), FeedbackActivity.class));
    }
}
