package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public interface IDownloadRequest {


    @GET("clientApps/techray-coic")
    Observable<AppMessage> getAppMessage();



}
