package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public interface IDownloadRequest {


    @GET("clientApps/techray-coic")
    Observable<AppMessage> getAppMessage();

    @Streaming//注明为流文件，防止retrofit将大文件读入内存
    @GET("clientApps/techray-coic/file")
    Observable<ResponseBody> down();//通过@Url覆盖baseurl
}
