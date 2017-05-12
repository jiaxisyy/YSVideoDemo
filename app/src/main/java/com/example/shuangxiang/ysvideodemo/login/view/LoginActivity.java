package com.example.shuangxiang.ysvideodemo.login.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
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
    @BindView(R.id.cb_login_showPassword)
    CheckBox mCbLoginShowPassword;
    @BindView(R.id.btn_login_login)
    Button mBtnLoginLogin;
    private boolean mChecked;
    private ProgressDialog mProgressDialog;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        //当页面更布局有背景时使用可以全屏观看
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initSomething() {
        if (CacheUtils.getString(this, Constants.Define.USERNAME) != null && !CacheUtils.getString
                (this, Constants.Define.USERNAME).equals("") && CacheUtils.getString(this,
                Constants.Define.PASSWORD) != null && !CacheUtils.getString(this, Constants.Define
                .PASSWORD).equals("")) {
            String username = CacheUtils.getString(this, Constants.Define.USERNAME);
            String password = CacheUtils.getString(this, Constants.Define.PASSWORD);
            mEtLoginUserName.setText(username);
            mEtLoginPassWord.setText(password);
        }
        //监听密码状态
        mCbLoginShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //如果选中，显示密码
                    showPassWord();
                } else {
                    //否则隐藏密码
                    hidePassWord();
                }


            }
        });


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
        mProgressDialog.show();

    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();

    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassWord() {

    }

    @Override
    public void toMainActivity(User user) {
        LoginPresenter loginPresenter = new LoginPresenter(this, this, mChecked);
        mProgressDialog = new ProgressDialog(this);
        showLoading();
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
        mEtLoginPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    @Override
    public void hidePassWord() {
        mEtLoginPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {

        //有效点击
        if (Utils.isValidClick()) {
            if (getUserName().equals("userNameError") || getPassWord().equals("passWordError")) {
                showFailedError("输入有误,请重新输入");
            }
            //是否记住密码
            mChecked = mCbLoginIsRemember.isChecked();
            toMainActivity(new User(getUserName(), getPassWord()));
        }
    }
}
