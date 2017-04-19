package com.example.shuangxiang.ysvideodemo.login.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.login.bean.LoginInfo;
import com.example.shuangxiang.ysvideodemo.login.model.ILoginModel;
import com.example.shuangxiang.ysvideodemo.login.model.LoginModel;
import com.example.shuangxiang.ysvideodemo.login.view.ILoginView;
import com.example.shuangxiang.ysvideodemo.ui.HomeActivity;

import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/7.
 */

public class LoginPresenter implements ILoginPresenter {

    private final ILoginModel mLoginModel;
    private final ILoginView mLoginView;
    private Context mContext;
    private boolean isChecked;

    public LoginPresenter(ILoginView loginView, Context context, Boolean isChecked) {
        this.mContext = context;
        mLoginModel = new LoginModel(this, context);
        mLoginView = loginView;
        this.isChecked = isChecked;
    }

    @Override
    public void login(User user) {
        if (Utils.isNetworkConnected(mContext)) {
            mLoginModel.getLoginInfo(user);
        } else {
            loginFailed("网络未连接");
        }

    }


    @Override
    public void loginSucceed(LoginInfo info) {
        if (isChecked) {
            CacheUtils.putString(mContext, Constants.Define.USERNAME, mLoginView.getUserName());
            CacheUtils.putString(mContext, Constants.Define.PASSWORD, mLoginView.getPassWord());
        }


        mContext.startActivity(new Intent(mContext, HomeActivity.class));
    }

    @Override
    public void loginFailed(String info) {
        mLoginView.showFailedError(info);
    }
}
