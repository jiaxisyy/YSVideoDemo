package com.example.shuangxiang.ysvideodemo.login.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.login.presenter.LoginPresenter;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/6.
 */

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_login_userName)
    EditText mEtLoginUserName;
    @BindView(R.id.et_login_passWord)
    EditText mEtLoginPassWord;
    @BindView(R.id.cb_login_isRemember)
    CheckBox mCbLoginIsRemember;
    @BindView(R.id.btn_login_login)
    Button mBtnLoginLogin;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initSomething() {

    }

    @Override
    public String getUserName() {
        if (mEtLoginUserName.getText().toString() != null && !mEtLoginUserName.getText().toString()
                .contains(" ")) {
            return mEtLoginUserName.getText().toString().trim();

        }
        return "userNameError";

    }

    @Override
    public String getPassWord() {
        if (mEtLoginPassWord.getText().toString() != null && !mEtLoginPassWord.getText().toString()
                .contains(" ")) {
            return mEtLoginPassWord.getText().toString().trim();

        }
        return "passWordError";
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassWord() {

    }

    @Override
    public void toMainActivity(User user) {
        LoginPresenter loginPresenter = new LoginPresenter(this, this);
        loginPresenter.login(user);
    }

    @Override
    public boolean isRememberPassWord() {
        return false;
    }

    @Override
    public void showFailedError(String error) {
        CustomToast.showToast(this, error, Toast.LENGTH_SHORT);
    }

    @Override
    public void showPassWord() {

    }

    @Override
    public void hidePassWord() {

    }

    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {

        //有效点击
        if (Utils.isValidClick()) {
            if (getUserName().equals("userNameError") || getPassWord().equals("passWordError")) {
                showFailedError("输入有误,请重新输入");
            }
            toMainActivity(new User(getUserName(), getPassWord()));
        }
    }
}
