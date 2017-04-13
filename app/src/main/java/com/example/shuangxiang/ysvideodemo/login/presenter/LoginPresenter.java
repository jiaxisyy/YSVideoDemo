package com.example.shuangxiang.ysvideodemo.login.presenter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.login.bean.LoginInfo;
import com.example.shuangxiang.ysvideodemo.login.model.ILoginModel;
import com.example.shuangxiang.ysvideodemo.login.model.LoginModel;
import com.example.shuangxiang.ysvideodemo.login.view.ILoginView;

import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/7.
 */

public class LoginPresenter implements ILoginPresenter {

    private final ILoginModel mLoginModel;
    private final ILoginView mLoginView;
    private Context mContext;

    public LoginPresenter(ILoginView loginView, Context context) {
        this.mContext = context;
        mLoginModel = new LoginModel(this,context);
        mLoginView = loginView;
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
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("TEST", fileStoreDir);

    }

    @Override
    public void loginFailed(String info) {
        mLoginView.showFailedError(info);
    }
}
