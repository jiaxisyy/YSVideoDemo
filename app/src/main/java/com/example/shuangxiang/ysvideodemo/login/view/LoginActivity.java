package com.example.shuangxiang.ysvideodemo.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/6.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.et_login_userName)
    EditText mEtLoginUserName;
    @BindView(R.id.et_login_passWord)
    EditText mEtLoginPassWord;
    @BindView(R.id.cb_login_isRemember)
    CheckBox mCbLoginIsRemember;
    @BindView(R.id.btn_login_login)
    Button mBtnLoginLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        Log.d("TEST", getUserName());

    }

    @Override
    public String getUserName() {
        return mEtLoginUserName.getText().toString() == null ? "为null" : mEtLoginUserName.getText()
                .toString().trim();
    }

    @Override
    public String getPassWord() {
        return mEtLoginPassWord.getText().toString();
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

    }

    @Override
    public boolean isRememberPassWord() {
        return false;
    }

    @Override
    public void showFailedError(String error) {
        CustomToast.showToast(this,error, Toast.LENGTH_SHORT);
    }

    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {
    }
}
