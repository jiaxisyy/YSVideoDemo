package com.example.shuangxiang.ysvideodemo.myservice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by shuang.xiang on 2017/4/13.
 */

public class MyServiceActivity extends BaseActivity implements IMyService {
    @BindView(R.id.tb_myservice)
    Toolbar mTbMyservice;
    @BindView(R.id.tv_callPhone)
    TextView mTvCallPhone;
    private PopupWindow mPopupWindow;
    private TextView mMessage;
    private TextView mCancel;
    private TextView mSure;

    @Override
    protected void initContentView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_myservice);
    }

    @Override
    protected void initSomething() {
        mTbMyservice.setNavigationIcon(R.drawable.icon_back);
        mTbMyservice.setTitle("");
        setSupportActionBar(mTbMyservice);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tv_callPhone)
    public void onViewClicked() {
        showPopupWindow();


    }


    @Override
    public void showPopupWindow() {

        View view = LayoutInflater.from(this).inflate(R.layout.pop_all, null);
        TextView title = (TextView) view.findViewById(R.id.tv_dialog_allTitle);
        mMessage = (TextView) view.findViewById(R.id.tv_dialog_allMessage);
        mCancel = (TextView) view.findViewById(R.id.tv_dialog_allCancel);
        mSure = (TextView) view.findViewById(R.id.tv_dialog_allSure);
        title.setText(R.string.pop_callPhone);
        mMessage.setText(R.string.pop_phoneNum);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.MATCH_PARENT, false);

        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        mPopupWindow.setFocusable(true);
        //下面的是设置外部是否可以点击
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.showAsDropDown(view, 0, 0, Gravity.FILL);


    }


    @Override
    public void finishActivity() {

    }

    @Override
    public void callPhone() {
        String number = mMessage.getText().toString().trim();

        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
