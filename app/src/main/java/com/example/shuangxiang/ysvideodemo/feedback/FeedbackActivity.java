package com.example.shuangxiang.ysvideodemo.feedback;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/12.
 */

public class FeedbackActivity extends BaseActivity implements IFeedbackView {
    @BindView(R.id.tv_feedback_submit)
    TextView mTvFeedbackSubmit;
    @BindView(R.id.et_feedback_inputQuestion)
    EditText mEtFeedbackInputQuestion;
    @BindView(R.id.et_feedback_inputPhone)
    EditText mEtFeedbackInputPhone;
    @BindView(R.id.iv_feedback_picAdd)
    ImageView mIvFeedbackPicAdd;
    @BindView(R.id.tv_feedback_wordNum)
    TextView mTvFeedbackWordNum;
    private static int REQUESTCODE = 1;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initSomething() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public String getFeedbackPhone() {
        return null;
    }

    @Override
    public String getFeedbackMessage() {
        return null;
    }


    @OnClick({R.id.tv_feedback_submit, R.id.iv_feedback_picAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_feedback_submit:
                break;
            case R.id.iv_feedback_picAdd:
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, REQUESTCODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE) {

            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(R.id.iv_feedback_picAdd);
                /* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
