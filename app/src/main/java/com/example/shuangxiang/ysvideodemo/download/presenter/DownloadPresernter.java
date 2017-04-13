package com.example.shuangxiang.ysvideodemo.download.presenter;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;
import com.example.shuangxiang.ysvideodemo.download.model.DownloadModel;
import com.example.shuangxiang.ysvideodemo.download.model.IDownloadModel;
import com.example.shuangxiang.ysvideodemo.download.view.IDownloadView;

/**
 * Created by shuang.xiang on 2017/4/12.
 */

public class DownloadPresernter implements IDownloadPresenter {
    private IDownloadView mDownloadView;
    private IDownloadModel mDownloadModel;
    private Context mContext;

    public DownloadPresernter(IDownloadView downloadView, Context context) {
        mDownloadView = downloadView;
        mDownloadModel = new DownloadModel(this);
        mContext = context;
    }

    @Override
    public void startDownload() {

    }

    @Override
    public void stopDownload() {

    }

    @Override
    public void getAppMessageSucceed(AppMessage message) {
        int versionCode = Utils.getVersionCode(mContext);
        if (message.getVersionCode() > versionCode) {
            //执行更新


        } else {


        }


    }

    @Override
    public void getAppMessageFailed() {
        mDownloadView.showUpdateMessage(Constants.Define.UPDATE_FAILED);

    }
}
